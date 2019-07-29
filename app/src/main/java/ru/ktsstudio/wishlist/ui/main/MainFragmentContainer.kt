package ru.ktsstudio.wishlist.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import ru.ktsstudio.wishlist.R
import ru.ktsstudio.wishlist.data.models.WishAdapterModel
import ru.ktsstudio.wishlist.ui.main.wishtabs.WishTabsFragment
import ru.ktsstudio.wishlist.ui.OnBackPressed
import ru.ktsstudio.wishlist.ui.main.wishtabs.add.WishAddFragment
import ru.ktsstudio.wishlist.ui.main.wishtabs.detail.WishDetailFragment
import ru.ktsstudio.wishlist.utils.navigateAdd
import ru.ktsstudio.wishlist.utils.navigateReplace

class MainFragmentContainer : Fragment(), MainNavigator, OnBackPressed {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_container, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (savedInstanceState == null) {
            navigateToWishTabs()
        }
    }

    override fun navigateToWishTabs() {
        childFragmentManager.navigateReplace(R.id.fragment_content, WishTabsFragment())
    }

    override fun navigateToWishAdd() {
        childFragmentManager.navigateAdd(R.id.fragment_content, WishAddFragment(), "ADD")
    }

    override fun navigateToWishDetail(wish: WishAdapterModel.Wish) {
        childFragmentManager.navigateAdd(R.id.fragment_content, WishDetailFragment.newInstance(wish), "DETAIL")
    }

    override fun onBackPressed() = childFragmentManager.popBackStackImmediate()

}