package com.example.mobil00

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class BaslangicSayfasi1 : AppCompatActivity() {






    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_baslangic_sayfasi1)




        var auth = FirebaseAuth.getInstance()
        var currenty = auth.currentUser

        var id = currenty?.uid.toString()
        var reftoRoot = FirebaseDatabase.getInstance().getReference("Users")
        var reftoMain = reftoRoot.child("$id")


        reftoMain.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {

                var onayyy = snapshot.child("Onay Durumu").getValue(String::class.java)

                if (onayyy == "Onaylandı" && currenty!=null){

                    var intent = Intent(applicationContext,deneme::class.java)
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK)
                    startActivity(intent)



                }


                if ( onayyy == "Onaylanmadı" && currenty!=null){

                    var intent = Intent(applicationContext,approvalPage::class.java)
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK)
                    Toast.makeText(applicationContext,"Onay bekleniyor",Toast.LENGTH_LONG).show()
                    startActivity(intent)


                }













            }

            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(applicationContext,"$error", Toast.LENGTH_LONG).show()
            }


        })







        /*if ( currenty != null) {

            var intent = Intent(applicationContext,deneme::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK)
            startActivity(intent)


        }*/




        val button=findViewById<Button>(R.id.devamm)

        button.setOnClickListener{

            val intent= Intent(this,Bildirim::class.java)

            startActivity(intent)
        }
    }
}

