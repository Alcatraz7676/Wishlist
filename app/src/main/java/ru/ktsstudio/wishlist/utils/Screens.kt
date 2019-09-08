package ru.ktsstudio.wishlist.utils

import androidx.fragment.app.Fragment
import ru.ktsstudio.wishlist.ui.auth.AuthFragmentContainer
import ru.ktsstudio.wishlist.ui.auth.login.LoginFragment
import ru.ktsstudio.wishlist.ui.auth.register.RegisterFragment
import ru.ktsstudio.wishlist.ui.main.MainFragmentContainer
import ru.ktsstudio.wishlist.ui.main.add.WishAddFragment
import ru.ktsstudio.wishlist.ui.main.detail.WishDetailFragment
import ru.ktsstudio.wishlist.ui.main.wishtabs.WishTabsFragment
import ru.terrakok.cicerone.android.support.SupportAppScreen

class Screens {

    class AuthContainer : SupportAppScreen() {
        override fun getFragment(): Fragment {
            return AuthFragmentContainer()
        }
    }

    class LoginScreen : SupportAppScreen() {
        override fun getFragment(): Fragment {
            return LoginFragment()
        }
    }

    class RegisterScreen : SupportAppScreen() {
        override fun getFragment(): Fragment {
            return RegisterFragment()
        }
    }

    class MainContainer : SupportAppScreen() {
        override fun getFragment(): Fragment {
            return MainFragmentContainer()
        }
    }

    class WishTabsScreen : SupportAppScreen() {
        override fun getFragment(): Fragment {
            return WishTabsFragment()
        }
    }

    class WishAddScreen : SupportAppScreen() {
        override fun getFragment(): Fragment {
            return WishAddFragment()
        }
    }

    class WishDetailScreen(private val wishId: Long) : SupportAppScreen() {
        override fun getFragment(): Fragment {
            return WishDetailFragment.newInstance(wishId)
        }
    }

}