package com.grayfien.p3l_10603.fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.gson.*
import com.grayfien.p3l_10603.R
import java.text.SimpleDateFormat
import com.grayfien.p3l_10603.RClient
import com.grayfien.p3l_10603.adapter.PresensiInstrukturAdapter
import com.grayfien.p3l_10603.dataClass.PresensiData
import com.grayfien.p3l_10603.dataClass.ResponseDataPresensi
import com.grayfien.p3l_10603.databinding.FragmentPresensiInstrukturBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.lang.reflect.Type
import java.util.*
import kotlin.collections.ArrayList


class FragmentPresensiInstruktur : Fragment() {
    private var _binding: FragmentPresensiInstrukturBinding? = null
    private val binding get() = _binding!!
    private val listPresensi = ArrayList<PresensiData>()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentPresensiInstrukturBinding.inflate(inflater, container,false)
        return binding.root
        getDataPresensiInstruktur()
    }


    override fun onStart() {
        super.onStart()
        getDataPresensiInstruktur()
    }

    private fun getDataPresensiInstruktur(){
        binding.rvData.setHasFixedSize(true)
        binding.rvData.layoutManager = LinearLayoutManager(context)
        val bundle = arguments
        val id = bundle?.getInt("id")
        binding.progressBar.visibility
        val gson = GsonBuilder()
            .registerTypeAdapter(PresensiData::class.java, DateDeserializer())
            .create()

        val dateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())

        val call = RClient.api.getDataPresensiAll()
        call?.enqueue(object : Callback<ResponseDataPresensi> {
            override fun onResponse(
                call: Call<ResponseDataPresensi>,
                response: Response<ResponseDataPresensi>
            ) {
                Log.d("PresensiResponse", "onResponse called")
                Log.d("PresensiResponse", "Response code: ${response.code()}")
                Log.d("PresensiResponse", "Response message: ${response.message()}")
                Log.d("PresensiResponse", "Response body: ${response.body()}")
                if(response.isSuccessful){
                    listPresensi.clear()
                    response.body()?.let { responseData ->
                        responseData.data.forEach { dataItem ->
                            listPresensi.add(dataItem)
                        }
//                        listPresensi.addAll(responseData.data)
                    }
                    val adapter = PresensiInstrukturAdapter (listPresensi, requireContext())
                    binding.rvData.adapter = adapter
                    adapter.notifyDataSetChanged()
                    binding.progressBar.isVisible = false
                }else{

                }
            }

            override fun onFailure(call: Call<ResponseDataPresensi>, t: Throwable) {
                Log.e("PresensiResponse", "API call failed", t)
            }
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    class DateDeserializer : JsonDeserializer<Date> {
        override fun deserialize(
            json: JsonElement?,
            typeOfT: Type?,
            context: JsonDeserializationContext?
        ): Date {
            val dateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
            val dateString = json?.asString
            return try {
                dateFormat.parse(dateString)
            } catch (e: Exception) {
                throw JsonParseException("Failed to parse date: $dateString", e)
            }
        }
    }


}