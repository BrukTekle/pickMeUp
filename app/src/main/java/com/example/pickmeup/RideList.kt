package com.example.pickmeup

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.pickmeup.server.Ride
import com.example.pickmeup.server.Rides
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_ride_list.*

class RideList(ridesJson: String?) : Fragment() {

    val gson = Gson()
    val rides = gson.fromJson(ridesJson, Rides::class.java)

    var layoutManager: RecyclerView.LayoutManager? = null
    //val trip1=Trip("Asmara","Fairfield","2019/2/3","Haile","23212543","200.34","Er3-123254","Green","Corola")
    //val trip2=Trip("Asmara","Fairfield","2019/2/3","Haile","23212543","200.34","Er3-123254","Green","Corola")
    //val trip3=Trip("Asmara","Fairfield","2019/2/3","Haile","23212543","200.34","Er3-123254","Green","Corola")

    val images= intArrayOf(R.drawable.night_car_motion_144629_1280x960,R.drawable.night_car_motion_144629_1280x960,R.drawable.night_car_motion_144629_1280x960,R.drawable.night_car_motion_144629_1280x960,R.drawable.night_car_motion_144629_1280x960,R.drawable.night_car_motion_144629_1280x960,R.drawable.night_car_motion_144629_1280x960,R.drawable.night_car_motion_144629_1280x960,R.drawable.night_car_motion_144629_1280x960)
        //,R.drawable.imagescar,R.drawable.imgdark)

    var tripList=ArrayList<Trip>()
    lateinit var userType:String
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.activity_ride_list, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val frag=fragment_all()
        userType=frag.getType()
    //    Toast.makeText(context,"user Type= "+frag,Toast.LENGTH_LONG).show()

        if (rides != null){
            for (ride in rides.rides){
                println("***************************")
                println(ride.departure)
                println("***************************")
                tripList.add(Trip(ride.from_lng+", "+ride.from_lat,ride.to_lng+", "+ride.to_lat,ride.departure,ride.firstname+" "+ride.lastname,ride.phone,ride.fare.toString(),ride.plate,ride.color,ride.car_type,ride.seats_available.toString()))
            }
        }


        val   cAdapter = context?.let { CustomAdapter(it,tripList,images) }
//        val cAdapter=CustomAdapter(this,tripList,images)
//        val r1=findViewByI<RecyclerView>(R.id.recyclerId)
        layoutManager = LinearLayoutManager(context)
        recyclerId?.layoutManager = layoutManager
        recyclerId?.adapter =cAdapter
    }
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_ride_list)
//
//
//
//
//
//    }
}
