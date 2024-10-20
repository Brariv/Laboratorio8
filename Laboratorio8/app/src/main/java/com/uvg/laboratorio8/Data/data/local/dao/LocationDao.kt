package com.uvg.laboratorio8.Data.data.local.dao

import androidx.room.Insert
import androidx.room.Dao
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.uvg.laboratorio8.Data.data.local.entity.LocationEntity

@Dao
interface LocationDao {
    @Query ("SELECT * FROM LocationEntity")
    suspend fun getAllLocations() : List<LocationEntity>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertAll(locations: List<LocationEntity>)

    @Query ("SELECT * FROM LocationEntity WHERE id = :id")
    suspend fun getLocation(id: Int) : LocationEntity
}