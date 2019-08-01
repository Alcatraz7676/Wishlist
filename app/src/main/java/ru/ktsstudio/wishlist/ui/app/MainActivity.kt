package ru.ktsstudio.wishlist.ui.app

import android.content.IntentFilter
import android.net.ConnectivityManager
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.github.karczews.rxbroadcastreceiver.RxBroadcastReceivers
import com.google.android.material.snackbar.Snackbar
import io.reactivex.disposables.CompositeDisposable
import kotlinx.android.synthetic.main.activity_main.*
import ru.ktsstudio.wishlist.R
import ru.ktsstudio.wishlist.data.models.User
import ru.ktsstudio.wishlist.ui.auth.AuthFragmentContainer
import ru.ktsstudio.wishlist.ui.main.MainFragmentContainer
import ru.ktsstudio.wishlist.ui.OnBackPressed
import ru.ktsstudio.wishlist.utils.addTo
import ru.ktsstudio.wishlist.utils.navigateReplace

class MainActivity : AppCompatActivity(), ActivityNavigator {

    private var compositeDisposable = CompositeDisposable()
    private var snackbar: Snackbar? = null
    var currentUser: User? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        if (savedInstanceState == null) {
            navigateToLoginScreen()
        }
    }

    override fun onResume() {
        super.onResume()
        val filter = IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION)
        RxBroadcastReceivers.fromIntentFilter(this, filter)
            .subscribe { intent ->
                if (intent?.extras?.getBoolean(ConnectivityManager.EXTRA_NO_CONNECTIVITY) == true) {
                    snackbar = makeSnackbar(activity_content)
                    snackbar?.show()
                } else {
                    snackbar?.dismiss()
                }
            }.addTo(compositeDisposable)
    }

    override fun onPause() {
        super.onPause()
        compositeDisposable.clear()
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
        Snackbar.make(view, R.string.main_activity_snackbar_network_missing, Snackbar.LENGTH_INDEFINITE)

}