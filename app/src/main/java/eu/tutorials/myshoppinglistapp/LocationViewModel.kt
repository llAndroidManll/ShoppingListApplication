package eu.tutorials.myshoppinglistapp

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class LocationViewModel : ViewModel() {

    private val _location = mutableStateOf<LocationData?>(null)
    val location : State<LocationData?> = _location

    private val _address = mutableStateOf(listOf<GeocodingResult>())
    val address : State<List<GeocodingResult>> = _address

    fun fetchAddress(latlng: String) {
        try {
            viewModelScope.launch {
                val result = RetrofitClient.create()
                    .getAddressFromCoordinates(latlng, "AIzaSyADOV36bpfdLndUA4hU9of2TDeprFW0eJo")

                _address.value = result.results
            }
        } catch (e: Exception) {
            Log.e("FETCH ERROR", "${e.cause}\n${e.message}")
        }
    }

    fun updateLocation(location: LocationData){
        _location.value = location
    }
}