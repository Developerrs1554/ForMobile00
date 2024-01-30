package com.example.mobil00

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class BaslangicSayfasi1 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_baslangic_sayfasi1)


        val button=findViewById<Button>(R.id.button1)

        button.setOnClickListener{

            val intent= Intent(this,Bildirim::class.java)

            startActivity(intent)
        }
    }
}

