package com.example.mobil00

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class deneme : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {


        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_deneme)


        //var auth:FirebaseAuth = FirebaseAuth.getInstance()

        var auth : FirebaseAuth = FirebaseAuth.getInstance()
        var user = auth.currentUser

        var id = user?.uid.toString()
        var reftoRoot = FirebaseDatabase.getInstance().getReference("Users")
        var reftoMain = reftoRoot.child("$id")

        reftoMain.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {

                var name = snapshot.child("Name").getValue(String::class.java)
                var num = snapshot.child("numara").getValue(String::class.java)
                var onayyy = snapshot.child("Onay Durumu").getValue(String::class.java)
                var gorevv = snapshot.child("Durum").getValue(String::class.java)


                if ( onayyy == "Onaylanmadı" ){

                    var intent = Intent(applicationContext,approvalPage::class.java)
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK)
                    Toast.makeText(applicationContext,"Çıkışınız Yapıldı",Toast.LENGTH_LONG).show()
                    startActivity(intent)


                }

                if(user == null){

                    var intent = Intent(applicationContext,approvalPage::class.java)
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK)
                    Toast.makeText(applicationContext,"Çıkışınız Yapıldı",Toast.LENGTH_LONG).show()
                    startActivity(intent)



                }








                val nameTextView = findViewById<TextView>(R.id.nameTextView)
                nameTextView.text = name

                val numaraTextView = findViewById<TextView>(R.id.numaraTextView)
                numaraTextView.text = num

                val durumTextView = findViewById<TextView>(R.id.durumTextView)
                durumTextView.text = gorevv
            }

            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(applicationContext, "$error", Toast.LENGTH_LONG).show()
            }
        })





        var outt = findViewById<Button>(R.id.outt)

        outt.setOnClickListener {

            FirebaseAuth.getInstance().signOut()

            var intent = Intent(applicationContext,BaslangicSayfasi1::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK)
            startActivity(intent)



        }

        val announce = findViewById<TextView>(R.id.announcement)

        announce.setOnClickListener {

            var intent = Intent(applicationContext,announcmentVieww::class.java)
            startActivity(intent)




        }














    }
}