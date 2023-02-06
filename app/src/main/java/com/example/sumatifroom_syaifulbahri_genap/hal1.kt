package com.example.sumatifroom_syaifulbahri_genap

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View

class hal1 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_hal1)
    }

    fun masuk(view: View){
        val pindah = Intent (this, MainActivity::class.java)
        startActivity(pindah)
    }
}