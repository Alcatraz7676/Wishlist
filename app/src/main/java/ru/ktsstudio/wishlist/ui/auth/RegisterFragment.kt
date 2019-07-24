package ru.ktsstudio.wishlist.ui.auth

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.fragment_register.*
import ru.ktsstudio.wishlist.R
import ru.ktsstudio.wishlist.data.models.response.AuthResponse
import ru.ktsstudio.wishlist.data.models.body.RegisterBody
import ru.ktsstudio.wishlist.data.models.User
import ru.ktsstudio.wishlist.data.stores.GsonStore
import ru.ktsstudio.wishlist.data.stores.RetrofitStore
import ru.ktsstudio.wishlist.data.stores.TokenStore
import ru.ktsstudio.wishlist.ui.app.MainActivity
import ru.ktsstudio.wishlist.utils.TextChangedListener

class RegisterFragment : Fragment() {

    private val authNavigator: AuthNavigator
        get() = parentFragment as AuthNavigator

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_register, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupEditText()
        btn_register.setOnClickListener {
            register()
        }
    }

    private fun setupEditText() {
        val textChangedListener = TextChangedListener {
            btn_register.isEnabled = input_email.text.isNotBlank() &&
                    input_password.text.isNotBlank() &&
                    input_name.text.isNotBlank() &&
                    input_surname.text.isNotBlank()
        }

        input_name.addTextChangedListener(textChangedListener)
        input_surname.addTextChangedListener(textChangedListener)
        input_email.addTextChangedListener(textChangedListener)
        input_password.addTextChangedListener(textChangedListener)
    }

    private fun register() {
        showLoading(true)
        RetrofitStore.service.register(
            RegisterBody(
                firstName = input_name.text.toString(),
                lastName = input_surname.text.toString(),
                email = input_email.text.toString(),
                password = input_password.text.toString()
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

    private fun showLoading(toLoad: Boolean) {
        group.isVisible = !toLoad
        progress_bar.isVisible = toLoad
    }

    private fun showToast(text: String) {
        Toast.makeText(activity, text, Toast.LENGTH_LONG).show()
    }

}