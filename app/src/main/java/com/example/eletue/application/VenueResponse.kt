package com.example.eletue.application

/**
 * Created by eletue on 15/11/2017.
 */

data class VenueResponseRoot(val response: VenueResponse) {
    data class VenueResponse(val venues: List<Venue>)
}