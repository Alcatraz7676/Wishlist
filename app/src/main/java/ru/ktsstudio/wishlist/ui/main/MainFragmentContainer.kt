package ru.ktsstudio.wishlist.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import com.arellomobile.mvp.MvpAppCompatFragment
import ru.ktsstudio.wishlist.R
import ru.ktsstudio.wishlist.di.DI
import ru.ktsstudio.wishlist.di.modules.ContentProviderModule
import ru.ktsstudio.wishlist.ui.common.BackButtonListener
import ru.ktsstudio.wishlist.ui.common.GlobalRouterProvider
import ru.ktsstudio.wishlist.ui.common.LocalRouterProvider
import ru.ktsstudio.wishlist.utils.Screens
import ru.ktsstudio.wishlist.utils.setFadeAnimation
import ru.terrakok.cicerone.Cicerone
import ru.terrakok.cicerone.Navigator
import ru.terrakok.cicerone.Router
import ru.terrakok.cicerone.android.support.SupportAppNavigator
import ru.terrakok.cicerone.commands.Command
import ru.terrakok.cicerone.commands.Forward
import toothpick.Toothpick
import javax.inject.Inject
import javax.inject.Named

class MainFragmentContainer : MvpAppCompatFragment(), GlobalRouterProvider, LocalRouterProvider,
    BackButtonListener {

    init {
        val scope = Toothpick.openScopes(DI.APP, DI.ACTIVITY, DI.MAIN)
        scope.installModules(
            ContentProviderModule()
        )
        Toothpick.inject(this, scope)
    }

    @Inject
    @field:Named("local")
    lateinit var localCicerone: Cicerone<Router>

    private var navigator: Navigator? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_container, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (childFragmentManager.findFragmentById(R.id.fragment_content) == null) {
            localCicerone.router.replaceScreen(Screens.WishTabsScreen())
        }
    }

    override fun onResume() {
        super.onResume()
        localCicerone.navigatorHolder.setNavigator(getNavigator())
    }

    override fun onPause() {
        localCicerone.navigatorHolder.removeNavigator()
        super.onPause()
    }

    override fun onDestroy() {
        super.onDestroy()
        Toothpick.closeScope(DI.MAIN)
    }

    override fun getRouter(): Router = localCicerone.router

    override fun getGlobalRouter(): Router = (activity as GlobalRouterProvider).getGlobalRouter()

    override fun onBackPressed(): Boolean {
        val fragment = childFragmentManager.findFragmentById(R.id.fragment_content)
        return if (
            fragment != null
            && fragment is BackButtonListener
            && (fragment as BackButtonListener).onBackPressed()
        ) {
            true
        } else {
            getGlobalRouter().exit()
            true
        }
    }

    private fun getNavigator(): Navigator {
        if (navigator == null) {
            navigator = object : SupportAppNavigator(
                activity, childFragmentManager,
                R.id.fragment_content
            ) {
                override fun setupFragmentTransaction(
                    command: Command?,
                    currentFragment: Fragment?,
                    nextFragment: Fragment?,
                    fragmentTransaction: FragmentTransaction?
                ) {
                    if (command == Forward::class.java)
                        fragmentTransaction?.setFadeAnimation()
                }
            }
        }
        return navigator!!
    }

}