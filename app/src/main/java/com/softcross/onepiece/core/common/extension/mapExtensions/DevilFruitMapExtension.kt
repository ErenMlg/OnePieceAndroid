package com.softcross.onepiece.core.common.extension.mapExtensions

import com.softcross.onepiece.core.data.modal.DevilFruit
import com.softcross.onepiece.core.local.entity.FavoriteDevilFruitEntity
import com.softcross.onepiece.core.network.dto.devilFruit.DevilFruitResponse
import retrofit2.Response

fun Response<DevilFruitResponse>.toDevilFruitList(): List<DevilFruit> {
    return body()?.results?.map {
        DevilFruit(
            it.devilFruitID.orEmpty(),
            it.devilFruitName.orEmpty(),
            it.devilFruitType.orEmpty(),
            it.devilFruitPictureURL.orEmpty()
        )
    } ?: throw Exception("Devil Fruits not found")
}

fun FavoriteDevilFruitEntity.toDevilFruit(): DevilFruit {
    return DevilFruit(
        id = id.toString(),
        devilFruitName = name,
        devilFruitType = type,
        devilFruitPictureURL = imageURL
    )
}

fun DevilFruit.toFavoriteDevilFruit(): FavoriteDevilFruitEntity {
    return FavoriteDevilFruitEntity(
        id = id.toInt(),
        name = devilFruitName,
        type = devilFruitType,
        imageURL = devilFruitPictureURL
    )
}