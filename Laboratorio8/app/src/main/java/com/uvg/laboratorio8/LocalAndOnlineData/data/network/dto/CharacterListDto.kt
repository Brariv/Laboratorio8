package com.uvg.laboratorio8.LocalAndOnlineData.data.network.dto

import kotlinx.serialization.Serializable

@Serializable
data class CharacterListDto (
    val results: List<CharacterDto>,

)