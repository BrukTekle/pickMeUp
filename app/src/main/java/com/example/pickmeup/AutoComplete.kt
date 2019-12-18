package com.example.pickmeup

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.content.Intent
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
//import com.sun.xml.internal.bind.v2.model.core.ID
import java.util.Arrays.asList
import com.google.android.libraries.places.widget.AutocompleteSupportFragment
import androidx.core.content.ContextCompat.getSystemService
import android.icu.lang.UCharacter.GraphemeClusterBreak.T
import android.widget.Toast
import androidx.fragment.app.FragmentActivity
import com.google.android.gms.common.api.Status
import com.google.android.libraries.places.api.model.Place
import com.google.android.libraries.places.widget.listener.PlaceSelectionListener
import java.util.*
import androidx.core.app.ComponentActivity.ExtraData
import androidx.core.content.ContextCompat.getSystemService
import android.icu.lang.UCharacter.GraphemeClusterBreak.T
import android.location.Address
import android.location.Geocoder
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.app.ActivityCompat
import androidx.fragment.app.Fragment
import com.google.android.libraries.places.api.Places
import com.google.android.libraries.places.api.net.FindCurrentPlaceRequest
import com.google.android.libraries.places.api.net.FindCurrentPlaceResponse
import com.google.android.libraries.places.api.net.PlacesClient
import com.karumi.dexter.Dexter
import com.karumi.dexter.MultiplePermissionsReport
import com.karumi.dexter.PermissionToken
import com.karumi.dexter.listener.PermissionRequest
import com.karumi.dexter.listener.multi.MultiplePermissionsListener
import kotlinx.android.synthetic.main.activity_auto_complete.*
import java.lang.StringBuilder
import java.util.jar.Manifest


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
            supportFragmentManager.findFragmentById(R.id.fragment_place) as AutocompleteSupportFragment
//        val autocompleteFragment= activity!!.supportFragmentManager().findFragmentById(R.id.fragment_place) as AutocompleteSupportFragment

//        var fm = activity!!.getSupportFragmentManager()
//        val autocompleteFragment=fm.findFragmentById(R.id.fragment_place) as AutocompleteSupportFragment
        autocompleteFragment.setPlaceFields(placesFields)
        autocompleteFragment.setHint("From")

        autocompleteFragment.setOnPlaceSelectedListener(object : PlaceSelectionListener {
            override fun onPlaceSelected(p0: Place) {

                Toast.makeText(this@AutoComplete, "" + p0.address, Toast.LENGTH_LONG).show()
                fromLat = p0.latLng!!.latitude
                fromLat = p0.latLng!!.longitude
                Toast.makeText(this@AutoComplete, "" + fromLat, Toast.LENGTH_LONG).show()
            }

            override fun onError(p0: Status) {
                Toast.makeText(this@AutoComplete, "" + "p0.statusMessage", Toast.LENGTH_LONG).show()
            }

        })
    }

    private fun setupPlacesAutocomplete2() {
        val autocompleteFragment =
            supportFragmentManager.findFragmentById(R.id.fragment_place2) as AutocompleteSupportFragment
        autocompleteFragment.setPlaceFields(placesFields)
        autocompleteFragment.setHint("To")

        autocompleteFragment.setOnPlaceSelectedListener(object : PlaceSelectionListener {
            override fun onPlaceSelected(p0: Place) {
                Toast.makeText(this@AutoComplete, "" + p0.address, Toast.LENGTH_LONG).show()
                toLat = p0.latLng!!.latitude
                toLat = p0.latLng!!.longitude
                Toast.makeText(this@AutoComplete, "" + myApplication.type, Toast.LENGTH_LONG).show()
            }

            override fun onError(p0: Status) {
                Toast.makeText(this@AutoComplete, "" + p0.statusMessage, Toast.LENGTH_LONG).show()
            }
        })
    }

    private fun initPlaces() {
        Places.initialize(this, getString(R.string.places_api))
        placesClient = Places.createClient(this)
    }

    fun viewRides(view: View) {
        val intent = Intent(this, fragment_all::class.java)
        intent.putExtra("typeOfUser", "driver")
        startActivity(intent)
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
}
    
