package com.grayfien.p3l_10603.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.grayfien.p3l_10603.dataClass.JadwalHarianData
import com.grayfien.p3l_10603.databinding.JadwalHarianAdapterBinding


class JadwalHarianAdapter(val data: ArrayList<JadwalHarianData>) :
    RecyclerView.Adapter<JadwalHarianAdapter.JadwalHarianViewHolder>() {

    inner class JadwalHarianViewHolder(item: JadwalHarianAdapterBinding) : RecyclerView.ViewHolder(item.root) {
        private val binding = item
        fun bind(jadwalHarianData: JadwalHarianData) {
            with(binding) {

                textViewNamaKelas.text = jadwalHarianData.nama_kelas
                textViewHargaKelas.text = jadwalHarianData.harga_kelas
                textViewNamaInstruktur.text = jadwalHarianData.nama_instruktur
                textViewTanggal.text = jadwalHarianData.tanggal_jadwal_harian
                textViewHari.text = jadwalHarianData.hari
                textViewJam.text = jadwalHarianData.jam
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): JadwalHarianViewHolder {
        return JadwalHarianViewHolder(
            JadwalHarianAdapterBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: JadwalHarianViewHolder, position: Int) {
        holder.bind(data[position])
    }

    override fun getItemCount(): Int = data.size

}