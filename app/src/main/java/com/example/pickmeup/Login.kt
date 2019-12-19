package com.example.pickmeup

import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import com.example.pickmeup.server.*
import com.example.pickmeup.utils.Utils
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_login.*


class Login : AppCompatActivity() {
// another change

    lateinit var userType: String
    @RequiresApi(Build.VERSION_CODES.KITKAT)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val intent = getIntent()

        if ((intent.hasExtra("msg"))) {
            Toast.makeText(applicationContext, intent.getStringExtra("msg"), Toast.LENGTH_SHORT).show()
        }

        initDataBindings()

        initActions()

    }

    private fun initDataBindings() {
        val id = R.drawable.login_background
        Utils.setImageToImageView(applicationContext, bgImageView, id)

    }

    @RequiresApi(Build.VERSION_CODES.KITKAT)
    private fun login(): User? {
        var user: User? = null

        val thread = Thread(Runnable {
            try {
                val con: Connection = Connection();

                val received = con.login(emailLogin.text.toString(), passwordLogin.text.toString())

                val gson = Gson()
                user = gson.fromJson(received, User::class.java)
            } catch (e: Exception) {
                println(e)
                e.printStackTrace()
            }
        })
        thread.start()

        while (thread.isAlive) {
        }

        return user;
    }

    @RequiresApi(Build.VERSION_CODES.KITKAT)
    private fun initActions() {
        forgotButton.setOnClickListener {
            //Toast.makeText(applicationContext, "Clicked Forgot Password", Toast.LENGTH_SHORT).show()
            forgotpassword().show(supportFragmentManager, "show dialog here")
        }

        createTextView.setOnClickListener {

            val intent = Intent(this, SignUp::class.java)
            startActivity(intent)
            //Toast.makeText(applicationContext, "Clicked Create Account", Toast.LENGTH_SHORT).show()
        }

            val btn: Button? = findViewById(R.id.loginButton);
            btn?.setOnClickListener {

                val logged = login()

                if ((logged != null) && (logged.authorized == true)) {
                    val pref = getSharedPreferences("pickmeup", Context.MODE_PRIVATE)
                    val edit = pref.edit();
                    val gson = Gson()
                    edit.putString("user", gson.toJson(logged))
                    edit.apply()

                    if (logged.is_driver == 1) {
                        myApplication.type = "driverLogin Changed"
                        val intent2 = Intent(this, AutoComplete::class.java)
                        intent2.putExtra("type", "driver")
                        startActivity(intent2)
                    } else {
                        val intent = Intent(this, SearchRide::class.java)
                        intent.putExtra("type", "user")
                        startActivity(intent)
                    }
                } else {
                    Toast.makeText(
                        applicationContext,
                        "Wrong username or password!",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }


        }

        fun creatAccount(view: View) {

        }
        //endregion


}
