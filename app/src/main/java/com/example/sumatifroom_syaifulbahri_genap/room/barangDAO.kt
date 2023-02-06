package com.example.sumatifroom_syaifulbahri_genap.room

import androidx.room.*


@Dao
interface barangDAO {

    @Insert
    fun addtbbarang(barang: tb_barang)

    @Update
    fun updatetbbarang(barang: tb_barang)

    @Delete
    fun deletetbbarang(barang: tb_barang)

    @Query("SELECT * FROM tb_barang")
    fun tampilsemua() : List<tb_barang>

    @Query("SELECT * FROM tb_barang WHERE id =:barang_id")
    fun tampilid(barang_id: Int) : List<tb_barang>

}