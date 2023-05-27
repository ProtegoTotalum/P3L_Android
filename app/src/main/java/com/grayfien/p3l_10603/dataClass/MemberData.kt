package com.grayfien.p3l_10603.dataClass

import com.google.gson.annotations.SerializedName

data class MemberData(
    @SerializedName("id") val id: Int,
    @SerializedName("nomor_member") val nomor_member: String,
    @SerializedName("id_user") val id_user: Int,
    @SerializedName("nama_member") val nama_member: String,
    @SerializedName("email_member") val email_member: String,
    @SerializedName("nomor_telepon_member") val nomor_telepon_member:String,
    @SerializedName("tanggal_lahir_member") val tanggal_lahir_member:String,
    @SerializedName("alamat_member") val alamat_member:String,
    @SerializedName("sisa_deposit_reguler") val sisa_deposit_reguler:Int,
    @SerializedName("masa_berlaku_member") val masa_berlaku_member:String,
    @SerializedName("status_member") val status_member:String,
    @SerializedName("username_member") val username_member: String,
    @SerializedName("password_member") val password_member: String,

)
