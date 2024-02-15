package com.example.mobil00

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.google.firebase.auth.FirebaseAuth

class deneme : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_deneme)


        var outt = findViewById<Button>(R.id.outt)

        outt.setOnClickListener {

            FirebaseAuth.getInstance().signOut()

            var intent = Intent(applicationContext,BaslangicSayfasi1::class.java)
            startActivity(intent)



        }














    }
}