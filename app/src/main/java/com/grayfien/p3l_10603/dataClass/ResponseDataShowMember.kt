package com.grayfien.p3l_10603.dataClass

import com.google.gson.annotations.SerializedName

data class ResponseDataShowMember(
    val message: String,
    val data: List<MemberData>,
    val dataUser: List<UserData>
)
