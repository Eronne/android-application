package com.example.eletue.application

/**
 * Created by eletue on 15/11/2017.
 */
 data class Venue (val id: String, val name: String, val location: Location) {
    data class Location(val address: String?, val formattedAddress: List<String>)
}