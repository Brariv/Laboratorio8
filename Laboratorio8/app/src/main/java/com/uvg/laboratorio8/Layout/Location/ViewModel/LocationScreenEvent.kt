package com.uvg.laboratorio8.Layout.Location.ViewModel

interface LocationScreenEvent {
    data class onLocationClick(val ID: Int): LocationScreenEvent
    data object onLocationListClick: LocationScreenEvent
    data object onLoadClick: LocationScreenEvent
}