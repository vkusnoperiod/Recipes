package com.example.foodie

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface FavoriteRecipesDao {
    // Добавление любимого рецепта
    @Insert
    suspend fun addFavorite(favoriteRecipe: FavoriteRecipeEntity)

    // Удаление любимого рецепта
    @Query("DELETE FROM favorite_recipes WHERE userId = :userId AND recipeId = :recipeId")
    suspend fun removeFavorite(userId: Int, recipeId: Int)

    // Получение всех любимых рецептов пользователя
    @Query("SELECT * FROM favorite_recipes WHERE userId = :userId")
    suspend fun getFavoritesByUser(userId: Int): List<FavoriteRecipeEntity>


}
