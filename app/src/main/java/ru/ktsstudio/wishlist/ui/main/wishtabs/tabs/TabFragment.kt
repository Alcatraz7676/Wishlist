package ru.ktsstudio.wishlist.ui.main.wishtabs.tabs

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import com.jakewharton.rxbinding3.swiperefreshlayout.refreshes
import io.reactivex.Observable
import io.reactivex.rxkotlin.addTo
import kotlinx.android.synthetic.main.fragment_rv.*
import ru.ktsstudio.wishlist.R
import ru.ktsstudio.wishlist.data.content_provider.ContentProviderRepository
import ru.ktsstudio.wishlist.data.db.model.Wish
import ru.ktsstudio.wishlist.data.db.repository.WishRepository
import ru.ktsstudio.wishlist.di.DI
import ru.ktsstudio.wishlist.ui.common.BaseFragment
import ru.ktsstudio.wishlist.ui.common.LocalRouterProvider
import ru.ktsstudio.wishlist.ui.main.wishtabs.adapters.WishAdapter
import ru.ktsstudio.wishlist.ui.main.wishtabs.adapters.WishAdapterModel
import ru.ktsstudio.wishlist.ui.main.wishtabs.adapters.WishAdapterModel.Header
import ru.terrakok.cicerone.Router
import toothpick.Toothpick
import javax.inject.Inject

abstract class TabFragment : BaseFragment(), TabView {

    init {
        val scope = Toothpick.openScopes(DI.APP, DI.ACTIVITY, DI.MAIN)
        Toothpick.inject(this, scope)
    }

    @Inject
    lateinit var wishRepository: WishRepository
    @Inject
    lateinit var contentProviderRepository: ContentProviderRepository

    @InjectPresenter
    lateinit var presenter: TabPresenter

    @ProvidePresenter
    fun providePresenter() = TabPresenter(
        wishRepository, contentProviderRepository, localRouter,
        getWishes()
    )

    private val localRouter: Router
        get() = (parentFragment as LocalRouterProvider).getRouter()

    private lateinit var wishAdapter: WishAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_rv, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initAdapter()
        initSwipeRefreshLayout()
        presenter.getItems()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        presenter.onDestroy()
        recycler_view.adapter = null
    }

    override fun setWishes(wishes: List<WishAdapterModel.Wish>) {
        tv_empty_list.isVisible = wishes.isEmpty()
        wishAdapter.setWishItems(wishes)
    }

    override fun setContactNames(contactNames: List<String>) {
        wishAdapter.setContactNames(contactNames)
    }

    private fun initAdapter() {
        wishAdapter = WishAdapter(getHeader(),
            {
                presenter.navigateToWishDetail(it.id)
            }, { wishId, favourite ->
                presenter.addWishToFavourite(wishId, favourite)
            }
        )
        with(recycler_view) {
            adapter = wishAdapter
            layoutManager = LinearLayoutManager(activity)
            addItemDecoration(DividerItemDecoration(activity, DividerItemDecoration.VERTICAL))
            setHasFixedSize(true)
        }
    }

    private fun initSwipeRefreshLayout() {
        with(swipeContainer) {
            setColorSchemeResources(R.color.colorAccent)
            refreshes()
                .doAfterNext { swipeContainer.isRefreshing = false }
                .subscribe {
                    presenter.shuffleItems(wishAdapter.getWishItems())
                }.addTo(compositeDisposable)
        }
    }

    abstract fun getWishes(): Observable<List<Wish>>
    abstract fun getHeader(): Header

}