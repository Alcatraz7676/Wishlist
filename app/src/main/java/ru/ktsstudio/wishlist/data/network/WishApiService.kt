package ru.ktsstudio.wishlist.data.network

import io.reactivex.Single
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import ru.ktsstudio.wishlist.data.network.model.body.AddBody
import ru.ktsstudio.wishlist.data.network.model.response.AuthResponse
import ru.ktsstudio.wishlist.data.network.model.body.LoginBody
import ru.ktsstudio.wishlist.data.network.model.body.RegisterBody
import ru.ktsstudio.wishlist.data.network.model.response.WishListResponse
import ru.ktsstudio.wishlist.data.network.model.response.WishAddResponse

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
    ): Single<WishAddResponse>

}