package com.example.foodie

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey
import androidx.room.ColumnInfo
@Entity(
    tableName = "allergies",
    foreignKeys = [ForeignKey(
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
data class AllergyEntity(
    @PrimaryKey(autoGenerate = true)

    @ColumnInfo("allergy_id")val allergyId: Int,

    @ColumnInfo("user_id")val userId: Int,

    @ColumnInfo("ingredient_id")val ingredientId: Int,

)

