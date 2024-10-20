package com.uvg.laboratorio8.Layout.Location.ViewModel

import com.uvg.laboratorio8.Data.domain.model.Location

interface LocationRepository {
    suspend fun Info( location: Location? = null): Boolean

}