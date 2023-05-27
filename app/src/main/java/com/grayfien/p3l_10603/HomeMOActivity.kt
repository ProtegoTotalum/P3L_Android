package com.grayfien.p3l_10603

import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import com.grayfien.p3l_10603.fragment.FragmentHome
import nl.joery.animatedbottombar.AnimatedBottomBar

class HomeMOActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home_moactivity)
        val id_user = intent.extras?.getInt("id_user_login",0)

        Log.d("idUserHomeMO", "Received id_user_login: $id_user")

        val firstFragment = FragmentHome()
//        val secondFragment = FragmentPasien()
//        val thirdFragment = FragmentUser()
//        val fourthFragment = FragmentObat()


        setCurrentFragment(firstFragment)


        val bottomNavigationView =
            findViewById<AnimatedBottomBar>(R.id.bottomNavigationView)

        bottomNavigationView.onTabSelected = {
            Log.d("bottomNavigationView", "Selected tab: " + it.title)
        }
        bottomNavigationView.onTabReselected = {
            Log.d("bottomNavigationView", "Reselected tab: " + it.title)
        }

        bottomNavigationView.setOnTabSelectListener(object : AnimatedBottomBar.OnTabSelectListener {
            override fun onTabSelected(
                lastIndex: Int,
                lastTab: AnimatedBottomBar.Tab?,
                newIndex: Int,
                newTab: AnimatedBottomBar.Tab
            ) {
                when(newTab.id) {
                    R.id.tab_home -> setCurrentFragment(firstFragment)

                    //R.id.tab_pasien -> setCurrentFragment(secondFragment)
                    R.id.tab_presensi_instruktur -> {
                        val intent = Intent(this@HomeMOActivity, PresensiInstrukturActivity::class.java)
                        startActivity(intent)
                    }

//                    R.id.tab_obat -> {
//                        val intent = Intent(this@HomeActivity, ObatActivity::class.java)
//                        startActivity(intent)
//                    }
//
//                    R.id.tab_supplier -> {
//                        val intent = Intent(this@HomeActivity, SupplierActivity::class.java)
//                        startActivity(intent)
//                    }
//
//                    R.id.tab_profil -> setCurrentFragment(thirdFragment)
//                    R.id.tab_profil -> {
//                        val intent = Intent(this@HomeActivity, UserActivity::class.java)
//                        startActivity(intent)
//                    }

                    R.id.logout -> {
                        val builder: AlertDialog.Builder = AlertDialog.Builder(this@HomeMOActivity)
                        builder.setMessage("Are you sure want to exit?")
                            .setPositiveButton("YES", object : DialogInterface.OnClickListener {
                                override fun onClick(dialogInterface: DialogInterface, i: Int) {

                                    //Keluar dari aplikasi
                                    finishAndRemoveTask()
                                }
                            })
                            .show()
                    }
                }
            }
        })
    }
    private fun setCurrentFragment(fragment: Fragment) =
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.layout_fragment, fragment)
            commit()
        }
}