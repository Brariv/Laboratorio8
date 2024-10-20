package com.uvg.laboratorio8.Layout.MainScreen.ViewModel

import com.uvg.laboratorio8.Data.domain.usrpref.UserPreferences
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import com.uvg.laboratorio8.dataStore
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.uvg.laboratorio8.Data.data.DataStoreUserPrefs
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class LoginViewModel(
    private val preferences: UserPreferences
): ViewModel() {

    private val _state = MutableStateFlow(LoginState())
    val state = _state.asStateFlow()

    val authStatus = preferences.authStatus()
        .onStart { delay(2000) }
        .map { isLogged ->
            if (isLogged) {
                LoginStatus.Authenticated
            } else {
                LoginStatus.NonAuthenticated
            }
        }
        .catch { error ->
            println(error)
            LoginStatus.NonAuthenticated
        }
        .onEach { item -> println(item) }
        .stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(5000),
            LoginStatus.Loading
        )


    fun onEvent(event: LoginEvent) {
        when (event) {
            is LoginEvent.NameChange -> {
                _state.update {
                    it.copy(name = event.name)
                }
            }
            LoginEvent.SaveName -> {
                setName()
            }
            LoginEvent.DeleteName -> {
                eraseName()
            }
        }
    }

    val userNameState = preferences
        .getName()
        .stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(5000),
            null
        )

    fun logIn() {
        viewModelScope.launch {
            preferences.logIn()
        }
    }

    fun logOut() {
        viewModelScope.launch {
            preferences.logOut()
        }
    }

    fun setName() {
        viewModelScope.launch {
            preferences.setName(_state.value.name)
        }
    }

    fun eraseName() {
        viewModelScope.launch {
            preferences.setName("")
        }
    }

    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application = checkNotNull(this[APPLICATION_KEY])
                LoginViewModel(
                    preferences = DataStoreUserPrefs(application.dataStore)
                )
            }
        }
    }
}