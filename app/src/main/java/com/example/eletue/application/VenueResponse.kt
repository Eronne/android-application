package com.example.eletue.application

data class VenuesResponseRoot(val response: VenuesResponse) {
    data class VenuesResponse(val venues: List<Venue>)
}

data class VenueResponseRoot(val response: VenueResponse) {
    data class VenueResponse(val venue: Venue)
}