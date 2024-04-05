package com.example.foodie

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

// Если используете структуру с foreign keys:
@Entity(tableName = "favorite_recipes",
    foreignKeys = [
        ForeignKey(entity = UserEntity::class,
            parentColumns = ["id"],
            childColumns = ["userId"],
            onDelete = ForeignKey.CASCADE),
        ForeignKey(entity = RecipeEntity::class,
            parentColumns = ["id"],
            childColumns = ["recipeId"],
            onDelete = ForeignKey.CASCADE)
    ])
data class FavoriteRecipeEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val userId: Int,
    val recipeId: Int
)
