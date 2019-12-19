package com.example.pickmeup

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
//import com.sun.xml.internal.bind.v2.model.core.ID
import com.google.android.libraries.places.widget.AutocompleteSupportFragment
import android.widget.Toast
import com.google.android.gms.common.api.Status
import com.google.android.libraries.places.api.model.Place
import com.google.android.libraries.places.widget.listener.PlaceSelectionListener
import java.util.*
import android.os.Build
import android.view.View
import androidx.annotation.RequiresApi
import com.example.pickmeup.server.Connection
import com.example.pickmeup.server.User
import com.google.android.libraries.places.api.Places
import com.google.android.libraries.places.api.net.PlacesClient
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_auto_complete.*
import com.example.pickmeup.server.*

class AutoComplete : AppCompatActivity() {

    lateinit var placesClient: PlacesClient
    var fromLat = 0.0
    var fromLgt = 0.0
    var toLat = 0.0
    var toLgt = 0.0
    //    internal var placeId=""
    var placesFields =
        Arrays.asList(Place.Field.ID, Place.Field.NAME, Place.Field.ADDRESS, Place.Field.LAT_LNG)

    //
// override fun onCreateView(
//        inflater: LayoutInflater, container: ViewGroup?,
//        savedInstanceState: Bundle?
//    ): View? {
//        // Inflate the layout for this fragment
//        return inflater.inflate(R.layout.activity_auto_complete, container, false)
//
//    }
//    override fun onActivityCreated(savedInstanceState: Bundle?){
//        super.onActivityCreated(savedInstanceState)
//        initPlaces()
//        setupPlacesAutocomplete()
////        setupPlacesAutocomplete2()
//    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_auto_complete)


//        requestPermission()
        initPlaces()
        setupPlacesAutocomplete()
        setupPlacesAutocomplete2()


//        editDatePost.setOnClickListener{view->
//            Toast.makeText(this,"reache",Toast.LENGTH_LONG).show()
//            val c = Calendar.getInstance()
//            val mYear = c.get(Calendar.YEAR)
//            val mMonth = c.get(Calendar.MONTH)
//            val mDay = c.get(Calendar.DAY_OF_MONTH)
//            val dpd = DatePickerDialog(
//                this,
//                DatePickerDialog.OnDateSetListener { view, year, monthOfYear, dayOfMonth ->
//                    // Display Selected date in TextView
//                    editDatePost.setText("$dayOfMonth / $monthOfYear / $year")
//
//                },
//                mYear,
//                mMonth,
//                mDay
//            )
//
//            dpd.show()
//
//        }

//        editFrom.setOnFocusChangeListener { view, hasFocus ->
//            if (hasFocus)
//                setupPlacesAutocomplete()
//            else
//                Toast.makeText(this@AutoComplete,"focus lost",Toast.LENGTH_LONG).show()
//        }
//        setupCurrentPlace()
    }


    private fun setupPlacesAutocomplete() {
        val autocompleteFragment =
            supportFragmentManager.findFragmentById(R.id.fragment_place_search) as AutocompleteSupportFragment
//        val autocompleteFragment= activity!!.supportFragmentManager().findFragmentById(R.id.fragment_place) as AutocompleteSupportFragment

//        var fm = activity!!.getSupportFragmentManager()
//        val autocompleteFragment=fm.findFragmentById(R.id.fragment_place) as AutocompleteSupportFragment
        autocompleteFragment.setPlaceFields(placesFields)
        autocompleteFragment.setHint("From")

        autocompleteFragment.setOnPlaceSelectedListener(object : PlaceSelectionListener {
            override fun onPlaceSelected(p0: Place) {

            //    Toast.makeText(this@AutoComplete, "" + p0.address, Toast.LENGTH_LONG).show()
                fromLat = p0.latLng!!.latitude
                fromLgt = p0.latLng!!.longitude
             //   Toast.makeText(this@AutoComplete, "" + fromLat, Toast.LENGTH_LONG).show()
            }

            override fun onError(p0: Status) {
                Toast.makeText(this@AutoComplete, "" + "p0.statusMessage", Toast.LENGTH_LONG).show()
            }

        })
    }

    private fun setupPlacesAutocomplete2() {
        val autocompleteFragment =
            supportFragmentManager.findFragmentById(R.id.fragment_place2_search) as AutocompleteSupportFragment
        autocompleteFragment.setPlaceFields(placesFields)
        autocompleteFragment.setHint("To")

        autocompleteFragment.setOnPlaceSelectedListener(object : PlaceSelectionListener {
            override fun onPlaceSelected(p1: Place) {
              //  Toast.makeText(this@AutoComplete, "" + p1.address, Toast.LENGTH_LONG).show()
                toLat = p1.latLng!!.latitude
                toLgt = p1.latLng!!.longitude
              //  Toast.makeText(this@AutoComplete, "" + myApplication.type, Toast.LENGTH_LONG).show()
            }

            override fun onError(p1: Status) {
                Toast.makeText(this@AutoComplete, "" + p1.statusMessage, Toast.LENGTH_LONG).show()
            }
        })
    }

    private fun initPlaces() {
        Places.initialize(this, getString(R.string.places_api))
        placesClient = Places.createClient(this)
    }

    @RequiresApi(Build.VERSION_CODES.KITKAT)
    fun viewRides(view: View) {

        val thread = Thread(Runnable {
            try {
                val gson = Gson();
                val pref = getSharedPreferences("pickmeup", Context.MODE_PRIVATE)
                val saved = pref.getString("user","")
                val user: User? = gson.fromJson(saved,User::class.java)

                val con: Connection = Connection();

                var received:String? = con.posted(user?.email)
                    //date[2]+"-"+date[1]+"-"+date[0]+" "+editTime.text.toString()+":00",fromLat.toString(),fromLgt.toString(),toLat.toString(),toLgt.toString())

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

                val intent = Intent(this, fragment_all::class.java)
                intent.putExtra("typeOfUser", "driver")
                intent.putExtra("rides",received)
                startActivity(intent)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        })
        thread.start()
    }

    //date picker
    fun onClick(view: View) {

        when (view?.id) {
            R.id.editDatePost -> {
                val c = Calendar.getInstance()
                val mYear = c.get(Calendar.YEAR)
                val mMonth = c.get(Calendar.MONTH)
                val mDay = c.get(Calendar.DAY_OF_MONTH)
                val dpd = DatePickerDialog(
                    this,
                    DatePickerDialog.OnDateSetListener { view, year, monthOfYear, dayOfMonth ->
                        // Display Selected date in TextView
                        editDatePost.setText("$dayOfMonth / $monthOfYear / $year")

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

            R.id.editTimePost -> {
                val cal = Calendar.getInstance()
                val timeSetListener =
                    TimePickerDialog.OnTimeSetListener { timePicker, hour, minute ->
                        // Selected hour and minutes set into the TextView
                        cal.set(Calendar.HOUR_OF_DAY, hour)
                        cal.set(Calendar.MINUTE, minute)
                        editTimePost.setText(java.text.SimpleDateFormat("HH:mm").format(cal.time))
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

    @RequiresApi(Build.VERSION_CODES.KITKAT)
    fun submit(view: View){
        var msg = ""
        val thread = Thread(Runnable {
            try {
                val gson = Gson();
                val pref = getSharedPreferences("pickmeup", Context.MODE_PRIVATE)
                val saved = pref.getString("user","")
                val user: User? = gson.fromJson(saved,User::class.java)

                val con: Connection = Connection();

                val date = editDatePost.text.toString().split(" / ")

                println("******************************************************************")
                println(date[2]+"-"+date[1]+"-"+date[0]+" "+editTimePost.text.toString()+":00")
                println("******************************************************************")

                var received:String? = con.add(user?.id.toString(),date[2]+"-"+date[1]+"-"+date[0]+" "+editTimePost.text.toString()+":00",fromLgt.toString(),fromLat.toString(),toLgt.toString(),toLat.toString(),fare.text.toString(),seats.text.toString(),comment.text.toString())
                var result: Result? = gson.fromJson(received,Result::class.java)

                println(result?.success)
                println("******************************************************************")

                if ((result != null) && (result.success == true)){
                    msg = "Ride has been added successfully!"
                }
                else{
                    msg = "Can't reach the server now"
                }

            } catch (e: Exception) {
                e.printStackTrace()
            }
        })
        thread.start()

        while (thread.isAlive){

        }

        editDatePost.setText("")
        editTimePost.setText("")
        fare.setText("")
        seats.setText("")
        comment.setText("")
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()

    }
}
    
