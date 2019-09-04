package ru.ktsstudio.wishlist.data.db.repository

import androidx.room.Transaction
import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import ru.ktsstudio.wishlist.data.db.WishDao
import ru.ktsstudio.wishlist.data.db.model.Wish
import ru.ktsstudio.wishlist.data.prefs.SharedPreferenceRepository
import ru.ktsstudio.wishlist.data.network.repository.WishApiRepository
import toothpick.InjectConstructor

@InjectConstructor
class WishRepository(
    private val wishDao: WishDao,
    private val sharedPreferenceRepository: SharedPreferenceRepository,
    private val wishApiRepository: WishApiRepository
) : IWishRepository {

    override fun insertWishes(wishes: List<Wish>): Single<List<Long>> {
        return wishDao.insertAll(wishes)
    }

    override fun observeFavouriteWishes(): Observable<List<Wish>> {
        return wishDao
            .observeAll()
            .map {
                it.filter { item -> item.isFavourite }
            }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

    override fun observeMyWishes(): Observable<List<Wish>> {
        val currentUser = sharedPreferenceRepository.getCurrentUserLogin()
        return wishDao
            .observeAll()
            .map {
                it.filter { item ->
                    item.author.login != Wish.UNKNOWN_USER && item.author.login == currentUser
                }
            }
            .doOnSubscribe {
                wishApiRepository.loadWishesFromServer { wishes -> insertWishes(wishes) }
            }
    }

    override fun getWishById(id: Long): Single<Wish> {
        return wishDao.getWishById(id)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

    override fun observePopularWishes(): Observable<List<Wish>> {
        return wishDao
            .observeAll()
            .map {
                it.filter { item ->
                    item.author.login != sharedPreferenceRepository.getCurrentUserLogin()
                }
            }
    }

    @Transaction
    override fun changeWishFavouriteStatus(wishId: Long, favourite: Boolean): Completable {
        return wishDao.getWishById(wishId)
            .flatMapCompletable {
                wishDao.update(it.copy(isFavourite = favourite))
            }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

}