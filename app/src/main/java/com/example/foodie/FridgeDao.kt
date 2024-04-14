package com.example.foodie

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Insert
import androidx.room.Delete
import androidx.room.OnConflictStrategy

@Dao
interface FridgeDao {
    @Query("SELECT * FROM fridge")
    fun getAll(): List<FridgeEntity>

    @Query("SELECT * FROM fridge WHERE user_id = :userId")
    fun findIngredientsByUserId(userId: Int): List<FridgeEntity>
    @Query("SELECT COALESCE(MAX(fridge_product_id), -1) FROM fridge")
    fun getLastFridgeProductId(): Int

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(fridge: FridgeEntity)

    @Delete
    fun delete(fridge: FridgeEntity)
}

