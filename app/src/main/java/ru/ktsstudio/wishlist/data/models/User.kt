package ru.ktsstudio.wishlist.data.models

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = WishAdapterModel.TABLE_NAME)
data class User(
        @ColumnInfo(name = "login")
        val login: String = ""
) : Parcelable