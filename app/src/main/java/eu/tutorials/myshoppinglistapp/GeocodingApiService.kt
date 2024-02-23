package eu.tutorials.myshoppinglistapp

import com.google.android.gms.maps.model.LatLng
import retrofit2.http.GET
import retrofit2.http.Query

interface GeocodingApiService {


    @GET("maps/api/geocode/json")
    suspend fun getAddressFromCoordinates(
        @Query("latlng") latlng: LatLng,
        @Query("key") key: String
    ) :GeocodingResult

}