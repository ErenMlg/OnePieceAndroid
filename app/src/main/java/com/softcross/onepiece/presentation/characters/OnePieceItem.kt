package com.softcross.onepiece.presentation.characters

sealed class OnePieceItem {
    object VideoItem : OnePieceItem()
    data class CharacterItem(val uiCharacterItem: UiCharacterItem) : OnePieceItem()
}

data class UiCharacterItem(
    val id: String,
    val name: String,
    val origin: String,
    val crew: String,
    val bounty: String,
    val picture: String
)