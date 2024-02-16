package com.example.mobil00

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.core.view.ContentInfoCompat.Flags
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class approvalPage : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_approval_page)

        var auth: FirebaseAuth = FirebaseAuth.getInstance()
        var user = auth.currentUser


        if (user != null) {



            var id = user.uid.toString()
            var reftoRoot = FirebaseDatabase.getInstance().getReference("Users")
            var reftoMain = reftoRoot.child("$id")

            reftoMain.addListenerForSingleValueEvent(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {

                    var onayyy = snapshot.child("Onay Durumu").getValue(String::class.java)

                    if (onayyy == "Onaylandı"){

                        var intent = Intent(applicationContext,deneme::class.java)
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK)
                        startActivity(intent)



                    }










                }

                override fun onCancelled(error: DatabaseError) {
                    Toast.makeText(applicationContext,"$error", Toast.LENGTH_LONG).show()
                }


            })









        }















    }
}