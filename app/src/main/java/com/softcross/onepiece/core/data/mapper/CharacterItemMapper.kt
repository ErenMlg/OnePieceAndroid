package com.softcross.onepiece.core.data.mapper

import com.softcross.onepiece.core.common.mapper.OnePieceResponseItemMapper
import com.softcross.onepiece.core.data.modal.Character
import com.softcross.onepiece.core.network.dto.character.CharacterDto
import retrofit2.Response
import javax.inject.Inject

class CharacterItemMapper @Inject constructor() :
    OnePieceResponseItemMapper<CharacterDto, Character> {
    override fun map(input: Response<CharacterDto>?): Character {
        return input?.body()?.run {
            Character(
                characterID.orEmpty(),
                characterName.orEmpty(),
                characterStatus.orEmpty(),
                characterOrigin?.subLocationName.orEmpty(),
                characterCrew?.crewName.orEmpty(),
                characterOccupation?.occupationName.orEmpty(),
                characterBounty.orEmpty(),
                characterDevilFruit?.devilFruitName.orEmpty(),
                characterAbilities.orEmpty(),
                characterPicture.orEmpty()
            )
        } ?: throw Exception("Character not found")
    }


}