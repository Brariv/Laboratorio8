package com.uvg.laboratorio8.LocalAndOnlineData.domain.network

import com.uvg.laboratorio8.LocalAndOnlineData.data.network.dto.CharacterDto
import com.uvg.laboratorio8.LocalAndOnlineData.data.network.dto.CharacterListDto
import com.uvg.laboratorio8.LocalAndOnlineData.data.network.dto.LocationDto


interface RickAndMortyApi {
    suspend fun getAllCharacters(): List<CharacterDto>
    suspend fun getAllLocations(): List<LocationDto>

}