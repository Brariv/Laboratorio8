package com.uvg.laboratorio8.Data.domain.usrpref

import kotlinx.coroutines.flow.Flow

interface UserPreferences {
    suspend fun logIn()
    suspend fun logOut()
    fun authStatus(): Flow<Boolean>
    suspend fun setName(name: String)
    fun getName(): Flow<String>
}