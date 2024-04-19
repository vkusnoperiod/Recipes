package com.example.foodie

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface FavoriteRecipeDao {
    @Query("SELECT * FROM favorite_recipes")
    fun getAll(): List<FavoriteRecipeEntity>

    @Query("SELECT * FROM favorite_recipes WHERE user_id = :userId")
    fun findFavoriteRecipeByUserId(userId: Int): MutableList<FavoriteRecipeEntity>

    @Query("SELECT * FROM favorite_recipes WHERE recipe_id = :recipeId")
    fun findFavoriteRecipeByRecipeId(recipeId: Int): FavoriteRecipeEntity

    @Query("SELECT EXISTS(SELECT 1 FROM favorite_recipes WHERE recipe_id = :recipeId)")
    fun recipeExists(recipeId: Int): Boolean
    @Query("SELECT COALESCE(MAX(favorite_recipe_id), -1) FROM favorite_recipes")
    fun getLastFavoriteRecipeId(): Int
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(favoriteRecipe: FavoriteRecipeEntity)

    @Delete
    fun delete(favoriteRecipe: FavoriteRecipeEntity)

}
