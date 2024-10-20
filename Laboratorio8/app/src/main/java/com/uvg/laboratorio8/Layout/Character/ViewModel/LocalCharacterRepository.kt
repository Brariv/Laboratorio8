package com.uvg.laboratorio8.Layout.Character.ViewModel

import com.uvg.laboratorio8.Data.domain.model.Character

class LocalCharacterRepository: CharacterRepository {
    override suspend fun Info( character: Character?): Boolean {
        return character != null
    }
}