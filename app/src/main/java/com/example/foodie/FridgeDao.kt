package com.example.foodie

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Insert
import androidx.room.Delete
import androidx.room.OnConflictStrategy

@Dao
interface FridgeDao {
    @Query("SELECT * FROM fridge")
    fun getAll(): MutableList<FridgeEntity>

    @Query("SELECT COALESCE(MAX(fridge_product_id), -1) FROM fridge")
    fun getLastFridgeProductId(): Int

    @Query("SELECT ingredient_id FROM fridge WHERE user_id = :userId")
    fun getFridgeProductsByUserId(userId: Int): MutableList<Int>


    @Query("SELECT * FROM ingredients WHERE ingredient_id = :ingredientId")
    fun getIngredientItemByIngredientId(ingredientId: Int): IngredientEntity

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(fridge: FridgeEntity)

    @Delete
    fun delete(fridge: FridgeEntity)
}

