package ru.ktsstudio.wishlist.ui.auth.login

import android.Manifest
import android.app.Activity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.core.app.ActivityCompat
import androidx.core.view.isVisible
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import com.jakewharton.rxbinding3.view.clicks
import com.jakewharton.rxbinding3.widget.textChanges
import io.reactivex.rxkotlin.addTo
import kotlinx.android.synthetic.main.fragment_login.*
import ru.ktsstudio.wishlist.R
import ru.ktsstudio.wishlist.data.network.repository.WishApiRepository
import ru.ktsstudio.wishlist.data.prefs.SharedPreferenceRepository
import ru.ktsstudio.wishlist.di.DI
import ru.ktsstudio.wishlist.ui.common.BackButtonListener
import ru.ktsstudio.wishlist.ui.common.BaseFragment
import ru.ktsstudio.wishlist.ui.common.GlobalRouterProvider
import ru.ktsstudio.wishlist.ui.common.LocalRouterProvider
import ru.terrakok.cicerone.Router
import toothpick.Toothpick
import javax.inject.Inject

class LoginFragment : BaseFragment(), LoginView, BackButtonListener {

    init {
        val scope = Toothpick.openScopes(DI.APP, DI.ACTIVITY, DI.AUTH)
        Toothpick.inject(this, scope)
    }

    @Inject
    lateinit var wishApiRepository: WishApiRepository
    @Inject
    lateinit var sharedPreferenceRepository: SharedPreferenceRepository

    @InjectPresenter
    lateinit var presenter: LoginPresenter

    @ProvidePresenter
    fun providePresenter() =
        LoginPresenter(
            wishApiRepository,
            sharedPreferenceRepository,
            resources,
            localRouter,
            globalRouter
        )

    private val localRouter: Router
        get() = (parentFragment as LocalRouterProvider).getRouter()

    private val globalRouter: Router
        get() = (parentFragment as GlobalRouterProvider).getGlobalRouter()

    private var rationaleDialog: AlertDialog? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_login, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupEditText()
        btn_login.clicks().subscribe {
            presenter.login(input_email.text.toString(), input_password.text.toString())
        }.addTo(compositeDisposable)
        btn_register.clicks().subscribe {
            presenter.navigateToRegister()
        }.addTo(compositeDisposable)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        presenter.onDestroy()
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        if (requestCode == MY_PERMISSIONS_REQUEST_READ_CONTACTS)
            presenter.navigateToMain()
        else
            super.onRequestPermissionsResult(requestCode, permissions, grantResults)
    }

    override fun showLoading(loading: Boolean) {
        group_login.isVisible = !loading
        progress_bar.isVisible = loading
    }

    override fun showToast(text: String) {
        Toast.makeText(activity, text, Toast.LENGTH_LONG).show()
    }

    override fun showPermissionRationale(show: Boolean) {
        if (show) {
            rationaleDialog = AlertDialog.Builder(requireContext())
                .setMessage(R.string.permission_contacts_rationale)
                .setPositiveButton(android.R.string.ok) { _, _ ->
                    presenter.showRationaleDialog(false)
                }
                .setNegativeButton(android.R.string.no) { _, _ ->
                    presenter.showRationaleDialog(false)
                }
                .show()
        } else {
            rationaleDialog?.dismiss()
        }
    }

    override fun requestContactPermission() {
        if (ActivityCompat.shouldShowRequestPermissionRationale(
                activity as Activity,
                Manifest.permission.READ_CONTACTS
            )
        ) {
            presenter.showRationaleDialog(true)
        } else {
            requestPermissions(
                arrayOf(Manifest.permission.READ_CONTACTS),
                MY_PERMISSIONS_REQUEST_READ_CONTACTS
            )
        }
    }

    override fun enableLogin(enable: Boolean) {
        btn_login.isEnabled = enable
    }

    override fun onBackPressed(): Boolean {
        presenter.onBackPressed()
        return true
    }

    private fun setupEditText() {
        val emailObservable = input_email.textChanges()
        val passwordObservable = input_password.textChanges()

        presenter.loginBtnActivation(emailObservable, passwordObservable)
    }

    companion object {
        private const val MY_PERMISSIONS_REQUEST_READ_CONTACTS = 432
    }

}