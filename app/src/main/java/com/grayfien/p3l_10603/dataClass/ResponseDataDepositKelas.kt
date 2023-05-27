package com.grayfien.p3l_10603.dataClass

import com.google.gson.annotations.SerializedName

data class ResponseDataDepositKelas(
    @SerializedName("success") val stt: Boolean,
    val message: String,
    val data: List<DepositKelasData>
)
