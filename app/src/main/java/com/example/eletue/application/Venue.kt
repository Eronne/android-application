package com.example.eletue.application

 data class Venue (val id: String, val name: String, val location: Location) {
    data class Location(val address: String?, val formattedAddress: List<String>)
}