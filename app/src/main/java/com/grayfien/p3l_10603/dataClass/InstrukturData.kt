package com.grayfien.p3l_10603.dataClass

import com.google.gson.annotations.SerializedName
import java.sql.Time

data class InstrukturData(
    @SerializedName("id") val id: Int,
    @SerializedName("id_user") val id_user: Int,
    @SerializedName("nama_instruktur") val nama_instruktur: String,
    @SerializedName("email_instruktur") val email_instruktur: String,
    @SerializedName("nomor_telepon_instruktur") val nomor_telepon_instruktur:String,
    @SerializedName("username_instruktur") val username_instruktur: String,
    @SerializedName("password_instruktur") val password_instruktur: String,
    @SerializedName("jumlah_keterlambatan_instruktur") val jumlah_keterlambatan_instruktur: Int,
)
