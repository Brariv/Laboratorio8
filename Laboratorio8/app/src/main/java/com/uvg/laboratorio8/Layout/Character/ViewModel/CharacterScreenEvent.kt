package com.uvg.laboratorio8.Layout.Character.ViewModel

interface CharacterScreenEvent {
    data class onCharacterClick(val ID: Int): CharacterScreenEvent
    data object onCharacterListClick: CharacterScreenEvent
    data object onLoadClick: CharacterScreenEvent
}