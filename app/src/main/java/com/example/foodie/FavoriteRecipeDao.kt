package com.example.foodie

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface FavoriteRecipeDao {
    @Query("SELECT * FROM fridge")
    fun getAll(): List<FridgeEntity>

    @Query("SELECT * FROM favorite_recipes WHERE user_id = :userId")
    fun findFavoriteRecipeByUserId(userId: Int): List<FavoriteRecipeEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(fridge: FridgeEntity)

    @Delete
    fun delete(fridge: FridgeEntity)

}
