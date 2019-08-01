package ru.ktsstudio.wishlist.data.network

import io.reactivex.Single
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import ru.ktsstudio.wishlist.data.models.body.AddBody
import ru.ktsstudio.wishlist.data.models.response.AuthResponse
import ru.ktsstudio.wishlist.data.models.body.LoginBody
import ru.ktsstudio.wishlist.data.models.body.RegisterBody
import ru.ktsstudio.wishlist.data.models.response.WishListResponse
import ru.ktsstudio.wishlist.data.models.response.WishResponse

interface WishApiService {

    @POST("/api/user/auth")
    fun login(
        @Body body: LoginBody
    ): Single<AuthResponse>

    @POST("/api/user/register")
    fun register(
        @Body body: RegisterBody
    ): Single<AuthResponse>

    @GET("/api/wish/list")
    fun getWishList(): Single<WishListResponse>

    @POST("/api/wish/add")
    fun addWish(
        @Body body: AddBody
    ): Single<WishResponse>

}