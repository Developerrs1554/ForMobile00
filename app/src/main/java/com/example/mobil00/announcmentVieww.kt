package com.example.mobil00

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class announcmentVieww : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_announcment_vieww)


        var auth : FirebaseAuth = FirebaseAuth.getInstance()
        var user = auth.currentUser

        var id = user?.uid.toString()
        var reftoRoot = FirebaseDatabase.getInstance().getReference("Duyurular")
        //var reftoMain = reftoRoot.child("$id")

        reftoRoot.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {

                var titlee = snapshot.child("title").getValue(String::class.java)
                var bodyy = snapshot.child("body").getValue(String::class.java)



                val nameTextView = findViewById<TextView>(R.id.titlee)
                nameTextView.text = titlee.toString()

                val numaraTextView = findViewById<TextView>(R.id.bodyy)
                numaraTextView.text = bodyy.toString()

            }

            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(applicationContext, "$error", Toast.LENGTH_LONG).show()
            }
        })


























    }
}