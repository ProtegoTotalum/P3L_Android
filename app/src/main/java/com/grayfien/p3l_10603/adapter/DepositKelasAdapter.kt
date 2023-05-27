package com.grayfien.p3l_10603.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.grayfien.p3l_10603.dataClass.DepositKelasData
import com.grayfien.p3l_10603.databinding.DepositKelasAdapterBinding


class DepositKelasAdapter(val data: ArrayList<DepositKelasData>) :
    RecyclerView.Adapter<DepositKelasAdapter.DepositKelasViewHolder>() {

    inner class DepositKelasViewHolder(item: DepositKelasAdapterBinding) : RecyclerView.ViewHolder(item.root) {
        private val binding = item
        fun bind(depositKelasData: DepositKelasData) {
            with(binding) {

                textViewNamaMember.text = depositKelasData.nama_member
                textViewNamaKelas.text = depositKelasData.nama_kelas
                textViewValueSisaDepositKelas.text = depositKelasData.sisa_deposit_kelas.toString()
                textViewValueMasaBerlakuDepositKelas.text = depositKelasData.masa_berlaku_deposit_kelas
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DepositKelasViewHolder {
        return DepositKelasViewHolder(
            DepositKelasAdapterBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: DepositKelasViewHolder, position: Int) {
        holder.bind(data[position])
    }

    override fun getItemCount(): Int = data.size

}