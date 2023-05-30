package com.grayfien.p3l_10603.fragment

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.grayfien.p3l_10603.HomeActivity
import com.grayfien.p3l_10603.R
import com.grayfien.p3l_10603.RClient
import com.grayfien.p3l_10603.dataClass.InstrukturData
import com.grayfien.p3l_10603.dataClass.ResponseDataInstruktur
import com.grayfien.p3l_10603.databinding.FragmentInstrukturBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class FragmentInstruktur : Fragment() {
    private var _binding: FragmentInstrukturBinding? = null
    private val binding get() = _binding!!
    private var b: Bundle? = null
    private val listInstruktur = ArrayList<InstrukturData>()
    private var id_user_login: Int? = null



    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val activity: HomeActivity? = activity as HomeActivity?
        _binding = FragmentInstrukturBinding.inflate(inflater, container, false)

        id_user_login = activity?.getIdUserLogin() ?: 0

        id_user_login?.let{ getDataInstruktur(it)}
        Log.d("idUserOutsideFunIns", "Received id_user_login: $id_user_login")


//        binding.btnDetailDepositPaket.setOnClickListener {
//            val intent = Intent(requireActivity(), DepositKelasActivity::class.java)
//            intent.putExtra("id_member", id_user_login)
//            startActivity(intent)
//        }
//

        return binding.root
    }


    private fun getDataInstruktur(id:Int){

        val call = RClient.api.getDataInstruktur(id)
        call?.enqueue(object : Callback<ResponseDataInstruktur> {
            override fun onResponse(
                call: Call<ResponseDataInstruktur>,
                response: Response<ResponseDataInstruktur>
            ){
                Log.d("InstrukturResponse", "onResponse called")
                Log.d("InstrukturResponse", "Response code: ${response.code()}")
                Log.d("InstrukturResponse", "Response message: ${response.message()}")
                Log.d("InstrukturResponse", "Response body: ${response.body()}")
                if (response.isSuccessful) {
                    response.body()?.let {
                        if (it.stt) {
                            listInstruktur.clear()
                            listInstruktur.addAll(it.data)
                            with(binding) {
                                tvNamaInstruktur.setText(listInstruktur[0].nama_instruktur)
                                tvEmailInstruktur.setText(listInstruktur[0].email_instruktur)
                                tvNomorTeleponInstruktur.setText(listInstruktur[0].nomor_telepon_instruktur)
                                tvUsernameInstruktur.setText(listInstruktur[0].username_instruktur)
                                tvPasswordInstruktur.setText(listInstruktur[0].password_instruktur)
                                tvJumlahKeterlambatanInstruktur.setText(listInstruktur[0].jumlah_keterlambatan_instruktur.toString())
                            }
                        } else {
                            // Handle the case when the status is false
                            Log.e("InstrukturResponse", "API call unsuccessful: ${it.message}")
                        }
                    }
                } else {
                    // Handle the case when the API response is not successful
                    Log.e("InstrukturResponse", "API call failed with response code: ${response.code()}")
                }
            }
            override fun onFailure(call: Call<ResponseDataInstruktur>, t: Throwable) {
                Log.e("InstrukturResponse", "API call failed", t)
            }
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}