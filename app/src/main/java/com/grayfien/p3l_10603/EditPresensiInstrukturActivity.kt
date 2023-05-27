package com.grayfien.p3l_10603

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.aminography.primecalendar.civil.CivilCalendar
import com.aminography.primedatepicker.picker.PrimeDatePicker
import com.grayfien.p3l_10603.dataClass.PresensiData
import com.grayfien.p3l_10603.dataClass.ResponseDataPresensi
import com.grayfien.p3l_10603.databinding.ActivityEditPresensiInstrukturBinding
import com.shashank.sony.fancytoastlib.FancyToast
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.time.LocalDate
import java.time.format.DateTimeFormatter

class EditPresensiInstrukturActivity : AppCompatActivity() {
    private lateinit var binding: ActivityEditPresensiInstrukturBinding
    private var b: Bundle? = null
    private var listPresensi = java.util.ArrayList<PresensiData>()

    @SuppressLint("RestrictedApi")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEditPresensiInstrukturBinding.inflate(layoutInflater)

        setContentView(binding.root)
        /*supportActionBar?.setDefaultDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = "Form Edit Pasien"*/

        b = intent.extras
        val id_presensi = b?.getInt("id_presensi")


        id_presensi?.let { getDetailData(it) }

        binding.btnUpdatePresensi.setOnClickListener {
            with(binding) {
                val jam_mulai = editJamMasuk.text.toString()
                val jam_selesai = editJamSelesai.text.toString()
                val status_presensi = editStatusPresensi.text.toString()
                val call = RClient.api.updateDataPresensi(
                    jam_mulai,
                    jam_selesai,
                    status_presensi,
                    id_presensi
                )
                call.enqueue(object :
                    Callback<ResponseDataPresensi> {
                    override fun onResponse(
                        call: Call<ResponseDataPresensi>,
                        response: Response<ResponseDataPresensi>
                    ) {
                        Log.d("EditPresensiResponse", "onResponseDetail called")
                        Log.d("EditPresensiResponse", "Response code: ${response.code()}")
                        Log.d("EditPresensiResponse", "Response message: ${response.message()}")
                        Log.d("EditPresensiResponse", "Response body: ${response.body()}")
                        if (response.isSuccessful) {
                            FancyToast.makeText(
                                applicationContext,
                                "${response.body()?.message}",
                                FancyToast.LENGTH_LONG,
                                FancyToast.SUCCESS,
                                false
                            ).show()
                            finish()
                        }else{
                            Log.d("EditPresensiResponse", "Response is not successful")
                            FancyToast.makeText(
                                applicationContext,
                                "${response.body()?.message}",
                                FancyToast.LENGTH_LONG,
                                FancyToast.ERROR,
                                false
                            ).show()
                        }
                    }

                    override fun onFailure(call: Call<ResponseDataPresensi>, t: Throwable) {

                    }
                })
            }
        }
    }

    fun getDetailData(id_presensi: Int) {
        val call = RClient.api.getDataPresensi(id_presensi)
        call?.enqueue(object : Callback<ResponseDataPresensi> {
            override fun onResponse(
                call: Call<ResponseDataPresensi>,
                response: Response<ResponseDataPresensi>
            ) {
                if (response.isSuccessful) {
                    response.body()?.let { listPresensi.addAll(it.data) }
                    with(binding) {
                        editNamaInstruktur.setText(listPresensi[0].nama_instruktur)
                        editKelas.setText(listPresensi[0].nama_kelas)
                        editTglKelas.setText(listPresensi[0].tanggal_jadwal_harian)
                        editJamMasuk.setText(listPresensi[0].jam_mulai_kelas)
                        editJamSelesai.setText(listPresensi[0].jam_selesai_kelas)
                        editStatusPresensi.setText(listPresensi[0].status_presensi)
                    }
                }
            }

            override fun onFailure(call: Call<ResponseDataPresensi>, t: Throwable) {

            }
        })
    }
}