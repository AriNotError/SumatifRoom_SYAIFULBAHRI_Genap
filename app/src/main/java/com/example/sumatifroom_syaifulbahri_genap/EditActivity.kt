package com.example.sumatifroom_syaifulbahri_genap

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.example.sumatifroom_syaifulbahri_genap.room.Constant
import com.example.sumatifroom_syaifulbahri_genap.room.codepelita
import com.example.sumatifroom_syaifulbahri_genap.room.tb_barang
import kotlinx.android.synthetic.main.activity_edit.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class EditActivity : AppCompatActivity() {

    val db by lazy {codepelita(this)}
    private var tbbarangid: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit)
        tombolPerintah()
        setupView()
        tbbarangid = intent.getIntExtra("intent_id", tbbarangid)
        Toast.makeText(this, tbbarangid.toString(),Toast.LENGTH_SHORT).show()
    }

    fun setupView(){
        val intentType = intent.getIntExtra("intent_type",0)
        when (intentType){
            Constant.TYPE_CREATE -> {

            }
            Constant.TYPE_READ -> {
                btnSimpan.visibility = View.GONE
                btnUpdate.visibility = View.GONE
                tampildatanis()
            }
            Constant.TYPE_UPDATE -> {
                btnSimpan.visibility = View.GONE
                tampildatanis()
            }
        }
    }

    fun tombolPerintah(){
        btnSimpan.setOnClickListener{
            CoroutineScope(Dispatchers.IO).launch {
                db.tbBarDAO().addtbbarang(
                    tb_barang(
                        etId.text.toString().toInt(),
                        etQty.text.toString().toInt(),
                        etHarga.text.toString().toInt(),
                        etNama.text.toString()
                    )
                )
                finish()
            }
        }
        btnUpdate.setOnClickListener{
            CoroutineScope(Dispatchers.IO).launch {
                db.tbBarDAO().updatetbbarang(
                    tb_barang(
                        etId.text.toString().toInt(),
                        etQty.text.toString().toInt(),
                        etHarga.text.toString().toInt(),
                        etNama.text.toString()
                    )
                )
                finish()
            }
        }

    }

    fun tampildatanis(){
        tbbarangid = intent.getIntExtra("intent_id",0)
        CoroutineScope(Dispatchers.IO).launch {
            val notes = db.tbBarDAO().tampilid(tbbarangid).get(0)
            val dataid: String = notes.id.toString()
            val dataqty: String = notes.Qty.toString()
            val dataharga: String = notes.Harga.toString()
            etId.setText(dataid)
            etQty.setText(dataqty)
            etHarga.setText(dataharga)
            etNama.setText(notes.Nama_barang)
        }
    }

    fun backtomenu(view: View){
        val pindah = Intent (this, MainActivity::class.java)
        startActivity(pindah)
    }

}