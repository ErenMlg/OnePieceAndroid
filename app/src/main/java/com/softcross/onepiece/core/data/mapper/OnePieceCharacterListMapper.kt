package com.softcross.onepiece.core.data.mapper

import com.softcross.onepiece.core.common.mapper.OnePieceResponseListMapper
import com.softcross.onepiece.core.data.entity.CharacterEntity
import com.softcross.onepiece.core.network.dto.character.CharacterResponse
import retrofit2.Response
import javax.inject.Inject

class OnePieceCharacterListMapper @Inject constructor() :
    OnePieceResponseListMapper<CharacterResponse, CharacterEntity> {
    override fun map(input: Response<CharacterResponse>?): List<CharacterEntity> {
        return input?.body()!!.results.map {
            CharacterEntity(
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
        }
    }

}

