package ru.ktsstudio.wishlist.ui.main.wishtabs.adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import ru.ktsstudio.wishlist.R
import ru.ktsstudio.wishlist.ui.main.wishtabs.tabs.FavoriteFragment
import ru.ktsstudio.wishlist.ui.main.wishtabs.tabs.MyFragment
import ru.ktsstudio.wishlist.ui.main.wishtabs.tabs.PopularFragment

class WishTabsPagerAdapter(
    fragmentManager: FragmentManager,
    private val contactNames: List<String>?,
    getStr: (Int) -> String
) : FragmentPagerAdapter(fragmentManager) {

    private val tabs = listOf(
        getStr(R.string.wishtabs_fragment_tab_favorite),
        getStr(R.string.wishtabs_fragment_tab_popular),
        getStr(R.string.wishtabs_fragment_tab_my)
    )

    override fun getItem(position: Int): Fragment {
        return if (contactNames != null) {
            when (position) {
                0 -> FavoriteFragment.newInstance(contactNames)
                1 -> PopularFragment.newInstance(contactNames)
                2 -> MyFragment()
                else -> error("Unexpected")
            }
        } else {
            when (position) {
                0 -> FavoriteFragment()
                1 -> PopularFragment()
                2 -> MyFragment()
                else -> error("Unexpected")
            }
        }
    }

    override fun getPageTitle(position: Int): CharSequence? = tabs[position]

    override fun getCount() = tabs.size


}