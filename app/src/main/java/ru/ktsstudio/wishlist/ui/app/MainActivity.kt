package ru.ktsstudio.wishlist.ui.app

import android.os.Bundle
import android.view.View
import com.arellomobile.mvp.MvpAppCompatActivity
import com.arellomobile.mvp.presenter.InjectPresenter
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_main.*
import ru.ktsstudio.wishlist.R
import ru.ktsstudio.wishlist.di.DI
import ru.ktsstudio.wishlist.di.modules.ActivityModule
import ru.ktsstudio.wishlist.ui.auth.AuthFragmentContainer
import ru.ktsstudio.wishlist.ui.main.MainFragmentContainer
import ru.ktsstudio.wishlist.ui.OnBackPressed
import ru.ktsstudio.wishlist.utils.navigateReplace
import toothpick.Toothpick

class MainActivity : MvpAppCompatActivity(), ActivityNavigator, MainView {

    @InjectPresenter
    lateinit var presenter: MainPresenter

    private var snackbar: Snackbar? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initDi()
    }

    override fun onStart() {
        super.onStart()
        presenter.onStart()
        presenter.checkLoginState()
    }

    override fun showSnackBar(show: Boolean) {
        if (show) {
            snackbar = makeSnackbar(activity_content)
            snackbar?.show()
        } else {
            snackbar?.dismiss()
        }
    }

    override fun navigateToMainScreen() {
        supportFragmentManager.navigateReplace(R.id.activity_content, MainFragmentContainer())
    }

    override fun navigateToLoginScreen() {
        supportFragmentManager.navigateReplace(R.id.activity_content, AuthFragmentContainer())
    }

    override fun onBackPressed() {
        val fragment = supportFragmentManager.findFragmentById(R.id.activity_content)
        if (!(fragment as OnBackPressed).onBackPressed()) {
            super.onBackPressed()
        }
    }

    private fun makeSnackbar(view: View) =
        Snackbar
            .make(view, R.string.main_activity_snackbar_network_missing, Snackbar.LENGTH_INDEFINITE)
            .setAction(R.string.main_activity_snackbar_network_missing_action) {
                presenter.showSnackBar(false)
            }
    private fun initDi() {
        val scope = Toothpick.openScopes(DI.APP, DI.ACTIVITY)
        scope.installModules(
            ActivityModule(this)
        )
        Toothpick.inject(this, scope)
    }
}