package com.grayfien.p3l_10603

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log

class HomeActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        val id_user = intent.extras?.getInt("id_user_login",0)

        Log.d("idUser", "Received id_user_login: $id_user")
    }
}