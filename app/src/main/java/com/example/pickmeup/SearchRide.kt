package com.example.pickmeup

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.annotation.RequiresApi
import com.example.pickmeup.server.Connection
import com.example.pickmeup.server.Rides
import com.google.android.gms.common.api.Status
import com.google.android.libraries.places.api.Places
import com.google.android.libraries.places.api.model.Place
import com.google.android.libraries.places.api.net.PlacesClient
import com.google.android.libraries.places.widget.AutocompleteSupportFragment
import com.google.android.libraries.places.widget.listener.PlaceSelectionListener
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_search_ride.*
import java.util.*

class SearchRide : AppCompatActivity() {

    lateinit var placesClient: PlacesClient
    var fromLat=0.0
    var fromLgt=0.0
    var toLat=0.0
    var toLgt=0.0
    var placesFields= Arrays.asList(Place.Field.ID, Place.Field.NAME, Place.Field.ADDRESS, Place.Field.LAT_LNG)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search_ride)
        initPlaces()
        setupPlacesAutocomplete()
        setupPlacesAutocomplete2()
    }

    @RequiresApi(Build.VERSION_CODES.KITKAT)
    fun search(view: View) {
        val thread = Thread(Runnable {
            try {
                val gson = Gson();
                //val pref = getSharedPreferences("pickmeup", Context.MODE_PRIVATE)
                //val saved = pref.getString("user","")
                //val user: User? = gson.fromJson(saved,User::class.java)

                val con: Connection = Connection();

                val date = editDate.text.toString().split(" / ")

                var received:String? = con.check(date[2]+"-"+date[1]+"-"+date[0]+" "+editTime.text.toString()+":00",fromLat.toString(),fromLgt.toString(),toLat.toString(),toLgt.toString())

                //println("******************************************")
                //println(date[2]+"-"+date[1]+"-"+date[0]+" "+editTime.text.toString()+":00")
                //println(fromLat.toString())
                //println(fromLgt.toString())
                //println(toLat.toString())
                //println(toLgt.toString())
                //println(received)
                //println("******************************************")


                /*var rides = gson.fromJson(received,Rides::class.java)
                println(rides.toString())
                println("******************************************")*/
                val intent = Intent(this,fragment_all::class.java)
                intent.putExtra("rides",received)
                intent.putExtra("typeOfUser","User")
                startActivity(intent)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        })
        thread.start()
    }

    fun viewAll(view: View) {
        val intent = Intent(this,fragment_all::class.java)
        intent.putExtra("type","user")
        startActivity(intent)
    }

    private fun initPlaces(){
        Places.initialize(this,getString(R.string.places_api))
        placesClient= Places.createClient(this)
    }

    private fun setupPlacesAutocomplete() {
        val autocompleteFragment=supportFragmentManager.findFragmentById(R.id.fragment_place) as AutocompleteSupportFragment

        autocompleteFragment.setPlaceFields(placesFields)
        autocompleteFragment.setHint("From")

        autocompleteFragment.setOnPlaceSelectedListener(object : PlaceSelectionListener {
            override fun onPlaceSelected(p0: Place) {

               // Toast.makeText(this@SearchRide,""+p0.address, Toast.LENGTH_LONG).show()
                fromLgt=p0.latLng!!.latitude
                fromLat=p0.latLng!!.longitude
               // Toast.makeText(this@SearchRide,""+fromLat, Toast.LENGTH_LONG).show()
            }

            override fun onError(p0: Status) {
                Toast.makeText(this@SearchRide,""+"p0.statusMessage", Toast.LENGTH_LONG).show()
            }

        })
    }

    private fun setupPlacesAutocomplete2() {
        val autocompleteFragment=supportFragmentManager.findFragmentById(R.id.fragment_place2) as AutocompleteSupportFragment
        autocompleteFragment.setPlaceFields(placesFields)
        autocompleteFragment.setHint("To")

        autocompleteFragment.setOnPlaceSelectedListener(object : PlaceSelectionListener {
            override fun onPlaceSelected(p0: Place) {
               // Toast.makeText(this@SearchRide,""+p0.address, Toast.LENGTH_LONG).show()
                toLgt=p0.latLng!!.latitude
                toLat=p0.latLng!!.longitude
            }

            override fun onError(p0: Status) {
                Toast.makeText(this@SearchRide,""+p0.statusMessage, Toast.LENGTH_LONG).show()
            }

        })
    }

    fun viewMap(view: View) {
        val intt =Intent(this,MapsActivity::class.java)
        startActivity(intt)
    }

    //date picker
    fun onClick(view: View) {

        when (view?.id) {
            R.id.editDate -> {
                val c = Calendar.getInstance()
                val mYear = c.get(Calendar.YEAR)
                val mMonth = c.get(Calendar.MONTH)
                val mDay = c.get(Calendar.DAY_OF_MONTH)
                val dpd = DatePickerDialog(
                    this,
                    DatePickerDialog.OnDateSetListener { view, year, monthOfYear, dayOfMonth ->
                        // Display Selected date in TextView
                        editDate.setText("$dayOfMonth / $monthOfYear / $year")

                    },
                    mYear,
                    mMonth,
                    mDay
                )

                dpd.show()
            }
        }
    }

    //time picker
    fun onClick2(view: View) {
        when (view?.id) {

            R.id.editTime -> {
                val cal = Calendar.getInstance()
                val timeSetListener =
                    TimePickerDialog.OnTimeSetListener { timePicker, hour, minute ->
                        // Selected hour and minutes set into the TextView
                        cal.set(Calendar.HOUR_OF_DAY, hour)
                        cal.set(Calendar.MINUTE, minute)
                        editTime.setText(java.text.SimpleDateFormat("HH:mm").format(cal.time))
                    }
                TimePickerDialog(
                    this,
                    timeSetListener,
                    cal.get(Calendar.HOUR_OF_DAY),
                    cal.get(Calendar.MINUTE),
                    true
                ).show()

            }
        }

    }
}