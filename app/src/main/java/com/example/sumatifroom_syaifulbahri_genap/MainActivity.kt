package com.example.sumatifroom_syaifulbahri_genap

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.sumatifroom_syaifulbahri_genap.room.Constant
import com.example.sumatifroom_syaifulbahri_genap.room.codepelita
import com.example.sumatifroom_syaifulbahri_genap.room.tb_barang
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainActivity : AppCompatActivity() {

    lateinit var adapterActivity: AdapterActivity
    val db by lazy { codepelita(this) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        halEdit()
        setupRecyclerView()
    }

    override fun onStart() {
        super.onStart()
        loadData()
    }

    fun loadData(){
        CoroutineScope(Dispatchers.IO).launch {
            val barang = db.tbBarDAO().tampilsemua()
            Log.d("MainActivity", "dbResponse: $barang")
            withContext(Dispatchers.Main) {
                adapterActivity.setData(barang)
            }
        }
    }

    private fun halEdit(){
        btnInput.setOnClickListener{
            intentEdit(0, Constant.TYPE_CREATE)
        }
    }

    fun intentEdit(tbbarangid: Int, intentType:Int){
        startActivity(
            Intent(applicationContext,EditActivity::class.java)
                .putExtra("intent_id",tbbarangid)
                .putExtra("intent_type",intentType)
        )
    }



    fun setupRecyclerView(){
        adapterActivity = AdapterActivity(arrayListOf(),object : AdapterActivity.OnAdapterListener{
            override fun onClick(tbBarang: tb_barang) {
              intentEdit(tbBarang.id,Constant.TYPE_READ)
            }

            override fun onUpdate(tbBarang: tb_barang) {
                intentEdit(tbBarang.id,Constant.TYPE_UPDATE)
            }

            override fun onDelete(tbBarang: tb_barang) {
                deleteAlert(tbBarang)
            }
        })
        listadatasiswa.apply {
            layoutManager = LinearLayoutManager(applicationContext)
            adapter = adapterActivity
        }
    }

    private fun deleteAlert(tbBarang: tb_barang){
        val dialog = AlertDialog.Builder(this)
        dialog.apply {
            setTitle("Konfirmasi Hapus")
            setMessage("Yakin hapus ${tbBarang.Nama_barang}?")
            setNegativeButton("Batal") {dialogInterface, i ->
                dialogInterface.dismiss()
            }
            setPositiveButton("Hapus") {dialogInterface, i ->
                CoroutineScope(Dispatchers.IO).launch {
                    db.tbBarDAO().deletetbbarang(tbBarang)
                    dialogInterface.dismiss()
                    loadData()
                }
            }
        }
        dialog.show()
    }

    fun backtohal1(view: View){
        val pindah = Intent (this, hal1::class.java)
        startActivity(pindah)
    }

}