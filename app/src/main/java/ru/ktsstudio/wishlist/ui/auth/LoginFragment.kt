package ru.ktsstudio.wishlist.ui.auth

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.fragment_login.*
import ru.ktsstudio.wishlist.R
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
        btn_login.setOnClickListener { authNavigator.navigateToMain() }
    }

    private fun setupEditText() {

        val textChangedListener = TextChangedListener {
            btn_login.isEnabled = input_login.text.isNotBlank() && input_password.text.isNotBlank()
        }

        input_login.addTextChangedListener(textChangedListener)
        input_password.addTextChangedListener(textChangedListener)
    }

    companion object {
        fun newInstance(): LoginFragment {
            return LoginFragment()
        }
    }
}