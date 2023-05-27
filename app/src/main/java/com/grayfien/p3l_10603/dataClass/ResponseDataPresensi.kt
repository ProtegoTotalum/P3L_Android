package com.grayfien.p3l_10603.dataClass

import com.google.gson.annotations.SerializedName

data class ResponseDataPresensi(
    @SerializedName("success") val stt: Boolean,
    val message: String,
    val data: List<PresensiData>
)
