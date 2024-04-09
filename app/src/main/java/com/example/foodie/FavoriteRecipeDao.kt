package com.example.foodie

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface FavoriteRecipesDao {
    @Query("SELECT * FROM fridge")
    fun getAll(): List<FridgeEntity>

    @Query("SELECT * FROM fridge WHERE user_id = :userId")
    fun findByUserId(userId: Int): List<FridgeEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(fridge: FridgeEntity)

    @Delete
    fun delete(fridge: FridgeEntity)

}
