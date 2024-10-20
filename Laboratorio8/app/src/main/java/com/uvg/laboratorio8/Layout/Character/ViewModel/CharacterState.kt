package com.uvg.laboratorio8.Layout.Character.ViewModel

import com.uvg.laboratorio8.Data.domain.model.Character

data class CharacterState(
    val character: Character? = null,
    val characterList: List<Character> = emptyList(),
    val isLoading: Boolean = false,
    val isSuccess: Boolean = false,
    val hasError: Boolean = false,
)
