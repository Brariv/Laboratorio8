package com.uvg.laboratorio8.Layout.Location.ViewModel

import Location

interface LocationRepository {
    suspend fun Info( location: Location? = null): Boolean

}