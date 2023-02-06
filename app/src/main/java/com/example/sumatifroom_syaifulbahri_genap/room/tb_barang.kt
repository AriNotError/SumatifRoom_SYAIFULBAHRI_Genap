package com.example.sumatifroom_syaifulbahri_genap.room

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity
class tb_barang (
    @PrimaryKey
    val id: Int,
    val Qty: Int,
    val Harga: Int,
    val Nama_barang: String
)