package ru.ktsstudio.wishlist.ui.auth

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.fragment_login.*
import ru.ktsstudio.wishlist.R
import ru.ktsstudio.wishlist.data.models.response.AuthResponse
import ru.ktsstudio.wishlist.data.models.body.LoginBody
import ru.ktsstudio.wishlist.data.models.User
import ru.ktsstudio.wishlist.data.stores.GsonStore
import ru.ktsstudio.wishlist.data.stores.RetrofitStore
import ru.ktsstudio.wishlist.data.stores.TokenStore
import ru.ktsstudio.wishlist.ui.app.MainActivity
import ru.ktsstudio.wishlist.utils.TextChangedListener

class LoginFragment : Fragment() {

    private val authNavigator: AuthNavigator
        get() = parentFragment as AuthNavigator

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_login, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupEditText()
        btn_login.setOnClickListener {
            (activity as MainActivity).currentUser = User(input_email.text.toString())
            login()
        }
        btn_register.setOnClickListener {
            authNavigator.navigateToRegister()
        }
    }

    private fun setupEditText() {

        val textChangedListener = TextChangedListener {
            btn_login.isEnabled = input_email.text.isNotBlank() && input_password.text.isNotBlank()
        }

        input_email.addTextChangedListener(textChangedListener)
        input_password.addTextChangedListener(textChangedListener)
    }

    private fun login() {
        showLoading(true)
        RetrofitStore.service.login(
            LoginBody(
                input_email.text.toString(),
                input_password.text.toString()
            )
        )
            .enqueue(object : retrofit2.Callback<AuthResponse> {

                override fun onFailure(call: retrofit2.Call<AuthResponse>, t: Throwable) {
                    showLoading(false)
                    showToast("Неизвестная ошибка")
                }

                override fun onResponse(call: retrofit2.Call<AuthResponse>,
                                        response: retrofit2.Response<AuthResponse>) {
                    showLoading(false)
                    if (response.body()?.status == "ok") {
                        TokenStore.token = response.body()?.data?.token
                        (activity as MainActivity).currentUser = User(response.body()?.data?.email!!)
                        authNavigator.navigateToMain()
                    } else {
                        val errorResponse = response.errorBody()?.string()
                        val authResponseError = GsonStore.instance.fromJson(errorResponse, AuthResponse::class.java)
                        showToast(authResponseError?.message ?: "Не удалось авторизоваться")
                    }
                }
            })
    }

    private fun showToast(text: String) {
        Toast.makeText(activity, text, Toast.LENGTH_LONG).show()
    }

    private fun showLoading(toLoad: Boolean) {
        group.isVisible = !toLoad
        progress_bar.isVisible = toLoad
    }

}