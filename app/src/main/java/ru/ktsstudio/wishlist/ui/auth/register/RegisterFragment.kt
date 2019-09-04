package ru.ktsstudio.wishlist.ui.auth.register

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import com.jakewharton.rxbinding3.view.clicks
import com.jakewharton.rxbinding3.widget.textChanges
import io.reactivex.rxkotlin.addTo
import kotlinx.android.synthetic.main.fragment_register.*
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

class RegisterFragment : BaseFragment(), RegisterView, BackButtonListener {

    init {
        val scope = Toothpick.openScopes(DI.APP, DI.ACTIVITY, DI.AUTH)
        Toothpick.inject(this, scope)
    }

    @Inject
    lateinit var wishApiRepository: WishApiRepository
    @Inject
    lateinit var sharedPreferenceRepository: SharedPreferenceRepository

    @InjectPresenter
    lateinit var presenter: RegisterPresenter

    @ProvidePresenter
    fun providePresenter() =
        RegisterPresenter(wishApiRepository, sharedPreferenceRepository, resources, localRouter,
            globalRouter
        )

    private val localRouter: Router
        get() = (parentFragment as LocalRouterProvider).getRouter()

    private val globalRouter: Router
        get() = (parentFragment as GlobalRouterProvider).getGlobalRouter()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_register, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupEditText()
        btn_register.clicks().subscribe {
            presenter.register(
                input_name.text.toString(),
                input_surname.text.toString(),
                input_email.text.toString(),
                input_password.text.toString()
            )
        }.addTo(compositeDisposable)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        presenter.onDestroy()
    }

    override fun showLoading(toLoad: Boolean) {
        group_register.isVisible = !toLoad
        progress_bar.isVisible = toLoad
    }

    override fun showToast(text: String) {
        Toast.makeText(activity, text, Toast.LENGTH_LONG).show()
    }

    override fun enableRegister(enable: Boolean) {
        btn_register.isEnabled = enable
    }

    override fun onBackPressed(): Boolean {
        presenter.onBackPressed()
        return true
    }

    private fun setupEditText() {
        val emailObservable = input_email.textChanges()
        val passwordObservable = input_password.textChanges()
        val nameObservable = input_name.textChanges()
        val surnameObservable = input_surname.textChanges()

        presenter.registerBtnActivation(
            emailObservable,
            passwordObservable,
            nameObservable,
            surnameObservable
        )
    }

}