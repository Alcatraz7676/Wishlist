package ru.ktsstudio.wishlist.ui.main.wishtabs.adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import ru.ktsstudio.wishlist.R
import ru.ktsstudio.wishlist.ui.main.wishtabs.tabs.favourite.FavouriteTabFragment
import ru.ktsstudio.wishlist.ui.main.wishtabs.tabs.my.MyTabFragment
import ru.ktsstudio.wishlist.ui.main.wishtabs.tabs.popular.PopularTabFragment

class WishTabsPagerAdapter(
    fragmentManager: FragmentManager,
    getStr: (Int) -> String
) : FragmentStatePagerAdapter(fragmentManager, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

    private val tabs = listOf(
        getStr(R.string.wishtabs_fragment_tab_favorite),
        getStr(R.string.wishtabs_fragment_tab_popular),
        getStr(R.string.wishtabs_fragment_tab_my)
    )

    override fun getItem(position: Int): Fragment {
        return when (position) {
            0 -> FavouriteTabFragment()
            1 -> PopularTabFragment()
            2 -> MyTabFragment()
            else -> error("Unexpected")
        }
    }

    override fun getPageTitle(position: Int): CharSequence? = tabs[position]

    override fun getCount() = tabs.size


}