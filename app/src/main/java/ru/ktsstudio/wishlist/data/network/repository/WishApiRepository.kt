package ru.ktsstudio.wishlist.data.network.repository

import android.annotation.SuppressLint
import android.util.Log
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import ru.ktsstudio.wishlist.data.db.model.User
import ru.ktsstudio.wishlist.data.db.model.Wish
import ru.ktsstudio.wishlist.data.network.WishApiService
import ru.ktsstudio.wishlist.data.network.model.body.AddBody
import ru.ktsstudio.wishlist.data.network.model.body.LoginBody
import ru.ktsstudio.wishlist.data.network.model.body.RegisterBody
import ru.ktsstudio.wishlist.data.network.model.response.AuthResponse
import ru.ktsstudio.wishlist.data.network.model.response.WishAddResponse
import ru.ktsstudio.wishlist.data.prefs.ISharedPreferencesRepository
import toothpick.InjectConstructor

@InjectConstructor
class WishApiRepository(
    private val wishApiService: WishApiService,
    private val sharedPreferencesRepository: ISharedPreferencesRepository
) : IWishApiRepository {

    override fun login(email: String, password: String): Single<AuthResponse> {
        return wishApiService.login(
            LoginBody(
                email,
                password
            )
        )
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

    override fun register(
        firstName: String,
        lastName: String,
        email: String,
        password: String
    ): Single<AuthResponse> {
        return wishApiService.register(
            RegisterBody(
                firstName,
                lastName,
                email,
                password
            )
        )
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

    override fun addWish(title: String, description: String): Single<WishAddResponse> {
        return wishApiService.addWish(
            AddBody(
                title,
                description
            )
        )
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

    @SuppressLint("CheckResult")
    override fun loadWishesFromServer(insertCallback: (List<Wish>) -> Single<List<Long>>) {
        wishApiService.getWishList()
            .subscribeOn(Schedulers.io())
            .flatMap { response ->
                val items = response.data.map {
                    Wish(
                        id = it.title.hashCode().toLong() + it.description.hashCode() + it.userId.hashCode(),
                        title = it.title,
                        description = it.description,
                        author = User(sharedPreferencesRepository.getCurrentUserLogin()!!),
                        photoId = 0
                    )
                }
                insertCallback(items)
            }
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ count ->
                Log.d("loadWishes onSuccess", "Inserted count=$count")
            }, {
                Log.d("loadWishes onError", it.localizedMessage, it)
            }
            )
    }

}