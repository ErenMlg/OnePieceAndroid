package com.softcross.onepiece.core.common.extension.mapExtensions

import com.softcross.onepiece.core.data.modal.Character
import com.softcross.onepiece.core.local.entity.FavoriteCharacterEntity
import com.softcross.onepiece.core.network.dto.character.CharacterDto
import com.softcross.onepiece.core.network.dto.character.CharacterResponse
import retrofit2.Response


fun Response<CharacterResponse>.toCharacterList(): List<Character> {
    return body()?.results?.map {
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

fun Response<CharacterDto>.toCharacter(): Character {
    return body()?.run {
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

fun FavoriteCharacterEntity.toCharacter(): Character {
    return Character(
        id = id.toString(),
        characterName = name,
        characterStatus = status,
        characterOrigin = origin,
        characterCrew = crew,
        characterOccupation = occupation,
        characterBounty = bounty,
        characterDevilFruit = devilFruit,
        characterAbilities = abilities,
        characterPictureURL = imageURL
    )
}

fun Character.toFavoriteCharacter(): FavoriteCharacterEntity {
    return FavoriteCharacterEntity(
        id = id.toInt(),
        name = characterName,
        status = characterStatus,
        bounty = characterBounty,
        abilities = characterAbilities,
        crew = characterCrew,
        imageURL = characterPictureURL,
        devilFruit = characterDevilFruit,
        occupation = characterOccupation,
        origin = characterOrigin
    )
}