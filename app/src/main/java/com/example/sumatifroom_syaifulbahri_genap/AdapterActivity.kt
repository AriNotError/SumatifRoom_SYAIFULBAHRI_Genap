package com.example.sumatifroom_syaifulbahri_genap

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.sumatifroom_syaifulbahri_genap.room.tb_barang
import kotlinx.android.synthetic.main.activity_adapter.view.*

class AdapterActivity (private val barang:ArrayList<tb_barang>, private val listener: OnAdapterListener)
    :RecyclerView.Adapter<AdapterActivity.BarangViewHolder>(){

    class BarangViewHolder(val view:View) : RecyclerView.ViewHolder(view)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BarangViewHolder {
        return BarangViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.activity_adapter,parent,false)
        )
    }

    override fun onBindViewHolder(holder: BarangViewHolder, position: Int) {
        val bar = barang[position]
        holder.view.read.setOnClickListener{listener.onClick(bar)}
        holder.view.update.setOnClickListener{listener.onUpdate(bar)}
        holder.view.delete.setOnClickListener{listener.onDelete(bar)}
        holder.view.TNama.text = bar.Nama_barang
        holder.view.Tid.text = bar.id.toString()
    }

    override fun getItemCount() = barang.size

    fun setData(list: List<tb_barang>){
        barang.clear()
        barang.addAll(list)
        notifyDataSetChanged()
    }

    interface OnAdapterListener{
        fun onClick(tbBarang: tb_barang)
        fun onUpdate(tbBarang: tb_barang)
        fun onDelete(tbBarang: tb_barang)
    }
}