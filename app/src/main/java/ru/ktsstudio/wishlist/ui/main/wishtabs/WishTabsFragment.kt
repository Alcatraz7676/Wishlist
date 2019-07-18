package ru.ktsstudio.wishlist.ui.main.wishtabs

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
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
        toolbar.title = resources.getString(R.string.app_name)
        setupViewPager()
        setupTabLayout()
    }

    private fun setupViewPager() {
        val context = activity as MainActivity
        pager.adapter = WishTabsFragmentStatePagerAdapter(context.supportFragmentManager, context)
    }

    private fun setupTabLayout() {
        tabs.setupWithViewPager(pager)
    }

    companion object {
        fun newInstance(): WishTabsFragment {
            return WishTabsFragment()
        }
    }

}