package com.grayfien.p3l_10603

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import com.grayfien.p3l_10603.databinding.ActivityDepositKelasBinding
import com.grayfien.p3l_10603.fragment.FragmentDepositKelas
import com.grayfien.p3l_10603.fragment.FragmentMember


class DepositKelasActivity : AppCompatActivity() {
    private lateinit var binding : ActivityDepositKelasBinding
    private var id_member: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDepositKelasBinding.inflate(layoutInflater)
        setContentView(binding.root)
        showDataFragment()

        id_member = intent.extras!!.getInt("id_member",0)

        val btnBack: Button = findViewById(R.id.btnBackDeposit)


//        btnBack.setOnClickListener(View.OnClickListener {
//
//            val moveHome = Intent(
//                this@DepositKelasActivity, HomeMemberActivity::class.java
//            ).apply { putExtra("id_user_login",  id_member) }
//            startActivity(moveHome)
//        })

//        btnBack.setOnClickListener {
//            val fragmentManager = supportFragmentManager
//            val fragmentTransaction = fragmentManager.beginTransaction()
//
//            // Create an instance of your FragmentMember fragment
//            val fragmentMember = FragmentMember()
//            val mBundle = Bundle()
//            mBundle.putInt("id_member", id_member)
//            fragmentMember.arguments = mBundle
//            // Replace the fragment container with your FragmentMember
//            fragmentTransaction.replace(R.id.fragmentContainer, fragmentMember)
//            fragmentTransaction.commit()
//        }
    }

    fun showDataFragment (){
        id_member = intent.extras!!.getInt("id_member",0)

        Log.d("idMemberOnDepositActivity", "Received id_member: $id_member")

        val mFragmentManager = supportFragmentManager
        val mFragmentTransaction =
            mFragmentManager.beginTransaction()
        val mFragment = FragmentDepositKelas()
        val mBundle = Bundle()
        mBundle.putInt("id_member", id_member)
        mFragment.arguments = mBundle
        mFragmentTransaction.replace(R.id.fl_data_deposit, mFragment).commit()
    }
}