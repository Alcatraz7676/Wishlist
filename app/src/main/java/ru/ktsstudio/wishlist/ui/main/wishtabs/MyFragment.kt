package ru.ktsstudio.wishlist.ui.main.wishtabs

import android.util.Log
import androidx.core.view.isVisible
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.fragment_rv.*
import ru.ktsstudio.wishlist.R
import ru.ktsstudio.wishlist.data.models.WishAdapterModel
import ru.ktsstudio.wishlist.data.models.WishAdapterModel.Wish
import ru.ktsstudio.wishlist.data.models.WishAdapterModel.Header
import ru.ktsstudio.wishlist.data.stores.RetrofitStore
import ru.ktsstudio.wishlist.utils.addTo
import kotlin.random.Random

class MyFragment : WishFragment() {

    override fun getWishes(): List<WishAdapterModel> {
        getWishesFromServer()
        return listOf(getHeader())
    }

    private fun getWishesFromServer() {
        RetrofitStore.service.getWishList()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe {
                loading(true)
                tv_empty_list.isVisible = false
            }
            .doOnTerminate { loading(false) }
            .subscribe({ response ->
                if (response?.status == "ok") {
                    val items = response.data.map {
                        Wish(
                            title = it.title,
                            description = it.description,
                            photoId = Random.nextInt(1, 101)
                        )
                    }
                    setItems(items)
                } else {
                    setItems(null)
                    Log.e("Retrofit onFailure", response.message ?: "Не удалось авторизоваться")
                }
            }, { errorResponse ->
                setItems(null)
                Log.e("Retrofit onFailure", errorResponse.message ?: "Не удалось авторизоваться")
            })
            .addTo(compositeDisposable)
    }

    private fun setItems(items: List<WishAdapterModel>?) {
        val header = getHeader()
        tv_empty_list.isVisible = items?.none { it is Wish } != false
        adapter.items = if (items != null) listOf(header) + items else listOf(header)
    }

    private fun getHeader() = Header(resources.getString(R.string.wishtabs_fragment_tv_header_my))

    private fun loading(toLoad: Boolean) {
        progress_bar.isVisible = toLoad
    }

}