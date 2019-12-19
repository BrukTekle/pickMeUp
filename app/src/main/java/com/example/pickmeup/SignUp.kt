package com.example.pickmeup

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.annotation.RequiresApi
import com.example.pickmeup.server.Connection
import com.example.pickmeup.utils.Utils
import com.google.gson.Gson
import com.example.pickmeup.server.*;

import kotlinx.android.synthetic.main.activity_sign_up.*


class SignUp : AppCompatActivity() {

    @RequiresApi(Build.VERSION_CODES.KITKAT)
    fun signup(){
        val thread = Thread(Runnable {
            try {
                val con:Connection = Connection();

                val received = con.signup(editFirstname.text.toString(), editLastName.text.toString(), editTel.text.toString(), editEmail.text.toString(), editPassword.text.toString());
                val gson = Gson();
                var result: Result? = gson.fromJson(received,Result::class.java)

                if ((result != null) && (result.success == true)){
                    if(asDriver.isChecked){
                        con.update(editEmail.text.toString(),editCarType.text.toString(),editCarColor.text.toString(),editPlateNumber.text.toString())
                    }
                    val intent = Intent(this,Login::class.java)
                    intent.putExtra("msg","Account created successfully!")
                    startActivity(intent)
                }
                else{
                    Toast.makeText(applicationContext, "This email already exists!", Toast.LENGTH_SHORT).show()
                }

            } catch (e: Exception) {
                e.printStackTrace()
            }
        })
        thread.start()
    }

    @RequiresApi(Build.VERSION_CODES.KITKAT)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)
        val id = R.drawable.login_background
        Utils.setImageToImageView(applicationContext, bgImageView, id)

        signUpButton.setOnClickListener { signup() }


        asDriver.setOnClickListener {
            if (asDriver.isChecked){
                editCarType.isEnabled = true;
                editCarColor.isEnabled = true;
                editPlateNumber.isEnabled = true;
            }
            else{
                editCarType.isEnabled = false;
                editCarColor.isEnabled = false;
                editPlateNumber.isEnabled = false;
            }
        }
    }
}
