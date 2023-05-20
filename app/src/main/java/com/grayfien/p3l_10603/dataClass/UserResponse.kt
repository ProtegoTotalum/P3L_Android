package com.grayfien.p3l_10603.dataClass

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class UserResponse {
    @SerializedName("message")
    val message: String? = null

    @SerializedName("user")
    val user: User? = null

    @SerializedName("token_type")
    val token: String? = null

    @SerializedName("access_token")
    val access: String? = null

    class User {
        @SerializedName("id")
        @Expose
        var id: Int? = null

        @SerializedName("id_user_login")
        @Expose
        var id_user_login: Int? = null

        @SerializedName("name")
        @Expose
        var name: String? = null

        @SerializedName("email")
        @Expose
        var email: String? = null

        @SerializedName("username")
        @Expose
        var username: String? = null

        @SerializedName("password")
        @Expose
        var password: String? = null

        @SerializedName("role")
        @Expose
        var role: String? = null

    }
}