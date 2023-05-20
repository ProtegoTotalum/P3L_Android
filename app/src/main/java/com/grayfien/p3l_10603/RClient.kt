package com.grayfien.p3l_10603

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RClient {
    val okHttpClient = OkHttpClient.Builder()
        .addInterceptor(HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        })
        .build()

        val retrofit = Retrofit.Builder()
            .baseUrl("http://192.168.18.9/P3L/P3L_BackEnd/public/")
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        var api = retrofit.create(api::class.java)
}
