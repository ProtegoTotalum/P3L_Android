package com.grayfien.p3l_10603.fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import com.grayfien.p3l_10603.R
import com.grayfien.p3l_10603.RClient
import com.grayfien.p3l_10603.adapter.DepositKelasAdapter
import com.grayfien.p3l_10603.dataClass.DepositKelasData
import com.grayfien.p3l_10603.dataClass.ResponseDataDepositKelas
import com.grayfien.p3l_10603.databinding.FragmentDepositKelasBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class FragmentDepositKelas : Fragment() {
    private var _binding: FragmentDepositKelasBinding? = null
    private val binding get() = _binding!!
    private val listDeposit = ArrayList<DepositKelasData>()
    private var id_member: Int? = null


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentDepositKelasBinding.inflate(inflater, container,false)
        return binding.root
        id_member = arguments?.getInt("id_member")

        id_member?.let{ getDataDepositKelas(it)}
        Log.d("DepositResponse", "Received id_member: $id_member")

    }


    override fun onStart() {
        super.onStart()
        id_member = arguments?.getInt("id_member")
        id_member?.let{ getDataDepositKelas(it)}
        getDataDepositKelas(id_member)
    }
    private fun getDataDepositKelas(id : Int?){
        binding.rvDataDeposit.setHasFixedSize(true)
        binding.rvDataDeposit.layoutManager = LinearLayoutManager(context)
        val bundle = arguments
//        val id = bundle?.getInt("id")
        binding.progressBar.visibility

        val call = RClient.api.getDataDepositKelas(id)
        call?.enqueue(object : Callback<ResponseDataDepositKelas> {
            override fun onResponse(
                call: Call<ResponseDataDepositKelas>,
                response: Response<ResponseDataDepositKelas>
            ) {
                Log.d("DepositResponse", "onResponse called")
                Log.d("DepositResponse", "Response code: ${response.code()}")
                Log.d("DepositResponse", "Response message: ${response.message()}")
                Log.d("DepositResponse", "Response body: ${response.body()}")
                if(response.isSuccessful){
                    listDeposit.clear()
                    response.body()?.let { responseData ->
                        responseData.data.forEach { dataItem ->
                            listDeposit.add(dataItem)
                        }
//                        listPresensi.addAll(responseData.data)
                    }
                    val adapter = DepositKelasAdapter(listDeposit)
                    binding.rvDataDeposit.adapter = adapter
                    adapter.notifyDataSetChanged()
                    binding.progressBar.isVisible = false
                }else{

                }
            }

            override fun onFailure(call: Call<ResponseDataDepositKelas>, t: Throwable) {
                Log.e("JadwalResponse", "API call failed", t)
            }
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}