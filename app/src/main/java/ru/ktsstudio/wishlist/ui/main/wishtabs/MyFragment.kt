package ru.ktsstudio.wishlist.ui.main.wishtabs

import android.util.Log
import androidx.core.view.isVisible
import kotlinx.android.synthetic.main.fragment_rv.*
import retrofit2.Call
import ru.ktsstudio.wishlist.R
import ru.ktsstudio.wishlist.data.models.WishAdapterModel
import ru.ktsstudio.wishlist.data.models.WishAdapterModel.Wish
import ru.ktsstudio.wishlist.data.models.WishAdapterModel.Header
import ru.ktsstudio.wishlist.data.models.response.WishListResponse
import ru.ktsstudio.wishlist.data.stores.RetrofitStore
import kotlin.random.Random

class MyFragment : WishFragment() {

    private var currentCall: Call<*>? = null

    override fun getWishes(): List<WishAdapterModel> {
        getWishesFromServer()
        return listOf(getHeader())
    }

    override fun onDestroyView() {
        super.onDestroyView()
        currentCall?.cancel()
        currentCall = null
    }

    private fun getWishesFromServer() {

        loading(true)
        tv_empty_list.isVisible = false

        RetrofitStore.service.getWishList()
            .apply { currentCall = this }
            .enqueue(object : retrofit2.Callback<WishListResponse> {

                override fun onFailure(call: Call<WishListResponse>, t: Throwable) {
                    setItems(null)
                    loading(false)
                    Log.e("Retrofit onFailure", t.message, t)
                }

                override fun onResponse(call: Call<WishListResponse>,
                                        response: retrofit2.Response<WishListResponse>) {
                    loading(false)
                    if (response.body()?.status == "ok") {
                        val items = response.body()?.data?.map {
                            Wish(
                                title = it.title,
                                description = it.description,
                                photoId = Random.nextInt(1, 101)
                            )
                        }
                        setItems(items)
                    } else {
                        setItems(null)
                        val errorResponse = response.errorBody()?.string()
                        Log.e("Retrofit onFailure", errorResponse ?: "Не удалось авторизоваться")
                    }
                }

            })
    }

    private fun setItems(items: List<WishAdapterModel>?) {
        val header = getHeader()
        tv_empty_list.isVisible = items?.none { it is Wish } != false
        adapter.items = if (items != null) listOf(header) + items else listOf(header)
    }

    private fun getHeader() = Header(resources.getString(R.string.wishtabs_fragment_tv_header_popular))

    private fun loading(toLoad: Boolean) {
        progress_bar.isVisible = toLoad
    }

}