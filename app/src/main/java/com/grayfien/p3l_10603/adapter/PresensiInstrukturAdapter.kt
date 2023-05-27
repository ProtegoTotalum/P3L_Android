package com.grayfien.p3l_10603.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.grayfien.p3l_10603.DetailPresensiInstrukturActivity
import com.grayfien.p3l_10603.dataClass.PresensiData
import com.grayfien.p3l_10603.databinding.PresensiInstrukturAdapterBinding

class PresensiInstrukturAdapter(private val data: ArrayList<PresensiData>, private val context: Context) :
    RecyclerView.Adapter<PresensiInstrukturAdapter.PresensiInstrukturViewHolder>() {

    inner class PresensiInstrukturViewHolder(item: PresensiInstrukturAdapterBinding) : RecyclerView.ViewHolder(item.root) {
        private val binding = item
        fun bind(presensiData: PresensiData) {
            with(binding) {

                txtNama.text = presensiData.nama_instruktur
                txtTanggal.text = presensiData.tanggal_jadwal_harian
                val idPresensi = presensiData.id_presensi
                cvData.setOnClickListener {
                    var i = Intent(
                        context,
                        DetailPresensiInstrukturActivity::class.java
                    ).apply {
                        putExtra("id_presensi", idPresensi)
                    }
                    context.startActivity(i)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PresensiInstrukturViewHolder {
        return PresensiInstrukturViewHolder(
            PresensiInstrukturAdapterBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: PresensiInstrukturViewHolder, position: Int) {
        holder.bind(data[position])
    }

    override fun getItemCount(): Int = data.size

}