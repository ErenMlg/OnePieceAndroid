package com.softcross.onepiece.core.data.mapper

import com.softcross.onepiece.core.common.mapper.OnePieceResponseListMapper
import com.softcross.onepiece.core.data.modal.Character
import com.softcross.onepiece.core.network.dto.character.CharacterResponse
import retrofit2.Response
import javax.inject.Inject

class CharacterListMapper @Inject constructor() :
    OnePieceResponseListMapper<CharacterResponse, Character> {
    override fun map(input: Response<CharacterResponse>?): List<Character> {
        return input?.body()?.results?.map {
            Character(
                it.characterID.orEmpty(),
                it.characterName.orEmpty(),
                it.characterStatus.orEmpty(),
                it.characterOrigin?.subLocationName.orEmpty(),
                it.characterCrew?.crewName.orEmpty(),
                it.characterOccupation?.occupationName.orEmpty(),
                it.characterBounty.orEmpty(),
                it.characterDevilFruit?.devilFruitName.orEmpty(),
                it.characterAbilities.orEmpty(),
                it.characterPicture.orEmpty()
            )
        } ?: throw Exception("Characters not found")
    }

}

