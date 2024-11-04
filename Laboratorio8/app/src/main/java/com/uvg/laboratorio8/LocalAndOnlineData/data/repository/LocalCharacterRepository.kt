package com.uvg.laboratorio8.LocalAndOnlineData.data.repository

import com.uvg.laboratorio8.LocalAndOnlineData.data.di.KtorDependencies
import com.uvg.laboratorio8.LocalAndOnlineData.data.local.CharacterDb
import com.uvg.laboratorio8.LocalAndOnlineData.data.local.dao.CharacterDao
import com.uvg.laboratorio8.LocalAndOnlineData.data.local.entity.mapToEntity
import com.uvg.laboratorio8.LocalAndOnlineData.data.local.entity.mapToModel
import com.uvg.laboratorio8.LocalAndOnlineData.data.network.HttpClientFactory
import com.uvg.laboratorio8.LocalAndOnlineData.data.network.KtorRickAndMortyApi
import com.uvg.laboratorio8.LocalAndOnlineData.data.network.dto.mapToCharacterModel
import com.uvg.laboratorio8.LocalAndOnlineData.domain.model.Character

class LocalCharacterRepository(
    private val characterDao: CharacterDao,
    private val api: KtorRickAndMortyApi
) {

    suspend fun getCharacters(): List<Character> {
        val LCharacters = characterDao.getAllCharacters()

        return LCharacters.map { localCharacter ->
            localCharacter.mapToModel()
        }
    }

    suspend fun getCharacter(id: Int): Character {
        val localCharacter = characterDao.getCharacter(id)

        return localCharacter.mapToModel()
    }

    suspend fun populateLocalCharacterDatabase() {
        val remoteCharacters = CharacterDb().getAllCharacters()
        val localCharacters = remoteCharacters.map { remoteCharacter ->
            remoteCharacter.mapToEntity()
        }
        characterDao.insertAll(localCharacters)
    }


    suspend fun populateOnlineCharacterDatabase() {
        val response = api.getAllCharacters()
        val onlineCharacters = response.map { characterDto ->
            characterDto.mapToCharacterModel()
        }
        characterDao.insertAll(onlineCharacters)
    }



}