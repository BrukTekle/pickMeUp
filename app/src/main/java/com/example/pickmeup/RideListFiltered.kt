package com.example.pickmeup

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_ride_list_filtered.*

class RideListFiltered : Fragment() {

    var layoutManager: RecyclerView.LayoutManager? = null
    val trip1 = Trip(
        "Asmara",
        "Fairfield",
        "2019/2/3",
        "Haile",
        "23212543",
        "200.34",
        "Er3-123254",
        "Green",
        "Corola"
    )
    val trip2 = Trip(
        "Asmara",
        "Fairfield",
        "2019/2/3",
        "Haile",
        "23212543",
        "200.34",
        "Er3-123254",
        "Green",
        "Corola"
    )
//    val trip3=Trip("Asmara","Fairfield","2019/2/3","Haile","23212543","200.34","Er3-123254","Green","Corola")

    val images = intArrayOf(
        R.drawable.night_car_motion_144629_1280x960,
        R.drawable.imagescar,
        R.drawable.imgdark
    )

    var tripList = ArrayList<Trip>()
    lateinit var userType: String
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.activity_ride_list_filtered, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val frag = fragment_all()
        userType = frag.getType()
        Toast.makeText(context, "user Type= " + frag, Toast.LENGTH_LONG).show()

        tripList.add(trip1)
        tripList.add(trip2)


        val cAdapter = context?.let { CustomAdapterFiltered(it, tripList, images) }
        layoutManager = LinearLayoutManager(context)
        filteredRecyclerId?.layoutManager = layoutManager
        filteredRecyclerId?.adapter = cAdapter
    }
}