package ru.ktsstudio.wishlist.ui.auth

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import ru.ktsstudio.wishlist.R
import ru.ktsstudio.wishlist.ui.app.ActivityNavigator
import ru.ktsstudio.wishlist.utils.navigate

class AuthFragmentContainer : Fragment(), AuthNavigator {

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
        childFragmentManager.navigate(R.id.fragment_content, LoginFragment.newInstance())
    }

    override fun navigateToMain() {
        requireActivity().let { it as? ActivityNavigator }?.navigateToMainScreen()
    }

    companion object {
        fun newInstance(): AuthFragmentContainer {
            return AuthFragmentContainer()
        }
    }
}