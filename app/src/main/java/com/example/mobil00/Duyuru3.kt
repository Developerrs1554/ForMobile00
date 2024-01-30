package com.example.mobil00

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class Duyuru3 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_duyuru3)

        val button=findViewById<Button>(R.id.button1)

        button.setOnClickListener{

            val intent= Intent(this,MainActivity::class.java)

            startActivity(intent)
        }
    }
}