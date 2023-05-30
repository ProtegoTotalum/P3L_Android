package com.grayfien.p3l_10603

import com.grayfien.p3l_10603.dataClass.*
import retrofit2.Call
import retrofit2.http.*

interface api {
    @GET("api/user")
    open fun getDataUser(): Call<ResponseDataUser?>?

    @FormUrlEncoded
    @POST("api/login")
    fun login(
        @Field("username") username:String?,
        @Field("password") password:String?,
    ): Call<UserResponse>

    @GET("api/createpresensiinstruktur")
    open fun createPresensi(): Call<ResponseDataPresensi>


    @GET("api/getpresensi/{id}")
    fun getDataPresensi(@Path("id") id: Int): Call<ResponseDataPresensi>?

    @GET("api/getpresensi")
    open fun getDataPresensiAll(): Call<ResponseDataPresensi>?

    @FormUrlEncoded
    @PUT("presensi/{id}")
    fun updateDataPresensi(
        @Field("jam_mulai_kelas") jam_mulai_kelas: String?,
        @Field("jam_selesai_kelas") jam_selesai_kelas: String?,
        @Field("status_presensi") status_presensi: String?,
        @Path("id") id: Int?
    ): Call<ResponseDataPresensi>

    @GET("api/jammulai/{id}")
    open fun updateJamMulai(@Path("id") id: Int?): Call<ResponseCreate>

    @GET("api/jamselesai/{id}")
    open fun updateJamSelesai(@Path("id") id: Int?): Call<ResponseCreate>

    @GET("api/getjadwalharian")
    open fun getDataJadwalHarianAll(): Call<ResponseDataJadwalHarian>?

    @GET("api/getmember/{id}")
    fun getDataMember(@Path("id") id: Int): Call<ResponseDataMember>?

    @GET("api/getdepositkelas/{id}")
    fun getDataDepositKelas(@Path("id") id: Int?): Call<ResponseDataDepositKelas>?

    @GET("api/getinstruktur/{id}")
    fun getDataInstruktur(@Path("id") id: Int): Call<ResponseDataInstruktur>?
}