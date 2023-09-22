package com.qtproduct.sunflower.usecase

import com.qtproduct.sunflower.model.Plant
import java.util.UUID

/**
 * @author quangtran
 * @since 9/22/23
 */
class GetListPlantUseCase {
    val plants = listOf(
        Plant(
            id = UUID.randomUUID().toString(),
            name = "Apple",
            createAt = System.currentTimeMillis(),
            updateAt = System.currentTimeMillis()
        ),
        Plant(
            id = UUID.randomUUID().toString(),
            name = "Tomato",
            createAt = System.currentTimeMillis(),
            updateAt = System.currentTimeMillis()
        ),
        Plant(
            id = UUID.randomUUID().toString(),
            name = "Avocado",
            createAt = System.currentTimeMillis(),
            updateAt = System.currentTimeMillis()
        ),
        Plant(
            id = UUID.randomUUID().toString(),
            name = "Banana",
            createAt = System.currentTimeMillis(),
            updateAt = System.currentTimeMillis()
        ),
        Plant(
            id = UUID.randomUUID().toString(),
            name = "Mango",
            createAt = System.currentTimeMillis(),
            updateAt = System.currentTimeMillis()
        ), Plant(
            id = UUID.randomUUID().toString(),
            name = "Orange",
            createAt = System.currentTimeMillis(),
            updateAt = System.currentTimeMillis()
        )

    )

    fun invoke(): List<Plant> {
        return plants
    }
}