package com.uvg.laboratorio8.Layout.Character.ViewModel

import kotlinx.coroutines.flow.MutableStateFlow
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.uvg.laboratorio8.Data.data.AppDataBase
import com.uvg.laboratorio8.Data.data.di.Dependencies
import com.uvg.laboratorio8.Data.data.local.CharacterDb
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import com.uvg.laboratorio8.Data.data.repository.LocalCharacterRepository
import com.uvg.laboratorio8.Data.data.local.dao.CharacterDao


class CharacterViewModel(
    private val characterRepository: LocalCharacterRepository

): ViewModel() {

    init {
        viewModelScope.launch {
            characterRepository.populateLocalCharacterDatabase()
        }
    }

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


            if (!_state.value.hasError){
                try {
                    val characters = characterRepository.getCharacters()
                    _state.update { it.copy(
                        characterList = characters,
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

    private fun onLoadCharacter(ID: Int) {
        viewModelScope.launch {
            val CscreenState = _state.value

            _state.update { it.copy(
                character = characterRepository.getCharacter(ID),
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
                CharacterViewModel(
                    characterRepository = LocalCharacterRepository(
                        characterDao = db.characterDao()
                    )
                )
            }
        }
    }



}