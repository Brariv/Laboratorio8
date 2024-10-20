package com.uvg.laboratorio8.Data.data.repository

import com.uvg.laboratorio8.Data.data.local.CharacterDb
import com.uvg.laboratorio8.Data.data.local.dao.CharacterDao
import com.uvg.laboratorio8.Data.data.local.entity.mapToEntity
import com.uvg.laboratorio8.Data.data.local.entity.mapToModel
import com.uvg.laboratorio8.Data.domain.model.Character

class LocalCharacterRepository(
    private val characterDao: CharacterDao
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
}