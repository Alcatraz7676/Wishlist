package ru.ktsstudio.wishlist.di.modules

import ru.ktsstudio.wishlist.ui.app.MainActivity
import toothpick.config.Module

class ActivityModule(activity: MainActivity) : Module() {

    init {
        bind(MainActivity::class.java).toInstance(activity)
    }

}