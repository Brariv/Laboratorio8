package com.uvg.laboratorio8.Layout.Character.ViewModel

import com.uvg.laboratorio8.Data.domain.model.Character

interface CharacterRepository {
    suspend fun Info( character: Character? = null): Boolean

}