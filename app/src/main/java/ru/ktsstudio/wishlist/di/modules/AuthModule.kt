package ru.ktsstudio.wishlist.di.modules

import ru.ktsstudio.wishlist.ui.auth.login.ILoginInteractor
import ru.ktsstudio.wishlist.ui.auth.login.LoginInteractor
import ru.ktsstudio.wishlist.ui.auth.register.IRegisterInteractor
import ru.ktsstudio.wishlist.ui.auth.register.RegisterInteractor
import toothpick.config.Module
import toothpick.ktp.binding.bind
import toothpick.ktp.binding.toClass

class AuthModule : Module() {

    init {
        bind<ILoginInteractor>().toClass<LoginInteractor>().singleton()
        bind<IRegisterInteractor>().toClass<RegisterInteractor>().singleton()
    }
}