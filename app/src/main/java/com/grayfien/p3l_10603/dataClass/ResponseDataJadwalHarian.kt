package com.grayfien.p3l_10603.dataClass

import com.google.gson.annotations.SerializedName

data class ResponseDataJadwalHarian(
    @SerializedName("success") val stt: Boolean,
    val message: String,
    val data: List<JadwalHarianData>
)
