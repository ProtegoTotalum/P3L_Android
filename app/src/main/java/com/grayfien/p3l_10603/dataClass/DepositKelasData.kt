package com.grayfien.p3l_10603.dataClass

import com.google.gson.annotations.SerializedName

data class DepositKelasData(
    @SerializedName("id_deposit_kelas") val id_deposit_kelas: Int,
    @SerializedName("id_member") val id_member: Int,
    @SerializedName("id_kelas") val id_kelas: Int,
    @SerializedName("nama_member") val nama_member: String,
    @SerializedName("nama_kelas") val nama_kelas: String,
    @SerializedName("sisa_deposit_kelas") val sisa_deposit_kelas: Int?,
    @SerializedName("masa_berlaku_deposit_kelas") val masa_berlaku_deposit_kelas: String?,
)
