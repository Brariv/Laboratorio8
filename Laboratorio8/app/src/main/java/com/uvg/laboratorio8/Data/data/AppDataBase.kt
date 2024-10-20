package com.uvg.laboratorio8.Data.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.uvg.laboratorio8.Data.data.local.dao.CharacterDao
import com.uvg.laboratorio8.Data.data.local.entity.CharacterEntity
import com.uvg.laboratorio8.Data.data.local.dao.LocationDao
import com.uvg.laboratorio8.Data.data.local.entity.LocationEntity

@Database(entities = [
    CharacterEntity::class,
    LocationEntity::class
                     ], version = 1)
abstract class AppDataBase: RoomDatabase() {
    abstract fun characterDao(): CharacterDao
    abstract fun locationDao(): LocationDao
}