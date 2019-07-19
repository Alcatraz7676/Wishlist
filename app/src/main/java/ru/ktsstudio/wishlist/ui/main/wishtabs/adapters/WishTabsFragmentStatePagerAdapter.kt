package ru.ktsstudio.wishlist.ui.main.wishtabs.adapters

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import ru.ktsstudio.wishlist.R
import ru.ktsstudio.wishlist.ui.main.wishtabs.FavoriteFragment
import ru.ktsstudio.wishlist.ui.main.wishtabs.MyFragment
import ru.ktsstudio.wishlist.ui.main.wishtabs.PopularFragment

class WishTabsFragmentStatePagerAdapter(fragmentManager: FragmentManager, private val context: Context) :
    FragmentStatePagerAdapter(fragmentManager) {

    private val tabs = listOf(
        getStringFromResource(R.string.wishtabs_fragment_tab_favorite),
        getStringFromResource(R.string.wishtabs_fragment_tab_popular),
        getStringFromResource(R.string.wishtabs_fragment_tab_my)
    )

    override fun getItem(position: Int): Fragment {
        return when (position) {
            0 -> FavoriteFragment.newInstance()
            1 -> PopularFragment.newInstance()
            2 -> MyFragment.newInstance()
            else -> error("Unexpected")
        }
    }

    override fun getPageTitle(position: Int): CharSequence? = tabs[position]

    override fun getCount() = tabs.size

    private fun getStringFromResource(res: Int) = context.getString(res)

}