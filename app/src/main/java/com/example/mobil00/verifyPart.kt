package com.example.mobil00

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import com.google.firebase.auth.PhoneAuthCredential
import com.google.firebase.auth.PhoneAuthProvider
import com.google.firebase.database.FirebaseDatabase


class verifyPart : AppCompatActivity() {



    lateinit var auth:FirebaseAuth
    lateinit var Kİ :String  // Kullanıcı ismi kayıt ekranındanalındır
    lateinit var KN : String // kullanıcı numarası kayıt ekranından alındı





    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_verify_part)

        val codesended = findViewById<EditText>(R.id.kod)
        val verifyOnay = findViewById<Button>(R.id.onayy)

        Kİ = intent.getStringExtra("named").toString()
        KN = intent.getStringExtra("numm").toString()


        auth = FirebaseAuth.getInstance()
        var currentUser = auth.currentUser
        var identifier = currentUser?.uid

        val storedVerifİd = intent.getStringExtra("otpcr")



        verifyOnay.setOnClickListener {

            var otp = codesended.text.toString().trim()

            if(otp.isNotEmpty()) {

                val credential = PhoneAuthProvider.getCredential(storedVerifİd.toString(),otp)
                signInWithPhoneAuthCredential(credential)



            }

            else {

                Toast.makeText(applicationContext,"Lütfen alanı doldurunuz", Toast.LENGTH_LONG).show()



            }




        }



    }

    private fun signInWithPhoneAuthCredential(credential: PhoneAuthCredential) {
        auth.signInWithCredential(credential)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information

                    var currentUser = auth.currentUser
                    var identifier = currentUser?.uid

                    var databas = FirebaseDatabase.getInstance()
                    var reff = databas.getReference("Users")
                    var userinfo =databas.getReference("${identifier.toString()}")

                    /*reff.orderByChild("numara").equalTo("$KN").addValueEventListener(object : ValueEventListener{
                        override fun onDataChange(snapshot: DataSnapshot) {

                            if (snapshot.exists()) {
                                val newData = hashMapOf(
                                    "Name" to "$Kİ"

                                )





                                userinfo.child("Name").updateChildren(newData as Map<String, Any>)
                                    .addOnSuccessListener {

                                        var intent = Intent(this@VerifTheCode,MainActivity::class.java)
                                        //intent.putExtra("KİİN",Kİ)
                                        //intent.putExtra("KNNN",KN)
                                        startActivity(intent)

                                    }
                                    .addOnFailureListener { e ->
                                        Toast.makeText(applicationContext,"Hata",Toast.LENGTH_LONG).show()
                                    }





                            }




                            else{

                                kayitAlVeri()
                                var intent = Intent(this@VerifTheCode,MainActivity::class.java)
                                //intent.putExtra("KİİN",Kİ)
                                //intent.putExtra("KNNN",KN)
                                startActivity(intent)





                            }




                        }

                        override fun onCancelled(error: DatabaseError) {
                            Toast.makeText(this@VerifTheCode,"Sunucu kaynaklı sorun $error",Toast.LENGTH_LONG).show()
                        }


                    })*/


                    //-------------------------------

                    kayitAlVeri()
                    var intent = Intent(applicationContext,approvalPage::class.java)
                    intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                    startActivity(intent)


















                    /*reff.orderByChild("numara").equalTo(KN).addValueEventListener(object :ValueEventListener{


                        override fun onDataChange(snapshot: DataSnapshot) {

                            if (snapshot.exists()){


                                for (kisiSnapshot in snapshot.children) {
                                    kullanıcıTransfer = kisiSnapshot.child("Name").getValue(String::class.java)
                                        .toString()

                                }

                                var intent = Intent(this@VerifTheCode,Home::class.java)
                                intent.putExtra("kayitYapani",kullanıcıTransfer)
                                intent.putExtra("kayitluNum",KN)
                                startActivity(intent)



                            }
                            else{

                                kayitAlVeri()

                                var intent = Intent(this@VerifTheCode,Home::class.java)
                                intent.putExtra("Kİİ",Kİ)
                                intent.putExtra("KNN",KN)








                            }


                        }

                        override fun onCancelled(error: DatabaseError) {

                            Toast.makeText(applicationContext,"hAYA ${error}",Toast.LENGTH_LONG).show()

                        }


                    })*/










                    val user = task.result?.user
                } else {
                    // Sign in failed, display a message and update the UI

                    if (task.exception is FirebaseAuthInvalidCredentialsException) {
                        // The verification code entered was invalid
                    }
                    // Update UI
                }
            }
    }







    private fun kayitAlVeri() {

        var userinUser = auth.currentUser
        var userUid = userinUser?.uid.toString()

        var databas = FirebaseDatabase.getInstance()
        var refff = databas.getReference("Users")
        var kayıt = refff.child("$userUid")
        kayıt.child("Name").setValue("$Kİ")
        kayıt.child("numara").setValue("$KN")
        kayıt.child("Durum").setValue("Üye")
        //kayıt.child("Açıklama").setValue("")
        kayıt.child("Onay Durumu").setValue("Onaylanmadı")
        kayıt.child("Kullanıcı uid").setValue(userUid)
        //.child("Arzulanan tarih").setValue("")
        //kayıt.child("Belirlenen tarih").setValue("")


    }




}