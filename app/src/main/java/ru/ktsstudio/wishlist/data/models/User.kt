package ru.ktsstudio.wishlist.data.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class User(val login: String = "") : Parcelable