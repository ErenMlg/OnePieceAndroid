package com.softcross.onepiece.core.data.mapper

import com.softcross.onepiece.core.common.mapper.OnePieceResponseListMapper
import com.softcross.onepiece.core.data.entity.DevilFruitEntity
import com.softcross.onepiece.core.network.dto.devilFruit.DevilFruitResponse
import retrofit2.Response
import javax.inject.Inject

class DevilFruitListMapper @Inject constructor(): OnePieceResponseListMapper<DevilFruitResponse, DevilFruitEntity> {
    override fun map(input: Response<DevilFruitResponse>?): List<DevilFruitEntity> {
        return input?.body()?.results?.map {
            DevilFruitEntity(
                it.devilFruitID.orEmpty(),
                it.devilFruitName.orEmpty(),
                it.devilFruitType.orEmpty(),
                it.devilFruitPictureURL.orEmpty()
            )
        } ?: throw Exception("Devil Fruits not found")
    }
}