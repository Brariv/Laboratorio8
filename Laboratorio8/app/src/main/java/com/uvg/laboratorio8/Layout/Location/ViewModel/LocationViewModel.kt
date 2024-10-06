package com.uvg.laboratorio8.Layout.Location.ViewModel

import LocationDb
import kotlinx.coroutines.flow.MutableStateFlow
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.uvg.laboratorio8.Data.CharacterDb
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class LocationViewModel(
    private val LocationRepository: LocationRepository
): ViewModel() {

    private val _state = MutableStateFlow(LocationState())
    val state = _state.asStateFlow()

    fun onEvent(event: LocationScreenEvent) {
        when (event) {
            LocationScreenEvent.onLocationListClick -> onLoadCharacterList()
            is LocationScreenEvent.onLocationClick -> onLoadCharacter(event.ID)
            is LocationScreenEvent.onLoadClick -> onError()
        }
    }

    private fun onError(){
        _state.update { it.copy(
            isLoading = false,
            hasError = true
        )}
    }

     private fun onLoadCharacterList() {
        viewModelScope.launch {
            val LscreenState = _state.value

            _state.update { it.copy(
                isLoading = true,
                hasError = false
            )}

            delay(4000)

            val locations by lazy{
                LocationDb().getAllLocations()
            }

            if (!_state.value.hasError){
                _state.update { it.copy(
                    locationList = locations,
                    isLoading = false,
                    isSuccess = true,
                    hasError = false
                )}
            }

        }
    }

    private fun onLoadCharacter(ID: Int) {
        viewModelScope.launch {
            val LscreenState = _state.value

            _state.update { it.copy(
                location = LocationDb().getLocationById(ID),
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
                LocationViewModel(
                    LocationRepository = LocalLocationRepository()
                )
            }
        }
    }



}