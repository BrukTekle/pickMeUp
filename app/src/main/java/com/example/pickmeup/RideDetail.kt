package com.example.pickmeup

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_ride_detail.*

class RideDetail : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ride_detail)

        val recivedIntent=intent

        val img=recivedIntent.getIntExtra("image",0)
        var DriverName=recivedIntent.getStringExtra("name")
        var from=recivedIntent.getStringExtra("from")
        val to=recivedIntent.getStringExtra("to")
        val phone=recivedIntent.getStringExtra("phone")
        var price=recivedIntent.getStringExtra("price")
        var plateNumber=recivedIntent.getStringExtra("plateNumber")
        val carColor=recivedIntent.getStringExtra("color")
        val carType=recivedIntent.getStringExtra("carType")
        val dateAndTime = recivedIntent.getStringExtra("date")
        val seats = recivedIntent.getStringExtra("seats")

        img1Detail.setImageResource(img)
        txtName.text= "Driver's Name: "+DriverName
        txtFromDetail.text= "From Location: " + from
        txtToDetail.text="To Location: "+to
        txtPhone.text="Phone Number: "+phone
        txtPriceDetail.text="Fare: "+price
        txtCarPlate.text="Plate Number: "+plateNumber
        txtColor.text="Car's Color: "+carColor
        txtType.text="Car Type: "+carType
        txtDateDetail.text = "Date and Time: "+dateAndTime
        txtSit.text = "Seats Available: "+ seats
    }
}
