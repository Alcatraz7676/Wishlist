package ru.ktsstudio.wishlist.ui.main.wishtabs

import android.Manifest
import android.app.Application
import android.content.ContentResolver
import android.content.SharedPreferences
import android.content.pm.PackageManager
import android.provider.ContactsContract
import androidx.core.content.ContextCompat
import com.arellomobile.mvp.InjectViewState
import ru.ktsstudio.wishlist.data.models.WishAdapterModel.Wish
import ru.ktsstudio.wishlist.ui.BasePresenter
import ru.ktsstudio.wishlist.utils.KEY_TOKEN
import ru.ktsstudio.wishlist.utils.KEY_USER_LOGIN
import javax.inject.Inject

@InjectViewState
class WishTabsPresenter : BasePresenter<WishTabsView>() {

    @Inject
    lateinit var sharedPreferences: SharedPreferences
    @Inject
    lateinit var contentResolver: ContentResolver
    @Inject
    lateinit var application: Application

    fun getContactNames(): List<String>? {

        val contactNames = mutableListOf<String>()

        if (ContextCompat.checkSelfPermission(
                application.applicationContext,
                Manifest.permission.READ_CONTACTS
            ) == PackageManager.PERMISSION_GRANTED
        ) {
            val cursor = contentResolver.query(
                ContactsContract.Contacts.CONTENT_URI,
                null,
                null,
                null,
                null
            )
            if (cursor != null && cursor.moveToFirst()) {
                while (cursor.moveToNext()) {
                    val contactName = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME))
                    contactNames.add(contactName)
                }
                cursor.close()
            }
            return contactNames
        } else {
            return null
        }
    }

    fun logout() {
        sharedPreferences.edit().apply {
            remove(KEY_USER_LOGIN)
            remove(KEY_TOKEN)
        }.apply()
        viewState.navigateToLogin()
    }

    fun navigateToWishAdd() {
        viewState.navigateToWishAdd()
    }

}