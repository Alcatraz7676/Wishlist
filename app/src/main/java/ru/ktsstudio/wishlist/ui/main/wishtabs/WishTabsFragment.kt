package ru.ktsstudio.wishlist.ui.main.wishtabs

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.fragment_wishtabs.*
import ru.ktsstudio.wishlist.R
import ru.ktsstudio.wishlist.ui.app.MainActivity
import ru.ktsstudio.wishlist.ui.main.wishtabs.adapters.WishTabsFragmentStatePagerAdapter

class WishTabsFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_wishtabs, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupToolbar()
        setupViewPager()
        setupTabLayout()
    }

    private fun setupToolbar() {
        toolbar.title = resources.getString(R.string.app_name)
    }

    private fun setupViewPager() {
        val context = activity as MainActivity
        pager.adapter = WishTabsFragmentStatePagerAdapter(childFragmentManager) { resId -> context.getString(resId) }
    }

    private fun setupTabLayout() {
        tabs.setupWithViewPager(pager)
    }

}