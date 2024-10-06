package com.uvg.laboratorio8.Layout.Location.ViewModel

import Location


class LocalLocationRepository: LocationRepository {
    override suspend fun Info( location: Location?): Boolean {
        return location != null
    }
}