package ru.ktsstudio.wishlist.ui.main.wishtabs

import android.os.Bundle
import android.view.*
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import com.jakewharton.rxbinding3.view.clicks
import io.reactivex.rxkotlin.addTo
import kotlinx.android.synthetic.main.fragment_wishtabs.*
import ru.ktsstudio.wishlist.R
import ru.ktsstudio.wishlist.data.prefs.SharedPreferenceRepository
import ru.ktsstudio.wishlist.di.DI
import ru.ktsstudio.wishlist.ui.app.MainActivity
import ru.ktsstudio.wishlist.ui.common.BackButtonListener
import ru.ktsstudio.wishlist.ui.common.BaseFragment
import ru.ktsstudio.wishlist.ui.common.GlobalRouterProvider
import ru.ktsstudio.wishlist.ui.common.LocalRouterProvider
import ru.ktsstudio.wishlist.ui.main.wishtabs.adapters.WishTabsPagerAdapter
import ru.terrakok.cicerone.Router
import toothpick.Toothpick
import javax.inject.Inject

class WishTabsFragment : BaseFragment(), WishTabsView, LocalRouterProvider, BackButtonListener {

    init {
        val scope = Toothpick.openScopes(DI.APP, DI.ACTIVITY, DI.MAIN)
        Toothpick.inject(this, scope)
    }

    @Inject
    lateinit var sharedPreferenceRepository: SharedPreferenceRepository

    @InjectPresenter
    lateinit var presenter: WishTabsPresenter

    @ProvidePresenter
    fun providePresenter() =
        WishTabsPresenter(sharedPreferenceRepository, localRouter, globalRouter)

    private val localRouter: Router
        get() = (parentFragment as LocalRouterProvider).getRouter()

    private val globalRouter: Router
        get() = (parentFragment as GlobalRouterProvider).getGlobalRouter()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_wishtabs, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupToolbar()
        setupViewPager()
        setupTabLayout()
        fab_add_wish.clicks().subscribe {
            presenter.navigateToWishAdd()
        }.addTo(compositeDisposable)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        presenter.onDestroy()
        (activity as MainActivity).setSupportActionBar(null)
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

    override fun getRouter(): Router {
        return localRouter
    }

    override fun onBackPressed(): Boolean {
        presenter.onBackPressed()
        return true
    }

    private fun setupToolbar() {
        toolbar.title = resources.getString(R.string.app_name)
        (activity as MainActivity).setSupportActionBar(toolbar)
        setHasOptionsMenu(true)
    }

    private fun setupViewPager() {
        with(pager) {
            adapter = WishTabsPagerAdapter(childFragmentManager) { resId ->
                context.getString(resId)
            }
            offscreenPageLimit = 2
        }
    }

    private fun setupTabLayout() {
        tabs.setupWithViewPager(pager)
    }

}