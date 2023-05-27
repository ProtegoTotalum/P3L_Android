package com.grayfien.p3l_10603

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.gson.GsonBuilder
import com.grayfien.p3l_10603.adapter.JadwalHarianAdapter
import com.grayfien.p3l_10603.adapter.PresensiInstrukturAdapter
import com.grayfien.p3l_10603.dataClass.JadwalHarianData
import com.grayfien.p3l_10603.dataClass.PresensiData
import com.grayfien.p3l_10603.dataClass.ResponseDataJadwalHarian
import com.grayfien.p3l_10603.dataClass.ResponseDataPresensi
import com.grayfien.p3l_10603.databinding.ActivityMainPageBinding
import com.grayfien.p3l_10603.databinding.ActivityPresensiInstrukturBinding
import com.grayfien.p3l_10603.fragment.FragmentJadwalHarian
import com.grayfien.p3l_10603.fragment.FragmentPresensiInstruktur
import com.shashank.sony.fancytoastlib.FancyToast
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.text.SimpleDateFormat
import java.util.*

class MainPageActivity : AppCompatActivity() {
    private lateinit var binding : ActivityMainPageBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainPageBinding.inflate(layoutInflater)
        setContentView(binding.root)
        showDataFragment()


        val btnLogin: Button = findViewById(R.id.btnLoginMain)

//        binding = ActivityMainPageBinding.inflate(layoutInflater)
//        setContentView(binding?.root)

        btnLogin.setOnClickListener(View.OnClickListener {

            val moveHome = Intent(
                this@MainPageActivity, MainActivity::class.java
            )
            startActivity(moveHome)
        })
    }

    fun showDataFragment (){
        val mFragmentManager = supportFragmentManager
        val mFragmentTransaction =
            mFragmentManager.beginTransaction()
        val mFragment = FragmentJadwalHarian()
//        val mBundle = Bundle()
//        mBundle.putInt("id", id)
//        mFragment.arguments = mBundle
        mFragmentTransaction.replace(R.id.fl_data_jadwal, mFragment).commit()
    }
}