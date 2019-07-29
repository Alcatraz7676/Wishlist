package ru.ktsstudio.wishlist.ui.auth

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import ru.ktsstudio.wishlist.R
import ru.ktsstudio.wishlist.ui.app.ActivityNavigator
import ru.ktsstudio.wishlist.ui.OnBackPressed
import ru.ktsstudio.wishlist.ui.auth.login.LoginFragment
import ru.ktsstudio.wishlist.ui.auth.register.RegisterFragment
import ru.ktsstudio.wishlist.utils.navigateAdd
import ru.ktsstudio.wishlist.utils.navigateReplace

class AuthFragmentContainer : Fragment(), AuthNavigator, OnBackPressed {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_container, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (savedInstanceState == null) {
            navigateToLogin()
        }
    }

    override fun navigateToLogin() {
        childFragmentManager.navigateReplace(R.id.fragment_content, LoginFragment())
    }

    override fun navigateToRegister() {
        childFragmentManager.navigateAdd(R.id.fragment_content, RegisterFragment(), "REGISTER")
    }

    override fun navigateToMain() {
        requireActivity().let { it as? ActivityNavigator }?.navigateToMainScreen()
    }

    override fun onBackPressed() = childFragmentManager.popBackStackImmediate()

}