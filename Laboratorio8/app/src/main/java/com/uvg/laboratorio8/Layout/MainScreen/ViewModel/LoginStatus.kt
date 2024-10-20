package com.uvg.laboratorio8.Layout.MainScreen.ViewModel

interface LoginStatus {
    data object Loading: LoginStatus
    data object Authenticated: LoginStatus
    data object NonAuthenticated: LoginStatus

}