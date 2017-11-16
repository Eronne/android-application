package com.example.eletue.application

class VenueDetailsResponse(val response: NestedResponse) {
    class NestedResponse(val venue: Venue) {}
}