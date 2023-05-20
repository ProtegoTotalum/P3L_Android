package com.grayfien.p3l_10603.dataClass

import com.google.gson.annotations.SerializedName

data class ResponseCreate(
    @SerializedName("status") val stt: Int,
    @SerializedName("error") val e: Boolean,
    @SerializedName("message") val pesan: String,
)
