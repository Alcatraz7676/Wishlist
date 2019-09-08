package ru.ktsstudio.wishlist.data.db

import androidx.room.*
import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.Single
import ru.ktsstudio.wishlist.data.db.model.Wish
import ru.ktsstudio.wishlist.data.db.model.Wish.Companion.QUERY_ALL
import ru.ktsstudio.wishlist.data.db.model.Wish.Companion.QUERY_BY_ID

@Dao
interface WishDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(wishes: List<Wish>): Single<List<Long>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(wishes: Wish): Completable

    @Update
    fun update(wish: Wish): Completable

    @Query(QUERY_BY_ID)
    fun getWishById(id: Long): Single<Wish>

    @Query(QUERY_ALL)
    fun observeAll(): Observable<List<Wish>>



}