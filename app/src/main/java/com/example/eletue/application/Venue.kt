package com.example.eletue.application

 data class Venue (val id: String, val name: String, val location: Location, val photos: VenuePhotoRoot) {
    data class Location(val address: String?, val formattedAddress: List<String>)

     data class VenuePhotoRoot (val count: Int, val groups: List<VenuePhotoGroup>)
     data class VenuePhotoGroup(val type: String, val name: String, val count: Int, val items: List<VenuePhotoItem>)
     data class VenuePhotoItem(val prefix: String, val suffix: String, val width: Int, val height: Int) {
         fun getImgUrl(width: Int, height: Int) = "$prefix$width" + "x" + "$height$suffix"
     }
}