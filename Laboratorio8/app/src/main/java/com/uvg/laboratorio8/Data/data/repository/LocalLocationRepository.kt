package com.uvg.laboratorio8.Data.data.repository


import com.uvg.laboratorio8.Data.data.local.LocationDb
import com.uvg.laboratorio8.Data.data.local.dao.LocationDao
import com.uvg.laboratorio8.Data.data.local.entity.mapToEntity
import com.uvg.laboratorio8.Data.data.local.entity.mapToModel
import com.uvg.laboratorio8.Data.domain.model.Location

class LocalLocationRepository(
    private val locationDao: LocationDao
) {
    suspend fun getLocations(): List<Location> {
        val LLocation = locationDao.getAllLocations()

        return LLocation.map { localLocation ->
            localLocation.mapToModel()
        }
    }

    suspend fun getLocation(id: Int): Location {
        val localLocation = locationDao.getLocation(id)

        return localLocation.mapToModel()
    }

    suspend fun populateLocalLocationDatabase() {
        val remoteLocations = LocationDb().getAllLocations()
        val localLocation = remoteLocations.map { remoteLocation ->
            remoteLocation.mapToEntity()
        }
        locationDao.insertAll(localLocation)
    }
}