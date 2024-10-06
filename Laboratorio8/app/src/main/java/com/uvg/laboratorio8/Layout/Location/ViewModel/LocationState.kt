package com.uvg.laboratorio8.Layout.Location.ViewModel

import Location


data class LocationState(
    val location: Location? = null,
    val locationList: List<Location> = emptyList(),
    val isLoading: Boolean = false,
    val isSuccess: Boolean = false,
    val hasError: Boolean = false,
)
