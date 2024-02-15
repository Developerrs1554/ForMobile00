package com.example.mobil00

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayoutStates
import com.google.firebase.FirebaseException
import com.google.firebase.FirebaseTooManyRequestsException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import com.google.firebase.auth.FirebaseAuthMissingActivityForRecaptchaException
import com.google.firebase.auth.PhoneAuthCredential
import com.google.firebase.auth.PhoneAuthOptions
import com.google.firebase.auth.PhoneAuthProvider
import com.google.firebase.database.FirebaseDatabase
import java.util.concurrent.TimeUnit

class MainActivity : AppCompatActivity() {


    lateinit var auth: FirebaseAuth
    lateinit var kayitAlinanKullaniciİsmi:String
    lateinit var kayitAlinanNumara:String
    lateinit var kayitYpmisNum:String


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val rezİsiım = findViewById<EditText>(R.id.isim)
        val rezNumara = findViewById<EditText>(R.id.numara)

        val kayıtOl = findViewById<Button>(R.id.devamm)


        auth = FirebaseAuth.getInstance()

        /* hesabımVar.setOnClickListener{


             rezİsiım.visibility = View.GONE
             hesabımVar.visibility = View.GONE
             kayıtOl.visibility = View.GONE

             forlogin.visibility = View.VISIBLE

             forlogin.setOnClickListener {

                 if (rezNumara.text.toString().isNotEmpty()) {

                     var database = FirebaseDatabase.getInstance()
                     var connectoMot = database.getReference("Users")

                     kayitAlinanNumara = rezNumara.text.toString()
                     kayitAlinanNumara = "+90" + kayitAlinanNumara


                     connectoMot.orderByChild("numara").equalTo(kayitAlinanNumara)
                         .addListenerForSingleValueEvent(object : ValueEventListener{
                             override fun onDataChange(snapshot: DataSnapshot) {
                                 if(snapshot.exists()){


                                     login()
                                     var intent = Intent(applicationContext,VerifTheCode::class.java)
                                     startActivity(intent)



                                 }

                                 else{

                                     Toast.makeText(applicationContext,"Lütfen öncee kayıt olunuz",Toast.LENGTH_LONG).show()



                                 }



                             }


                             override fun onCancelled(error: DatabaseError) {
                                 Toast.makeText(applicationContext,"Firebase error",Toast.LENGTH_LONG).show()
                             }



                         })





                 }







             }





         }*/


        /*var currentUser = auth.currentUser
        if ( currentUser != null) {

            var intent = Intent(applicationContext,Home::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK)
            startActivity(intent)


        }*/



        kayıtOl.setOnClickListener {

            if(  rezNumara.text.toString().isNotEmpty()&& rezNumara.text.toString().isNotEmpty() ) {

                kayitAlinanKullaniciİsmi = rezİsiım.text.toString()
                kayitAlinanNumara = rezNumara.text.toString().trim()
                kayitAlinanNumara = "+90"+ kayitAlinanNumara


                var database = FirebaseDatabase.getInstance()
                var connectoMot = database.getReference("Users")

                login()








                /*connectoMot.orderByChild("numara").equalTo(kayitAlinanNumara)
                    .addListenerForSingleValueEvent(object : ValueEventListener{
                        override fun onDataChange(snapshot: DataSnapshot) {
                            if(snapshot.exists()){

                                Toast.makeText(applicationContext,"Zaten bir hesabınız var lütfen giriş yapınız",Toast.LENGTH_LONG).show()




                            }

                            else{

                                login()



                            }



                        }


                        override fun onCancelled(error: DatabaseError) {
                            Toast.makeText(applicationContext,"Firebase error",Toast.LENGTH_LONG).show()
                        }



                    })*/



            }

            else{

                rezİsiım.error = "Lütfen bilgilerin tamamını doldurunuz"
                rezNumara.error = "Lütfen bilgilerin tamamını doldurunuz"

            }


        }




    }




    private fun login() {




        val options = PhoneAuthOptions.newBuilder(auth)
            .setPhoneNumber(kayitAlinanNumara) // Phone number to verify
            .setTimeout(60L, TimeUnit.SECONDS) // Timeout and unit
            .setActivity(this) // Activity (for callback binding)
            .setCallbacks(callbacks) // OnVerificationStateChangedCallbacks
            .build()
        PhoneAuthProvider.verifyPhoneNumber(options)





    }




    private var  callbacks = object : PhoneAuthProvider.OnVerificationStateChangedCallbacks() {

        override fun onVerificationCompleted(credential: PhoneAuthCredential) {



            /*var intent = Intent(applicationContext,VerifTheCode::class.java)
            startActivity(intent)

            signInWithPhoneAuthCredential(credential)*/



        }

        override fun onVerificationFailed(e: FirebaseException) {
            // This callback is invoked in an invalid request for verification is made,
            // for instance if the the phone number format is not valid.
            Log.w(ConstraintLayoutStates.TAG, "onVerificationFailed", e)

            if (e is FirebaseAuthInvalidCredentialsException) {
                // Invalid request

                Toast.makeText(applicationContext,"Lütfen telefon numarasını 552******* formatında giriniz",Toast.LENGTH_LONG).show()

            } else if (e is FirebaseTooManyRequestsException) {
                // The SMS quota for the project has been exceeded
                Toast.makeText(applicationContext,"Sms kotası aşıldı",Toast.LENGTH_LONG).show()


            } else if (e is FirebaseAuthMissingActivityForRecaptchaException) {
                // reCAPTCHA verification attempted with null Activity
                Toast.makeText(applicationContext,"sdsdsds",Toast.LENGTH_LONG).show()
            }

            // Show a message and update the UI
        }

        override fun onCodeSent(
            verificationId: String,
            token: PhoneAuthProvider.ForceResendingToken,
        ) {
            // The SMS verification code has been sent to the provided phone number, we
            // now need to ask the user to enter the code and then construct a credential
            // by combining the code with a verification ID.





            //-----------------------------------------------


            var intent = Intent ( applicationContext,verifyPart::class.java)
            intent.putExtra("otpcr",verificationId)
            intent.putExtra("named",kayitAlinanKullaniciİsmi)
            intent.putExtra("numm",kayitAlinanNumara)
            startActivity(intent)









            //-------------------------------------------------











        }
    }

    private fun signInWithPhoneAuthCredential(credential: PhoneAuthCredential) {

        auth.signInWithCredential(credential)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {





                    val user = task.result?.user
                } else {
                    // Sign in failed, display a message and update the UI
                    Log.w(ConstraintLayoutStates.TAG, "signInWithCredential:failure", task.exception)
                    if (task.exception is FirebaseAuthInvalidCredentialsException) {
                        // The verification code entered was invalid
                    }
                    // Update UI
                }
            }




    }


























}