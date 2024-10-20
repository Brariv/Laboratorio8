package com.uvg.laboratorio8.Layout.Location.ViewModel

import com.uvg.laboratorio8.Data.domain.model.Location


class LocalLocationRepository: LocationRepository {
    override suspend fun Info( location: Location?): Boolean {
        return location != null
    }
}