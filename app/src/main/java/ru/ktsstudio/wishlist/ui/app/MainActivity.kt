package ru.ktsstudio.wishlist.ui.app

import android.content.IntentFilter
import android.net.ConnectivityManager
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import com.arellomobile.mvp.MvpAppCompatActivity
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import com.github.karczews.rxbroadcastreceiver.RxBroadcastReceivers
import com.google.android.material.snackbar.Snackbar
import io.reactivex.disposables.Disposable
import kotlinx.android.synthetic.main.activity_main.*
import ru.ktsstudio.wishlist.R
import ru.ktsstudio.wishlist.data.prefs.SharedPreferenceRepository
import ru.ktsstudio.wishlist.di.DI
import ru.ktsstudio.wishlist.ui.auth.AuthFragmentContainer
import ru.ktsstudio.wishlist.ui.common.BackButtonListener
import ru.ktsstudio.wishlist.ui.common.GlobalRouterProvider
import ru.ktsstudio.wishlist.ui.main.MainFragmentContainer
import ru.terrakok.cicerone.NavigatorHolder
import ru.terrakok.cicerone.Router
import ru.terrakok.cicerone.android.support.SupportAppNavigator
import ru.terrakok.cicerone.commands.Command
import toothpick.Toothpick
import javax.inject.Inject

class MainActivity : MvpAppCompatActivity(), MainView, GlobalRouterProvider {

    init {
        val scope = Toothpick.openScopes(DI.APP, DI.ACTIVITY)
        Toothpick.inject(this, scope)
    }

    private var broadcastReceiverDisposable: Disposable? = null

    @Inject
    lateinit var sharedPreferenceRepository: SharedPreferenceRepository

    @Inject
    lateinit var navigatorHolder: NavigatorHolder
    @Inject
    lateinit var router: Router

    @InjectPresenter
    lateinit var presenter: MainPresenter

    @ProvidePresenter
    fun providePresenter() = MainPresenter(
        sharedPreferenceRepository,
        router
    )

    private var snackbar: Snackbar? = null

    private val navigator = object : SupportAppNavigator(this, R.id.activity_content) {

        override fun setupFragmentTransaction(
            command: Command?,
            currentFragment: Fragment?,
            nextFragment: Fragment?,
            fragmentTransaction: FragmentTransaction?
        ) {
            if (currentFragment is AuthFragmentContainer && nextFragment is MainFragmentContainer) {
                fragmentTransaction?.setCustomAnimations(R.anim.enter, R.anim.exit)
            } else if (currentFragment is MainFragmentContainer && nextFragment is AuthFragmentContainer) {
                fragmentTransaction?.setCustomAnimations(R.anim.pop_enter, R.anim.pop_exit)
            }
        }

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        if (savedInstanceState == null) {
            presenter.checkLoginState()
        }
    }

    override fun onResume() {
        super.onResume()
        presenter.observeConnectivity(true)
    }

    override fun onResumeFragments() {
        super.onResumeFragments()
        navigatorHolder.setNavigator(navigator)
    }

    override fun onPause() {
        navigatorHolder.removeNavigator()
        presenter.observeConnectivity(false)
        super.onPause()
    }

    override fun onDestroy() {
        super.onDestroy()
        Toothpick.closeScope(DI.ACTIVITY)
    }

    override fun showSnackBar(show: Boolean) {
        if (show) {
            snackbar = makeSnackbar(activity_content)
            snackbar?.show()
        } else {
            snackbar?.dismiss()
        }
    }

    override fun registerBroadcastReciever(register: Boolean) {
        if (register) {
            val filter = IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION)
            broadcastReceiverDisposable =
                RxBroadcastReceivers.fromIntentFilter(applicationContext, filter)
                    .subscribe { intent ->
                        if (intent?.extras?.getBoolean(ConnectivityManager.EXTRA_NO_CONNECTIVITY) == true) {
                            showSnackBar(true)
                        } else {
                            showSnackBar(false)
                        }
                    }
        } else {
            broadcastReceiverDisposable?.dispose()
        }
    }

    override fun onBackPressed() {
        val fragment = supportFragmentManager.findFragmentById(R.id.activity_content)
        if (fragment != null
            && fragment is BackButtonListener &&
            (fragment as BackButtonListener).onBackPressed()
        ) {
            return
        } else {
            super.onBackPressed()
        }
    }

    override fun getGlobalRouter(): Router = router

    private fun makeSnackbar(view: View) =
        Snackbar
            .make(view, R.string.main_activity_snackbar_network_missing, Snackbar.LENGTH_INDEFINITE)
            .setAction(R.string.main_activity_snackbar_network_missing_action) {
                presenter.showSnackBar(false)
            }

}