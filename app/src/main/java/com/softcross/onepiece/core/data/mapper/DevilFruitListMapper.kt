package com.softcross.onepiece.core.data.mapper

import com.softcross.onepiece.core.common.mapper.OnePieceResponseListMapper
import com.softcross.onepiece.core.data.modal.DevilFruit
import com.softcross.onepiece.core.network.dto.devilFruit.DevilFruitResponse
import retrofit2.Response
import javax.inject.Inject

class DevilFruitListMapper @Inject constructor(): OnePieceResponseListMapper<DevilFruitResponse, DevilFruit> {
    override fun map(input: Response<DevilFruitResponse>?): List<DevilFruit> {
        return input?.body()?.results?.map {
            DevilFruit(
                it.devilFruitID.orEmpty(),
                it.devilFruitName.orEmpty(),
                it.devilFruitType.orEmpty(),
                it.devilFruitPictureURL.orEmpty()
            )
        } ?: throw Exception("Devil Fruits not found")
    }
}