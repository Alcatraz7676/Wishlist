package ru.ktsstudio.wishlist.di.modules

import ru.ktsstudio.wishlist.ui.main.add.IWishAddInteractor
import ru.ktsstudio.wishlist.ui.main.add.WishAddInteractor
import ru.ktsstudio.wishlist.ui.main.detail.IWishDetailInteractor
import ru.ktsstudio.wishlist.ui.main.detail.WishDetailInteractor
import ru.ktsstudio.wishlist.ui.main.wishtabs.IWishTabsInteractor
import ru.ktsstudio.wishlist.ui.main.wishtabs.WishTabsInteractor
import ru.ktsstudio.wishlist.ui.main.wishtabs.tabs.ITabInteractor
import ru.ktsstudio.wishlist.ui.main.wishtabs.tabs.TabInteractor
import toothpick.config.Module
import toothpick.ktp.binding.bind
import toothpick.ktp.binding.toClass

class MainModule : Module() {

    init {
        bind<IWishTabsInteractor>().toClass<WishTabsInteractor>().singleton()
        bind<ITabInteractor>().toClass<TabInteractor>().singleton()
        bind<IWishDetailInteractor>().toClass<WishDetailInteractor>().singleton()
        bind<IWishAddInteractor>().toClass<WishAddInteractor>().singleton()
    }
}