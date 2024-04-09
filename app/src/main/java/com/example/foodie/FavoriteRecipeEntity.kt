package com.example.foodie

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(tableName = "favorite_recipes",
    foreignKeys = [
        ForeignKey(entity = UserEntity::class,
            parentColumns = arrayOf("user_id"),
            childColumns = arrayOf("user_id"),
            onDelete = ForeignKey.CASCADE),
        ForeignKey(entity = RecipeEntity::class,
            parentColumns = arrayOf("recipe_id"),
            childColumns = arrayOf("recipe_id"),
            onDelete = ForeignKey.CASCADE)
    ])
data class FavoriteRecipeEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "favorite_recipe_id")val favoriteRecipeId: Int,
    @ColumnInfo(name = "user_id")val userId: Int,
    @ColumnInfo(name = "recipe_id")val recipeId: Int
)
