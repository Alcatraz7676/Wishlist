package ru.ktsstudio.wishlist.di.modules

import android.app.Activity
import toothpick.config.Module

class ActivityModule(activity: Activity): Module() {

    init {
        bind(Activity::class.java).toInstance(activity)
    }

}