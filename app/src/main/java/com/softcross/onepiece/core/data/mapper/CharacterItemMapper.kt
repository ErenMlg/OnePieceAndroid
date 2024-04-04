package com.softcross.onepiece.core.data.mapper

import com.softcross.onepiece.core.common.mapper.OnePieceResponseItemMapper
import com.softcross.onepiece.core.data.entity.CharacterEntity
import com.softcross.onepiece.core.network.dto.character.CharacterDto
import retrofit2.Response
import javax.inject.Inject

class CharacterItemMapper @Inject constructor() :
    OnePieceResponseItemMapper<CharacterDto, CharacterEntity> {
    override fun map(input: Response<CharacterDto>?): CharacterEntity {
        return input?.body()?.run {
            CharacterEntity(
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