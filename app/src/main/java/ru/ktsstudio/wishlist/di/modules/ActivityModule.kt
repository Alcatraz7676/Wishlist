package ru.ktsstudio.wishlist.di.modules

import ru.ktsstudio.wishlist.ui.app.IMainInteractor
import ru.ktsstudio.wishlist.ui.app.MainInteractor
import toothpick.config.Module
import toothpick.ktp.binding.bind
import toothpick.ktp.binding.toClass

class ActivityModule : Module() {

    init {
        bind<IMainInteractor>().toClass<MainInteractor>().singleton()
    }
}