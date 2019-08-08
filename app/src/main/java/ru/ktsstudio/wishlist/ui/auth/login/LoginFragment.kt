package ru.ktsstudio.wishlist.ui.auth.login

import android.Manifest
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.core.app.ActivityCompat
import androidx.core.view.isVisible
import com.arellomobile.mvp.presenter.InjectPresenter
import com.jakewharton.rxbinding3.view.clicks
import com.jakewharton.rxbinding3.widget.textChanges
import io.reactivex.rxkotlin.Observables
import io.reactivex.rxkotlin.addTo
import kotlinx.android.synthetic.main.fragment_login.*
import ru.ktsstudio.wishlist.R
import ru.ktsstudio.wishlist.ui.BaseFragment
import ru.ktsstudio.wishlist.ui.auth.AuthNavigator

class LoginFragment : BaseFragment(), LoginView {

    @InjectPresenter
    lateinit var presenter: LoginPresenter

    private val authNavigator: AuthNavigator
        get() = parentFragment as AuthNavigator

    override fun onStart() {
        super.onStart()
        presenter.onCreate()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_login, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupEditText()
        btn_login.clicks().subscribe {
            presenter.login(input_email.text.toString(), input_password.text.toString())
        }.addTo(compositeDisposable)
        btn_register.clicks().subscribe {
            navigateToRegister()
        }.addTo(compositeDisposable)
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        if (requestCode == MY_PERMISSIONS_REQUEST_READ_CONTACTS)
            navigateToMain()
        else
            super.onRequestPermissionsResult(requestCode, permissions, grantResults)
    }

    override fun requestPermissions() {
        if (ActivityCompat.shouldShowRequestPermissionRationale(
                requireActivity(),
                Manifest.permission.READ_CONTACTS
            )
        ) {
            AlertDialog.Builder(requireContext())
                .setMessage(R.string.permission_contacts_rationale)
                .setPositiveButton(android.R.string.ok) { dialog, _ ->
                    requestContactPermission()
                    dialog.dismiss()
                }
                .setNegativeButton(android.R.string.no) { dialog, _ -> dialog.dismiss() }
                .show()
        } else {
            requestContactPermission()
        }
    }

    override fun showToast(text: String) {
        Toast.makeText(activity, text, Toast.LENGTH_LONG).show()
    }

    override fun showLoading(toLoad: Boolean) {
        group_login.isVisible = !toLoad
        progress_bar.isVisible = toLoad
    }

    override fun navigateToMain() {
        authNavigator.navigateToMain()
    }

    override fun navigateToRegister() {
        authNavigator.navigateToRegister()
    }

    private fun setupEditText() {
        val emailObservable = input_email.textChanges()
        val passwordObservable = input_password.textChanges()

        Observables.combineLatest(emailObservable, passwordObservable) { newEmail, newPassword ->
            btn_login.isEnabled = newEmail.isNotBlank() && newPassword.isNotBlank()
        }.subscribe()
    }

    private fun requestContactPermission() {
        requestPermissions(
            arrayOf(Manifest.permission.READ_CONTACTS),
            MY_PERMISSIONS_REQUEST_READ_CONTACTS
        )
    }

    companion object {
        private const val MY_PERMISSIONS_REQUEST_READ_CONTACTS = 432
    }

}