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
        onDelete = ForeignKey.CASCADE
    ),
        ForeignKey(
            entity = IngredientEntity::class,
            parentColumns = arrayOf("ingredient_id"),
            childColumns = arrayOf("ingredient_id"),
            onDelete = ForeignKey.CASCADE
        ),
        ForeignKey(
            entity = IngredientEntity::class,
            parentColumns = arrayOf("ingredient_title"),
            childColumns = arrayOf("ingredient_title"),
            onDelete = ForeignKey.CASCADE
        )],
    indices = [Index(value = ["ingredient_title"], unique = true)]
)
data class AllergiesEntity(
    @PrimaryKey(autoGenerate = true)

    @ColumnInfo("allergy_id")val allergyId: Int,

    @ColumnInfo("user_id")val userId: Int,

    @ColumnInfo("ingredient_id")val ingredientId: Int,

    @ColumnInfo("ingredient_title")val ingredientTitle: String

)

