package ru.ktsstudio.wishlist.ui.main

import android.os.Bundle
import android.view.*
import com.arellomobile.mvp.MvpAppCompatFragment
import ru.ktsstudio.wishlist.R
import ru.ktsstudio.wishlist.data.models.WishAdapterModel
import ru.ktsstudio.wishlist.ui.main.wishtabs.WishTabsFragment
import ru.ktsstudio.wishlist.ui.OnBackPressed
import ru.ktsstudio.wishlist.ui.app.ActivityNavigator
import ru.ktsstudio.wishlist.ui.main.add.WishAddFragment
import ru.ktsstudio.wishlist.ui.main.detail.WishDetailFragment
import ru.ktsstudio.wishlist.utils.navigateAdd
import ru.ktsstudio.wishlist.utils.navigateReplace

class MainFragmentContainer : MvpAppCompatFragment(), MainNavigator, OnBackPressed {

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

    override fun navigateToLogin() {
        requireActivity().let { it as? ActivityNavigator }?.navigateToLoginScreenAnimation()
    }

    override fun navigateToWishAdd() {
        childFragmentManager.navigateAdd(R.id.fragment_content, WishAddFragment())
    }

    override fun navigateToWishDetail(wish: WishAdapterModel.Wish) {
        childFragmentManager.navigateAdd(R.id.fragment_content, WishDetailFragment.newInstance(wish))
    }

    override fun onBackPressed() = childFragmentManager.popBackStackImmediate()

}