package ru.ktsstudio.wishlist.ui.main.wishtabs

import android.os.Bundle
import android.view.*
import com.jakewharton.rxbinding3.view.clicks
import kotlinx.android.synthetic.main.fragment_wishtabs.*
import ru.ktsstudio.wishlist.R
import ru.ktsstudio.wishlist.data.models.WishAdapterModel.Wish
import ru.ktsstudio.wishlist.ui.BaseFragment
import ru.ktsstudio.wishlist.ui.main.MainNavigator
import ru.ktsstudio.wishlist.ui.main.wishtabs.adapters.WishTabsPagerAdapter
import ru.ktsstudio.wishlist.utils.addTo

class WishTabsFragment : BaseFragment(), WishTabsNavigator {

    private val mainNavigator: MainNavigator
        get() = parentFragment as MainNavigator

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

    override fun navigateToWishDetail(wish: Wish) {
        mainNavigator.navigateToWishDetail(wish)
    }

    private fun setupToolbar() {
        toolbar.title = resources.getString(R.string.app_name)
    }

    private fun setupViewPager() {
        with(pager) {
            adapter = WishTabsPagerAdapter(childFragmentManager) { resId -> context.getString(resId) }
            offscreenPageLimit = 2
        }
    }

    private fun setupTabLayout() {
        tabs.setupWithViewPager(pager)
    }

}