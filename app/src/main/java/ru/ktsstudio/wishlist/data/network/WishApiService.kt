package ru.ktsstudio.wishlist.data.network

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import ru.ktsstudio.wishlist.data.models.response.AuthResponse
import ru.ktsstudio.wishlist.data.models.body.LoginBody
import ru.ktsstudio.wishlist.data.models.body.RegisterBody
import ru.ktsstudio.wishlist.data.models.response.WishListResponse

interface WishApiService {

    @POST("/api/user/auth")
    fun login(
        @Body body: LoginBody
    ): Call<AuthResponse>

    @POST("/api/user/register")
    fun register(
        @Body body: RegisterBody
    ): Call<AuthResponse>

    @GET("/api/wish/list")
    fun getWishList(): Call<WishListResponse>

}