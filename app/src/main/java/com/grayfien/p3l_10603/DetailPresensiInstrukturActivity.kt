package com.grayfien.p3l_10603

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AlertDialog
import com.grayfien.p3l_10603.dataClass.PresensiData
import com.grayfien.p3l_10603.dataClass.ResponseCreate
import com.grayfien.p3l_10603.dataClass.ResponseDataPresensi
import com.grayfien.p3l_10603.databinding.ActivityDetailPresensiInstrukturBinding
import com.grayfien.p3l_10603.fragment.FragmentPresensiInstruktur
import com.shashank.sony.fancytoastlib.FancyToast
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.text.SimpleDateFormat

class DetailPresensiInstrukturActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailPresensiInstrukturBinding
    private var b: Bundle? = null
    private val listPresensi = ArrayList<PresensiData>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailPresensiInstrukturBinding.inflate(layoutInflater)
        setContentView(binding.root)

        b = intent.extras
        val id_presensi = b?.getInt("id_presensi",0)
        Log.d("idPresensi", "Received id_presensi: $id_presensi")

        id_presensi?.let { getDataDetail(it) }

        binding.btnCancelPresensi.setOnClickListener {
            startActivity(Intent(this, FragmentPresensiInstruktur::class.java))
        }
        binding.btnEditJamMulai.setOnClickListener {
            val call = RClient.api.updateJamMulai(id_presensi)
            call.enqueue(object : Callback<ResponseCreate> {
                override fun onResponse(
                    call: Call<ResponseCreate>,
                    response: Response<ResponseCreate>
                ) {
                    Log.d("PresensiResponse3", "onResponseDetail called")
                    Log.d("PresensiResponse3", "Response code: ${response.code()}")
                    Log.d("PresensiResponse3", "Response message: ${response.message()}")
                    Log.d("PresensiResponse3", "Response body: ${response.body()}")
                    if (response.isSuccessful) {
                        FancyToast.makeText(
                            applicationContext,
                            "${response.body()?.pesan}",
                            FancyToast.LENGTH_LONG,
                            FancyToast.SUCCESS,
                            false
                        ).show()
                        recreate()
                    }else{
                        Log.d("PresensiResponse3", "Response body: ${response.body()?.toString()}")
                        Log.d("PresensiResponse3", "Response is not successful")
                        FancyToast.makeText(
                            this@DetailPresensiInstrukturActivity,
                            "${response.body()?.pesan}",
                            FancyToast.LENGTH_LONG,
                            FancyToast.ERROR,
                            false
                        ).show()
                    }
                }

                override fun onFailure(call: Call<ResponseCreate>, t: Throwable) {
                    Log.e("PresensiResponse3", "API request failed", t)
                }
            })
        }

        binding.btnEditJamSelesai.setOnClickListener {
            val call = RClient.api.updateJamSelesai(id_presensi)
            call.enqueue(object : Callback<ResponseCreate> {
                override fun onResponse(
                    call: Call<ResponseCreate>,
                    response: Response<ResponseCreate>
                ) {
                    Log.d("PresensiResponse4", "onResponseDetail called")
                    Log.d("PresensiResponse4", "Response code: ${response.code()}")
                    Log.d("PresensiResponse4", "Response message: ${response.message()}")
                    Log.d("PresensiResponse4", "Response body: ${response.body()}")
                    if (response.isSuccessful) {
                        FancyToast.makeText(
                            applicationContext,
                            "${response.body()?.pesan}",
                            FancyToast.LENGTH_LONG,
                            FancyToast.SUCCESS,
                            false
                        ).show()
                        recreate()
                    }else{
                        Log.d("PresensiResponse4", "Response body: ${response.body()?.toString()}")
                        Log.d("PresensiResponse4", "Response is not successful")
                        FancyToast.makeText(
                            applicationContext,
                            "${response.body()?.pesan}",
                            FancyToast.LENGTH_LONG,
                            FancyToast.ERROR,
                            false
                        ).show()
                    }
                }

                override fun onFailure(call: Call<ResponseCreate>, t: Throwable) {
                    Log.e("PresensiResponse4", "API request failed", t)
                }
            })
        }
    }

    fun getDataDetail(id_presensi: Int) {

        val call = RClient.api.getDataPresensi(id_presensi)
        call?.enqueue(object : Callback<ResponseDataPresensi> {
            override fun onResponse(
                call: Call<ResponseDataPresensi>,
                response: Response<ResponseDataPresensi>
            ) {
                Log.d("PresensiResponse2", "onResponseDetail called")
                Log.d("PresensiResponse2", "Response code: ${response.code()}")
                Log.d("PresensiResponse2", "Response message: ${response.message()}")
                Log.d("PresensiResponse2", "Response body: ${response.body()}")
                if (response.isSuccessful) {
                    response.body()?.let {
                        listPresensi.clear() // Clear the list before adding new data
                        listPresensi.addAll(it.data)
                        Log.d("PresensiData", listPresensi.toString())
                        Log.d("ListPresensi", "List Content: $listPresensi")
                        updateUIWithPresensiData()
                    }
//                    with(binding) {
//                        tvIdPresensi.text = listPresensi[0].id_presensi.toString()
//                        tvNamaInstruktur.text = listPresensi[0].nama_instruktur
//                        tvNamaKelas.text = listPresensi[0].nama_kelas
////                        val dateFormat = SimpleDateFormat("yyyy-MM-dd") // Format the date as desired
////                        val date = listPresensi[0].tanggal_jadwal_harian // Assuming it's a Date object
////                        val dateString = dateFormat.format(date) // Convert Date to String
//                        tvTglJadwalHarian.text = listPresensi[0].tanggal_jadwal_harian
////                        val timeFormat = SimpleDateFormat("HH:mm:ss") // Format the time as desired
////                        val timemulai = listPresensi[0].jam_mulai_kelas // Assuming it's a Time object
////                        val timeMulaiString = timeFormat.format(timemulai) // Convert Time to String
//                        tvJamMulai.text = listPresensi[0].jam_mulai_kelas?: ""
////                        val timeselesai = listPresensi[0].jam_selesai_kelas // Assuming it's a Time object
////                        val timeSelesaiString = timeFormat.format(timeselesai) // Convert Time to String
//                        tvJamSelesai.text = listPresensi[0].jam_selesai_kelas?: ""
//                        tvStatusPresensi.text = listPresensi[0].status_presensi?: ""
//                    }
                }else{
                    Log.d("PresensiResponse", "Response is not successful")
                }
            }

            override fun onFailure(call: Call<ResponseDataPresensi>, t: Throwable) {

            }
        })
    }

    override fun onRestart() {
        super.onRestart()
        this.recreate()
    }

    private fun updateUIWithPresensiData() {
        if (listPresensi.isNotEmpty()) {
            with(binding) {
                tvIdPresensi.text = listPresensi[0].id_presensi.toString()
                tvNamaInstruktur.text = listPresensi[0].nama_instruktur
                tvNamaKelas.text = listPresensi[0].nama_kelas
                tvTglJadwalHarian.text = listPresensi[0].tanggal_jadwal_harian
                tvJamMulai.text = listPresensi[0].jam_mulai_kelas?: ""
                tvJamSelesai.text = listPresensi[0].jam_selesai_kelas?: ""
                tvStatusPresensi.text = listPresensi[0].status_presensi?: ""
            }
        }else{
            FancyToast.makeText(
                this@DetailPresensiInstrukturActivity, "Data Kosong!", FancyToast.LENGTH_SHORT, FancyToast.SUCCESS, false
            ).show()
        }
    }
}