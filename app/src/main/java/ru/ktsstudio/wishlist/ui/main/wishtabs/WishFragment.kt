package ru.ktsstudio.wishlist.ui.main.wishtabs

import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.jakewharton.rxbinding3.swiperefreshlayout.refreshes
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.addTo
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.fragment_rv.*
import ru.ktsstudio.wishlist.R
import ru.ktsstudio.wishlist.data.db.WishDao
import ru.ktsstudio.wishlist.data.models.WishAdapterModel.Header
import ru.ktsstudio.wishlist.data.models.WishAdapterModel.Wish
import ru.ktsstudio.wishlist.data.network.WishApiService
import ru.ktsstudio.wishlist.di.DI
import ru.ktsstudio.wishlist.ui.BaseFragment
import ru.ktsstudio.wishlist.ui.main.wishtabs.adapters.WishAdapter
import toothpick.Toothpick
import javax.inject.Inject

abstract class WishFragment : BaseFragment() {

    @Inject
    lateinit var sharedPreferences: SharedPreferences
    @Inject
    lateinit var wishApiService: WishApiService
    @Inject
    lateinit var wishDao: WishDao

    private val wishTabsNavigator: WishTabsNavigator
        get() = parentFragment as WishTabsNavigator

    private val wishAdapter: WishAdapter by lazy {
        WishAdapter(
            getHeader(),
            getContactNames(),
            {
                wishTabsNavigator.navigateToWishDetail(it)
            }, {
                wishDao.update(it)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe {
                        Log.d("WishFragment", "Wish успешно добавлен в избранное")
                    }.addTo(compositeDisposable)
            }
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val scope = Toothpick.openScopes(DI.APP, DI.ACTIVITY)
        Toothpick.inject(this, scope)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_rv, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initList()
        initSwipeRefreshLayout()
        getWishes()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {
                tv_empty_list.isVisible = it.isEmpty()
                wishAdapter.items = it.toMutableList()
            }.addTo(compositeDisposable)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        recycler_view.adapter = null
    }

    private fun initList() {
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
                    wishAdapter.items = wishAdapter.items.shuffled().toMutableList()
                }.addTo(compositeDisposable)
        }
    }

    abstract fun getWishes(): Observable<List<Wish>>
    abstract fun getHeader(): Header
    abstract fun getContactNames(): List<String>?

}