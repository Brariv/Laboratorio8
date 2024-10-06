package com.uvg.laboratorio8.Layout.Character.ViewModel

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

class CharacterViewModel(
    private val CharacterRepository: CharacterRepository
): ViewModel() {

    private val _state = MutableStateFlow(CharacterState())
    val state = _state.asStateFlow()

    fun onEvent(event: CharacterScreenEvent) {
        when (event) {
            CharacterScreenEvent.onCharacterListClick -> onLoadCharacterList()
            is CharacterScreenEvent.onCharacterClick -> onLoadCharacter(event.ID)
            is CharacterScreenEvent.onLoadClick -> onError()
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
            val CscreenState = _state.value

            _state.update { it.copy(
                isLoading = true,
                hasError = false
            )}

            delay(4000)

            val characters by lazy{
                CharacterDb().getAllCharacters()
            }

            if (!_state.value.hasError){
                _state.update { it.copy(
                    characterList = characters,
                    isLoading = false,
                    isSuccess = true,
                    hasError = false
                )}
            }

        }
    }

    private fun onLoadCharacter(ID: Int) {
        viewModelScope.launch {
            val CscreenState = _state.value

            _state.update { it.copy(
                character = CharacterDb().getCharacterById(ID),
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
                CharacterViewModel(
                    CharacterRepository = LocalCharacterRepository()
                )
            }
        }
    }



}