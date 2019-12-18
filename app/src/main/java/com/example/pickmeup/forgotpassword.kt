package com.example.pickmeup


import android.app.AlertDialog
import android.app.Dialog
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.telephony.SmsManager
import android.view.LayoutInflater
import android.view.View
import android.widget.EditText
import androidx.appcompat.app.AppCompatDialogFragment
import androidx.fragment.app.Fragment
import android.widget.Toast


/**
 * A simple [Fragment] subclass.
 */
class forgotpassword : AppCompatDialogFragment() {

//    override fun onCreateView(
//        inflater: LayoutInflater, container: ViewGroup?,
//        savedInstanceState: Bundle?
//    ): View? {
//        // Inflate the layout for this fragment
//        return inflater.inflate(R.layout.fragment_forgotpassword, container, false)
//    }
    companion object {
    lateinit var usernam: EditText
    lateinit var email: EditText
}
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val builder:AlertDialog.Builder  =AlertDialog.Builder(activity)
        val inflater:LayoutInflater = activity!!.layoutInflater
         var View:View= inflater.inflate(R.layout.fragment_forgotpassword,null)
        builder.setView(View).setTitle("Reset")

            builder.setNegativeButton("cancel", object : DialogInterface.OnClickListener {
            override fun onClick(dialog: DialogInterface?, which: Int) {
            } })
        builder.setPositiveButton("submitt", object : DialogInterface.OnClickListener {
            override fun onClick(dialog: DialogInterface?, which: Int) {
                sendSMS("2134366418","Hello")
//                usernam=View.findViewById(R.id.username)
//                email=View.findViewById(R.id.email)
//
//                val intent = Intent(Intent.ACTION_SEND)
//                intent.type = "text/html"
//                intent.putExtra(Intent.EXTRA_EMAIL, "emailaddress@emailaddress.com")
//                intent.putExtra(Intent.EXTRA_SUBJECT, "recoverd passward")
//                intent.putExtra(Intent.EXTRA_TEXT, "the recovered password goes here")
//
//                startActivity(Intent.createChooser(intent, "emailaddress@emailaddress.com"))
            } })

            return builder.create()
}
    fun sendSMS(phoneNo: String, msg: String) {
        try {
            val smsManager = SmsManager.getDefault()
            smsManager.sendTextMessage(phoneNo, null, msg, null, null)
            Toast.makeText(
               context, "Message Sent",
                Toast.LENGTH_LONG
            ).show()
        } catch (ex: Exception) {
            Toast.makeText(
                context, ex.message.toString(),
                Toast.LENGTH_LONG
            ).show()
            ex.printStackTrace()
        }

    }

}


