package ru.ktsstudio.wishlist.utils

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager

fun FragmentManager.navigateReplace(containerId: Int, fragment: Fragment) {
    this.beginTransaction()
            .replace(containerId, fragment)
            .commitNow()
}

fun FragmentManager.navigateAdd(containerId: Int, fragment: Fragment) {
    this.beginTransaction()
            .addToBackStack(null)
            .replace(containerId, fragment)
            .commit()
}