package ru.ktsstudio.wishlist.ui.main.wishtabs

import android.os.Bundle
import android.view.*
import com.arellomobile.mvp.presenter.InjectPresenter
import com.jakewharton.rxbinding3.view.clicks
import io.reactivex.rxkotlin.addTo
import kotlinx.android.synthetic.main.fragment_wishtabs.*
import ru.ktsstudio.wishlist.R
import ru.ktsstudio.wishlist.data.models.WishAdapterModel.Wish
import ru.ktsstudio.wishlist.ui.BaseFragment
import ru.ktsstudio.wishlist.ui.app.MainActivity
import ru.ktsstudio.wishlist.ui.main.MainNavigator
import ru.ktsstudio.wishlist.ui.main.wishtabs.adapters.WishTabsPagerAdapter

class WishTabsFragment : BaseFragment(), WishTabsNavigator, WishTabsView {

    @InjectPresenter
    lateinit var presenter: WishTabsPresenter

    private val mainNavigator: MainNavigator
        get() = parentFragment as MainNavigator

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        presenter.onCreate()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_wishtabs, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupToolbar()
        setupViewPager()
        setupTabLayout()
        fab_add_wish.clicks().subscribe {
            navigateToWishAdd()
        }.addTo(compositeDisposable)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        (activity as MainActivity).setSupportActionBar(null)
    }

    override fun navigateToWishDetail(wish: Wish) {
        mainNavigator.navigateToWishDetail(wish)
    }

    override fun navigateToWishAdd() {
        mainNavigator.navigateToWishAdd()
    }

    override fun navigateToLogin() {
        mainNavigator.navigateToLogin()
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.main_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.logout -> {
                presenter.logout()
                return true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun setupToolbar() {
        toolbar.title = resources.getString(R.string.app_name)
        (activity as MainActivity).setSupportActionBar(toolbar)
        setHasOptionsMenu(true)
    }

    private fun setupViewPager() {
        with(pager) {
            adapter = WishTabsPagerAdapter(childFragmentManager, presenter.getContactNames()) {
                    resId -> context.getString(resId)
            }
            offscreenPageLimit = 2
        }
    }

    private fun setupTabLayout() {
        tabs.setupWithViewPager(pager)
    }

}