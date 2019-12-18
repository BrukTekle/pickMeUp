package com.example.pickmeup

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.google.android.gms.common.api.Status
import com.google.android.libraries.places.api.Places
import com.google.android.libraries.places.api.model.Place
import com.google.android.libraries.places.api.net.PlacesClient
import com.google.android.libraries.places.widget.AutocompleteSupportFragment
import com.google.android.libraries.places.widget.listener.PlaceSelectionListener
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

    fun search(view: View) {

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

                Toast.makeText(this@SearchRide,""+p0.address, Toast.LENGTH_LONG).show()
                fromLat=p0.latLng!!.latitude
                fromLat=p0.latLng!!.longitude
                Toast.makeText(this@SearchRide,""+fromLat, Toast.LENGTH_LONG).show()
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
                Toast.makeText(this@SearchRide,""+p0.address, Toast.LENGTH_LONG).show()
                toLat=p0.latLng!!.latitude
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
}
