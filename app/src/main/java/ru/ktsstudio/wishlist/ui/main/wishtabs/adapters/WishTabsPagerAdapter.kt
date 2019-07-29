package ru.ktsstudio.wishlist.ui.main.wishtabs.adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import ru.ktsstudio.wishlist.R
import ru.ktsstudio.wishlist.ui.main.wishtabs.FavoriteFragment
import ru.ktsstudio.wishlist.ui.main.wishtabs.MyFragment
import ru.ktsstudio.wishlist.ui.main.wishtabs.PopularFragment

class WishTabsPagerAdapter(fragmentManager: FragmentManager, getStr: (Int) -> String) :
    FragmentPagerAdapter(fragmentManager) {

    private val tabs = listOf(
        getStr(R.string.wishtabs_fragment_tab_favorite),
        getStr(R.string.wishtabs_fragment_tab_popular),
        getStr(R.string.wishtabs_fragment_tab_my)
    )

    override fun getItem(position: Int): Fragment {
        return when (position) {
            0 -> FavoriteFragment()
            1 -> PopularFragment()
            2 -> MyFragment()
            else -> error("Unexpected")
        }
    }

    override fun getPageTitle(position: Int): CharSequence? = tabs[position]

    override fun getCount() = tabs.size



}