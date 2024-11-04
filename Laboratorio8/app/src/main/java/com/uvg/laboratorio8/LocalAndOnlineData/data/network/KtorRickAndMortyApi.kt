package com.uvg.laboratorio8.LocalAndOnlineData.data.network

import com.uvg.laboratorio8.LocalAndOnlineData.data.local.entity.CharacterEntity
import com.uvg.laboratorio8.LocalAndOnlineData.data.network.dto.CharacterDto
import com.uvg.laboratorio8.LocalAndOnlineData.data.network.dto.CharacterListDto
import com.uvg.laboratorio8.LocalAndOnlineData.data.network.dto.LocationDto
import com.uvg.laboratorio8.LocalAndOnlineData.data.network.dto.LocationListDto
import com.uvg.laboratorio8.LocalAndOnlineData.domain.network.RickAndMortyApi
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get

class KtorRickAndMortyApi : RickAndMortyApi {
    private val client = HttpClientFactory.create()

    override suspend fun getAllCharacters(): List<CharacterDto> {
        val response = client.get("https://rickandmortyapi.com/api/character")
        val charactersDTO = response.body<CharacterListDto>()
        return charactersDTO.results
    }


    override suspend fun getAllLocations(): List<LocationDto> {
        val response = client.get("https://rickandmortyapi.com/api/location")
        val locationsDTO = response.body<LocationListDto>()
        return locationsDTO.results
    }


}




