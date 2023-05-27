package com.grayfien.p3l_10603.dataClass

import com.google.gson.annotations.SerializedName

data class JadwalHarianData(
    @SerializedName("id") val id: Int,
    @SerializedName("nama_instruktur") val nama_instruktur: String,
    @SerializedName("tanggal_jadwal_harian") val tanggal_jadwal_harian: String,
    @SerializedName("status_jadwal_harian") val status_jadwal_harian: String,
    @SerializedName("nama_kelas") val nama_kelas: String,
    @SerializedName("harga_kelas") val harga_kelas: String,
    @SerializedName("hari") val hari: String?,
    @SerializedName("jam") val jam: String?,
)
