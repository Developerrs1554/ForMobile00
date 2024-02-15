package com.example.mobil00

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.google.firebase.auth.FirebaseAuth

class BaslangicSayfasi1 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_baslangic_sayfasi1)

        var auth = FirebaseAuth.getInstance()
        var currenty = auth.currentUser

        if ( currenty != null) {

            var intent = Intent(applicationContext,deneme::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK)
            startActivity(intent)


        }




        val button=findViewById<Button>(R.id.devamm)

        button.setOnClickListener{

            val intent= Intent(this,Bildirim::class.java)

            startActivity(intent)
        }
    }
}

