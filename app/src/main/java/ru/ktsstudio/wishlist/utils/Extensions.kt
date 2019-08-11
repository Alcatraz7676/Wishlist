package ru.ktsstudio.wishlist.utils

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import ru.ktsstudio.wishlist.R

fun FragmentManager.navigateReplace(containerId: Int, fragment: Fragment) {
    this.beginTransaction()
            .replace(containerId, fragment)
            .commitNow()
}

fun FragmentManager.navigateReplaceSlideHorizontalLeftToRight(containerId: Int, fragment: Fragment) {
    this.beginTransaction()
        .setCustomAnimations(R.anim.enter, R.anim.exit)
        .replace(containerId, fragment)
        .commitNow()
}

fun FragmentManager.navigateReplaceSlideHorizontalRightToLeft(containerId: Int, fragment: Fragment) {
    this.beginTransaction()
        .setCustomAnimations(R.anim.pop_enter, R.anim.pop_exit)
        .replace(containerId, fragment)
        .commitNow()
}

fun FragmentManager.navigateAdd(containerId: Int, fragment: Fragment) {
    this.beginTransaction()
        .setCustomAnimations(android.R.anim.fade_in, android.R.anim.fade_out, android.R.anim.fade_in, android.R.anim.fade_out)
        .addToBackStack(null)
        .replace(containerId, fragment)
        .commit()
}