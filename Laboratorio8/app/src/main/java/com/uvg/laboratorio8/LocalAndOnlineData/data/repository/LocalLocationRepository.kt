package com.uvg.laboratorio8.LocalAndOnlineData.data.repository


import com.uvg.laboratorio8.LocalAndOnlineData.data.local.LocationDb
import com.uvg.laboratorio8.LocalAndOnlineData.data.local.dao.LocationDao
import com.uvg.laboratorio8.LocalAndOnlineData.data.local.entity.mapToEntity
import com.uvg.laboratorio8.LocalAndOnlineData.data.local.entity.mapToModel
import com.uvg.laboratorio8.LocalAndOnlineData.data.network.KtorRickAndMortyApi
import com.uvg.laboratorio8.LocalAndOnlineData.data.network.dto.mapToCharacterModel
import com.uvg.laboratorio8.LocalAndOnlineData.data.network.dto.mapToLocationModel
import com.uvg.laboratorio8.LocalAndOnlineData.domain.model.Location

class LocalLocationRepository(
    private val locationDao: LocationDao,
    private val api: KtorRickAndMortyApi
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

    suspend fun populateOnlineLocationDatabase() {
        val response = api.getAllLocations()
        val onlineLocation = response.map { locationDto ->
            locationDto.mapToLocationModel()
        }
        locationDao.insertAll(onlineLocation)
    }
}