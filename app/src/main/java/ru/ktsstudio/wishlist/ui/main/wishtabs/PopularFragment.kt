package ru.ktsstudio.wishlist.ui.main.wishtabs

import android.os.Bundle
import android.view.View
import io.reactivex.Observable
import ru.ktsstudio.wishlist.R
import ru.ktsstudio.wishlist.ui.app.WishApp
import ru.ktsstudio.wishlist.data.db.WishDatabase
import ru.ktsstudio.wishlist.data.models.WishAdapterModel.Wish
import ru.ktsstudio.wishlist.data.models.WishAdapterModel.Header
import ru.ktsstudio.wishlist.utils.KEY_USER_LOGIN
import java.util.ArrayList

class PopularFragment : WishFragment() {

    private var contactNames: List<String>? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        arguments?.let {
            contactNames = arguments!!.getStringArrayList(ARGS_CONTACT_NAMES)
        }
        super.onViewCreated(view, savedInstanceState)
    }

    override fun getWishes(): Observable<List<Wish>> {
        return wishDao
            .observeAll()
            .map {
                it.filter { item ->
                    item.author.login != sharedPreferences.getString(KEY_USER_LOGIN, "")
                }
            }
    }

    override fun getHeader() = Header(resources.getString(R.string.wishtabs_fragment_tv_header_popular))

    override fun getContactNames() = contactNames

    companion object {
        private const val ARGS_CONTACT_NAMES = "contacts"

        fun newInstance(contactNames: List<String>): PopularFragment {
            val popularFragment = PopularFragment()
            val bundle = Bundle()
            bundle.putStringArrayList(ARGS_CONTACT_NAMES, contactNames as ArrayList)
            popularFragment.arguments = bundle
            return popularFragment
        }
    }

}