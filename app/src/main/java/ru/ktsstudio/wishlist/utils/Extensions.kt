package ru.ktsstudio.wishlist.utils

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager

fun FragmentManager.navigate(containerId: Int, fragment: Fragment) {
    this.beginTransaction()
        .replace(containerId, fragment)
        .commitNow()
}