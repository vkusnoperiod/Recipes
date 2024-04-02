package com.example.foodie
import androidx.room.Dao
import androidx.room.Query
import androidx.room.Insert
import androidx.room.Delete
import androidx.room.Update
import androidx.room.OnConflictStrategy
@Dao
interface IngredientDao {
    @Query("SELECT * FROM ingredients")
    fun getAll(): List<IngredientEntity>

    @Query("SELECT * FROM ingredients WHERE recipe_id = :recipeId")
    fun findByRecipeId(recipeId: Int): List<IngredientEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(ingredient: IngredientEntity)

    @Delete
    fun delete(ingredient: IngredientEntity)
}
