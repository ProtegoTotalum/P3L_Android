package com.grayfien.p3l_10603

import com.grayfien.p3l_10603.dataClass.ResponseCreate
import com.grayfien.p3l_10603.dataClass.ResponseDataUser
import com.grayfien.p3l_10603.dataClass.UserResponse
import retrofit2.Call
import retrofit2.http.*

interface api {
    @GET("p3l/p3l_backend/public/api/user")
    open fun getDataUser(): Call<ResponseDataUser?>?

    @FormUrlEncoded
    @POST("api/login")
    fun login(
        @Field("username") username:String?,
        @Field("password") password:String?,
    ): Call<UserResponse>
}