package com.uvg.laboratorio8.Data.data.di

import android.content.Context
import androidx.room.Room
import com.uvg.laboratorio8.Data.data.AppDataBase

object Dependencies {
    private var database: AppDataBase? = null

    private fun buildDatabase(context: Context): AppDataBase {
        return Room.databaseBuilder(
            context,
            AppDataBase::class.java,
            "uvg23088.db"
        ).build()
    }

    fun provideDatabase(context: Context): AppDataBase {
        return database ?: synchronized(this) {
            database ?: buildDatabase(context).also { database = it }
        }
    }
}