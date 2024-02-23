package eu.tutorials.myshoppinglistapp

data class LocationData(
    val latitude: Double,
    val longitude: Double
)

data class GeocodingResponse(
    val result: List<GeocodingResult>,
    val status: String
)
data class GeocodingResult(
    val formatted_address: String
)