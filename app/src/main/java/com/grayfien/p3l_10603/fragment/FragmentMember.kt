package com.grayfien.p3l_10603.fragment

import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.aminography.primecalendar.civil.CivilCalendar
import com.aminography.primedatepicker.picker.PrimeDatePicker
import com.grayfien.p3l_10603.DepositKelasActivity
import com.grayfien.p3l_10603.HomeMemberActivity
import com.grayfien.p3l_10603.R
import com.grayfien.p3l_10603.RClient
import com.grayfien.p3l_10603.dataClass.MemberData
import com.grayfien.p3l_10603.dataClass.ResponseDataMember
import com.grayfien.p3l_10603.dataClass.ResponseDataShowMember
import com.grayfien.p3l_10603.databinding.FragmentJadwalHarianBinding
import com.grayfien.p3l_10603.databinding.FragmentMemberBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.time.LocalDate
import java.time.format.DateTimeFormatter

class FragmentMember : Fragment() {

    private var _binding: FragmentMemberBinding? = null
    private val binding get() = _binding!!
    private var b: Bundle? = null
    private val listMember = ArrayList<MemberData>()
    private var id_user_login: Int? = null



    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val activity:HomeMemberActivity? = activity as HomeMemberActivity?
        _binding = FragmentMemberBinding.inflate(inflater, container, false)

        id_user_login = activity?.getIdUserLogin() ?: 0

        id_user_login?.let{ getDataMember(it)}
        Log.d("idUserOutsideFun", "Received id_user_login: $id_user_login")


        binding.btnDetailDepositPaket.setOnClickListener {
            val intent = Intent(requireActivity(), DepositKelasActivity::class.java)
            intent.putExtra("id_member", id_user_login)
            startActivity(intent)
        }
//
//        binding.btnImage.setOnClickListener {
//            val intent = Intent(requireActivity(), CameraActivity::class.java)
//            startActivity(intent)
//        }

        return binding.root
    }


    private fun getDataMember(id:Int){
//        val id_user_login = b?.getInt("id")
//        id_user_login?.let{ getDataMember(it)}
//
//        Log.d("idUserInsideFun", "Received id_user_login: $id_user_login")

        val call = RClient.api.getDataMember(id)
        call?.enqueue(object : Callback<ResponseDataMember> {
            override fun onResponse(
                call: Call<ResponseDataMember>,
                response: Response<ResponseDataMember>
            ){
                Log.d("MemberResponse", "onResponse called")
                Log.d("MemberResponse", "Response code: ${response.code()}")
                Log.d("MemberResponse", "Response message: ${response.message()}")
                Log.d("MemberResponse", "Response body: ${response.body()}")
                if (response.isSuccessful) {
                    response.body()?.let {
                        if (it.stt) {
                            listMember.clear()
                            listMember.addAll(it.data)
                            with(binding) {
                                tvNomorMember.setText(listMember[0].nomor_member)
                                tvNamaMember.setText(listMember[0].nama_member)
                                tvEmailMember.setText(listMember[0].email_member)
                                tvNomorTeleponMember.setText(listMember[0].nomor_telepon_member)
                                tvTanggalLahirMember.setText(listMember[0].tanggal_lahir_member)
                                tvAlamatMember.setText(listMember[0].alamat_member)
                                tvSisaDepositReguler.setText(listMember[0].sisa_deposit_reguler.toString())
                                tvMasaBerlakuMember.setText(listMember[0].masa_berlaku_member)
                            }
                        } else {
                            // Handle the case when the status is false
                            Log.e("MemberResponse", "API call unsuccessful: ${it.message}")
                        }
                    }
                } else {
                    // Handle the case when the API response is not successful
                    Log.e("MemberResponse", "API call failed with response code: ${response.code()}")
                }
            }
            override fun onFailure(call: Call<ResponseDataMember>, t: Throwable) {
                Log.e("MemberResponse", "API call failed", t)
            }
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}