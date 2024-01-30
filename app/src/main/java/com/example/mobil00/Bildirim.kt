package com.example.mobil00

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class Bildirim : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bildirim)

        val button=findViewById<Button>(R.id.button1)

        button.setOnClickListener{

            val intent= Intent(this,Duyuru3::class.java)

            startActivity(intent)
        }
    }
}