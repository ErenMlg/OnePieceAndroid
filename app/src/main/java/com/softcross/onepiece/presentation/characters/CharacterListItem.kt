package com.softcross.onepiece.presentation.characters

sealed class CharacterListItem {
    object VideoItem : CharacterListItem()
    data class CharacterItem(val characterListUiItem: CharacterListUiItem) : CharacterListItem()
}

data class CharacterListUiItem(
    val id: String,
    val name: String,
    val bounty: String,
    val picture: String
)