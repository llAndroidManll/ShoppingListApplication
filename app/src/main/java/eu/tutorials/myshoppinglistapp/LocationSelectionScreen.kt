package eu.tutorials.myshoppinglistapp

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.MarkerState
import com.google.maps.android.compose.rememberCameraPositionState

@Composable
fun LocationSelectionScreen(
    location: LocationData,
    onLocationSelected: (LocationData) -> Unit
) {

    // The user location will basically be the location of where the phone is.
    val userLocation = remember{ mutableStateOf(LatLng(location.latitude,location.longitude)) }



    /*
    * So what we want to do ?
    * We want to position the camera of the maps.
    * So we want to basically say, hey, Google Maps, zoom in to that area where the user is right now,
    * I don't want to see California or whatever, like the default where we are.
    * I want to see wherever the user is at a certain zoom state or zoom level.
    *
    * */
    var cameraPositionState = rememberCameraPositionState {
        // we are setting latitude and longitude (userLocation.value) and then the Zoom level (10f)
        position = CameraPosition.fromLatLngZoom(userLocation.value, 10f)
    }

    Column(
        modifier= Modifier.fillMaxSize(),
    ) {
        GoogleMap(
            /*
            * What weight does ?
            *   It basically sets the size of the element in to in proportion relative to other
            *   weighted sibling elements inside of that column.
            *   So basically we're saying hey you have a lot of weight.
            *   So you're going to be very important.
            *   You're going to take a lot of space on the screen.
            *       (like z-index in Css)
            * */
            modifier = Modifier.weight(1f).padding(top = 16.dp),
            cameraPositionState = cameraPositionState,
            onMapClick = {
                userLocation.value = it
            }
        ) {
            // red dot on map.
            Marker(
                state = MarkerState(position = userLocation.value),

            )
        }

        var newLocation: LocationData

        Button(
            onClick = {
                newLocation = LocationData(userLocation.value.latitude, userLocation.value.longitude)
                onLocationSelected(newLocation)
            }
        ) {
            Text("Set Location")
        }
    }

}
