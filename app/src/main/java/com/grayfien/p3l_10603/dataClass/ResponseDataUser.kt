package com.grayfien.p3l_10603.dataClass

import com.google.gson.annotations.SerializedName

data class ResponseDataUser(
    @SerializedName("status") val stt: String,
    val message: String,
    val data: List<UserData>
)
