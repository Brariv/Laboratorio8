package com.uvg.laboratorio8.LocalAndOnlineData.data.network.dto

import kotlinx.serialization.Serializable

@Serializable
data class LocationListDto (
    val results: List<LocationDto>,

    )