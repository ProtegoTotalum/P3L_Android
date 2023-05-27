package com.grayfien.p3l_10603.fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.gson.GsonBuilder
import com.grayfien.p3l_10603.R
import com.grayfien.p3l_10603.RClient
import com.grayfien.p3l_10603.adapter.JadwalHarianAdapter
import com.grayfien.p3l_10603.dataClass.JadwalHarianData
import com.grayfien.p3l_10603.dataClass.ResponseDataJadwalHarian
import com.grayfien.p3l_10603.databinding.FragmentJadwalHarianBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList


class FragmentJadwalHarian : Fragment() {
    private var _binding: FragmentJadwalHarianBinding? = null
    private val binding get() = _binding!!
    private val listJadwal = ArrayList<JadwalHarianData>()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentJadwalHarianBinding.inflate(inflater, container,false)
        return binding.root
        getDataJadwalHarian()
    }


    override fun onStart() {
        super.onStart()
        getDataJadwalHarian()
    }
    private fun getDataJadwalHarian(){
        binding.rvDataJadwal.setHasFixedSize(true)
        binding.rvDataJadwal.layoutManager = LinearLayoutManager(context)
        val bundle = arguments
        val id = bundle?.getInt("id")
        binding.progressBar.visibility

        val call = RClient.api.getDataJadwalHarianAll()
        call?.enqueue(object : Callback<ResponseDataJadwalHarian> {
            override fun onResponse(
                call: Call<ResponseDataJadwalHarian>,
                response: Response<ResponseDataJadwalHarian>
            ) {
                Log.d("JadwalResponse", "onResponse called")
                Log.d("JadwalResponse", "Response code: ${response.code()}")
                Log.d("JadwalResponse", "Response message: ${response.message()}")
                Log.d("JadwalResponse", "Response body: ${response.body()}")
                if(response.isSuccessful){
                    listJadwal.clear()
                    response.body()?.let { responseData ->
                        responseData.data.forEach { dataItem ->
                            listJadwal.add(dataItem)
                        }
//                        listPresensi.addAll(responseData.data)
                    }
                    val adapter = JadwalHarianAdapter(listJadwal)
                    binding.rvDataJadwal.adapter = adapter
                    adapter.notifyDataSetChanged()
                    binding.progressBar.isVisible = false
                }else{

                }
            }

            override fun onFailure(call: Call<ResponseDataJadwalHarian>, t: Throwable) {
                Log.e("JadwalResponse", "API call failed", t)
            }
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}