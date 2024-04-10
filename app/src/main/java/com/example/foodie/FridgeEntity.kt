package com.example.foodie

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import androidx.room.ColumnInfo
import androidx.room.Index

@Entity(
    tableName = "fridge",
    foreignKeys = [
        ForeignKey(
            entity = UserEntity::class,
            parentColumns = arrayOf("user_id"),
            childColumns = arrayOf("user_id"),

        ),
        ForeignKey(
            entity = IngredientEntity::class,
            parentColumns = arrayOf("ingredient_id"),
            childColumns = arrayOf("ingredient_id"),

        ), ]
    )
data class FridgeEntity(
    @PrimaryKey

    @ColumnInfo(name = "fridge_product_id") val fridgeProductId: Int,

    @ColumnInfo(name = "user_id") val userId: Int,

    @ColumnInfo(name = "ingredient_id") val ingredientId: Int

)

