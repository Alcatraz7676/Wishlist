package ru.ktsstudio.wishlist.data.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.Single
import ru.ktsstudio.wishlist.data.models.WishAdapterModel
import ru.ktsstudio.wishlist.data.models.WishAdapterModel.Wish

@Dao
interface WishDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(wishes: List<Wish>): Single<List<Long>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(wishes: Wish): Completable

    @Query(WishAdapterModel.QUERY_ALL)
    fun observeAll(): Observable<List<Wish>>

}