package com.grayfien.p3l_10603

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.KeyEvent
import android.view.View
import com.grayfien.p3l_10603.databinding.ActivityPresensiInstrukturBinding
import com.grayfien.p3l_10603.fragment.FragmentHome
import com.grayfien.p3l_10603.fragment.FragmentPresensiInstruktur

class PresensiInstrukturActivity : AppCompatActivity() {
    private lateinit var binding : ActivityPresensiInstrukturBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPresensiInstrukturBinding.inflate(layoutInflater)
        setContentView(binding.root)
        showDataFragment()

        binding.txtCari.setOnKeyListener(View.OnKeyListener{ _, keyCode, event->if(keyCode == KeyEvent.KEYCODE_ENTER && event.action == KeyEvent.ACTION_UP){
            showDataFragment()
            return@OnKeyListener true
        }
            false
        })

//        binding.btnAdd.setOnClickListener{
//            startActivity(Intent(this,AddPasienActivity::class.java))
//        }
        binding.btnCancel.setOnClickListener{
            startActivity(Intent(this, FragmentHome::class.java))
        }
    }

    fun showDataFragment (){
        val mFragmentManager = supportFragmentManager
        val mFragmentTransaction =
            mFragmentManager.beginTransaction()
        val mFragment = FragmentPresensiInstruktur()
        val textCari = binding.txtCari.text.toString()
        val mBundle = Bundle()
        val id = textCari.toIntOrNull() ?: 0
        mBundle.putInt("id", id)
        mFragment.arguments = mBundle
        mFragmentTransaction.replace(R.id.fl_data, mFragment).commit()
    }
}