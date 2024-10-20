package com.uvg.laboratorio8.Layout.Location.ViewModel

import com.uvg.laboratorio8.Data.data.local.LocationDb
import kotlinx.coroutines.flow.MutableStateFlow
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.uvg.laboratorio8.Data.data.di.Dependencies
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import com.uvg.laboratorio8.Data.data.repository.LocalLocationRepository

class LocationViewModel(
    private val locationRepository: LocalLocationRepository
): ViewModel() {

    init {
        viewModelScope.launch {
            locationRepository.populateLocalLocationDatabase()
        }
    }

    private val _state = MutableStateFlow(LocationState())
    val state = _state.asStateFlow()

    fun onEvent(event: LocationScreenEvent) {
        when (event) {
            LocationScreenEvent.onLocationListClick -> onLoadLocationList()
            is LocationScreenEvent.onLocationClick -> onLoadLocation(event.ID)
            is LocationScreenEvent.onLoadClick -> onError()
        }
    }

    private fun onError(){
        _state.update { it.copy(
            isLoading = false,
            hasError = true
        )}
    }

     private fun onLoadLocationList() {
        viewModelScope.launch {
            val LscreenState = _state.value

            _state.update { it.copy(
                isLoading = true,
                hasError = false
            )}

            delay(4000)



            if (!_state.value.hasError){
                try {
                    val locations = locationRepository.getLocations()
                    _state.update { it.copy(
                        locationList = locations,
                        isLoading = false,
                        isSuccess = true,
                        hasError = false
                    )}
                } catch (e: Exception) {
                    _state.update { it.copy(
                        isLoading = false,
                        hasError = true
                    )}
                }
            }

        }
    }

    private fun onLoadLocation(ID: Int) {
        viewModelScope.launch {
            val LscreenState = _state.value

            _state.update { it.copy(
                location = locationRepository.getLocation(ID),
                isLoading = true,
                hasError = false
            )}

            delay(2000)

            if (!_state.value.hasError){
                _state.update { it.copy(
                    isLoading = false,
                    isSuccess = true,
                    hasError = false
                )}

            }


        }
    }

    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application = checkNotNull(this[APPLICATION_KEY])
                val db = Dependencies.provideDatabase(application)
                LocationViewModel(
                    locationRepository = LocalLocationRepository(
                        locationDao = db.locationDao()
                    )
                )
            }
        }
    }



}