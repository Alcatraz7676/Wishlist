package ru.ktsstudio.wishlist.ui.main.wishtabs

import android.Manifest
import android.content.ContentResolver
import android.content.SharedPreferences
import android.content.pm.PackageManager
import android.os.Bundle
import android.provider.ContactsContract
import android.view.*
import androidx.core.content.ContextCompat
import com.jakewharton.rxbinding3.view.clicks
import io.reactivex.rxkotlin.addTo
import kotlinx.android.synthetic.main.fragment_wishtabs.*
import ru.ktsstudio.wishlist.R
import ru.ktsstudio.wishlist.data.models.WishAdapterModel.Wish
import ru.ktsstudio.wishlist.di.DI
import ru.ktsstudio.wishlist.ui.BaseFragment
import ru.ktsstudio.wishlist.ui.app.MainActivity
import ru.ktsstudio.wishlist.ui.main.MainNavigator
import ru.ktsstudio.wishlist.ui.main.wishtabs.adapters.WishTabsPagerAdapter
import ru.ktsstudio.wishlist.utils.KEY_TOKEN
import ru.ktsstudio.wishlist.utils.KEY_USER_LOGIN
import toothpick.Toothpick
import javax.inject.Inject

class WishTabsFragment : BaseFragment(), WishTabsNavigator {

    @Inject
    lateinit var sharedPreferences: SharedPreferences
    @Inject
    lateinit var contentResolver: ContentResolver
    @Inject
    lateinit var activity: MainActivity

    private val mainNavigator: MainNavigator
        get() = parentFragment as MainNavigator

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val scope = Toothpick.openScopes(DI.APP, DI.ACTIVITY)
        Toothpick.inject(this, scope)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_wishtabs, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupToolbar()
        setupViewPager()
        setupTabLayout()
        fab_add_wish.clicks().subscribe {
            mainNavigator.navigateToWishAdd()
        }.addTo(compositeDisposable)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        activity.setSupportActionBar(null)
    }

    override fun onDestroy() {
        super.onDestroy()
        Toothpick.closeScope(DI.WISHTABS_FRAGMENT)
    }

    override fun navigateToWishDetail(wish: Wish) {
        mainNavigator.navigateToWishDetail(wish)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.main_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.logout -> {
                logout()
                return true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun setupToolbar() {
        toolbar.title = resources.getString(R.string.app_name)
        activity.setSupportActionBar(toolbar)
        setHasOptionsMenu(true)
    }

    private fun setupViewPager() {
        with(pager) {
            adapter =
                WishTabsPagerAdapter(childFragmentManager, getContactNames()) { resId -> context.getString(resId) }
            offscreenPageLimit = 2
        }
    }

    private fun getContactNames(): List<String>? {

        val contactNames = mutableListOf<String>()

        if (ContextCompat.checkSelfPermission(
                requireContext(),
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

    private fun setupTabLayout() {
        tabs.setupWithViewPager(pager)
    }

    private fun logout() {
        sharedPreferences.edit().apply {
            remove(KEY_USER_LOGIN)
            remove(KEY_TOKEN)
        }.apply()
        mainNavigator.navigateToLogin()
    }

}