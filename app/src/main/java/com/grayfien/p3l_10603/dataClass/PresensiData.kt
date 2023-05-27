package com.grayfien.p3l_10603.dataClass


import com.google.gson.annotations.SerializedName
import java.sql.Date
import java.sql.Time


data class PresensiData(
    @SerializedName("id_presensi") val id_presensi: Int,
    @SerializedName("id_instruktur") val id_instruktur: Int,
    @SerializedName("nama_instruktur") val nama_instruktur: String,
    @SerializedName("id_jadwal_harian") val id_jadwal_harian: Int,
    @SerializedName("tanggal_jadwal_harian") val tanggal_jadwal_harian: String,
    @SerializedName("nama_kelas") val nama_kelas: String,
    @SerializedName("jam_mulai") val jam_mulai_kelas: String?,
    @SerializedName("jam_selesai") val jam_selesai_kelas: String?,
    @SerializedName("status_presensi") val status_presensi: String?,
)
