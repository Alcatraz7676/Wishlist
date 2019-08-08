package ru.ktsstudio.wishlist.di.modules

import android.app.Application
import android.content.Context
import toothpick.config.Module

class AppModule(application: Application) : Module() {

    init {
        bind(Application::class.java).toInstance(application)
    }

}