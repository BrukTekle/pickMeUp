package com.example.pickmeup

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.pickmeup.utils.Utils
import kotlinx.android.synthetic.main.activity_login.*


class Login : AppCompatActivity() {
// another change

    lateinit var userType:String
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        initDataBindings()

        initActions()

    }

    private fun initDataBindings() {
        val id = R.drawable.login_background
        Utils.setImageToImageView(applicationContext, bgImageView, id)

    }

    private fun initActions() {
        forgotButton.setOnClickListener {
            Toast.makeText(applicationContext, "Clicked Forgot Password", Toast.LENGTH_SHORT).show()
            forgotpassword().show(supportFragmentManager,"show dialog here")
        }

        createTextView.setOnClickListener {

            val intent =Intent(this,SignUp::class.java)
            startActivity(intent)
            Toast.makeText(applicationContext, "Clicked Create Account", Toast.LENGTH_SHORT).show() }

        loginButton.setOnClickListener {
            userType=editFirstname.text.toString()
            Toast.makeText(applicationContext, userType, Toast.LENGTH_SHORT).show()
            if (userType=="driver"){
                myApplication.type="driverLogin Changed"
                val intent2 = Intent(this,AutoComplete::class.java)
                intent2.putExtra("type","driver")
                startActivity(intent2)
            }
            else{
                val intent = Intent(this,SearchRide::class.java)
                intent.putExtra("type","user")
                startActivity(intent)
            }

//            Toast.makeText(applicationContext, "Clicked Login", Toast.LENGTH_SHORT).show()
        }

    }

    fun creatAccount(view: View) {

    }
    //endregion


}
