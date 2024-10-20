package com.uvg.laboratorio8.Layout.MainScreen.ViewModel

sealed interface LoginEvent {
    data class NameChange(val name: String): LoginEvent
    data object SaveName: LoginEvent
    data object DeleteName: LoginEvent
}