package com.example.foodie

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Insert
import androidx.room.Delete
import androidx.room.OnConflictStrategy

@Dao
interface IngredientDao {
    @Query("SELECT * FROM ingredients")
    fun getAll(): List<IngredientEntity>

    @Query("SELECT * FROM ingredients WHERE recipe_id = :recipeId")
    fun findByRecipeId(recipeId: Int): List<IngredientEntity>

    @Query("SELECT ingredient_id FROM ingredients WHERE recipe_id = :recipeId")
    fun findIngredientsIdsByRecipeId(recipeId: Int): List<Int>

    @Query("SELECT ingredient_id FROM ingredients WHERE recipe_id IN (:recipeIds)")
    fun findIngredientsByRecipeIds(recipeIds: List<Int>): MutableList<Int>


    @Query("SELECT * FROM ingredients WHERE ingredient_title IN (:ingredientsAvailable)")
    fun filterIngredientsByTitle(ingredientsAvailable: List<String>): List<IngredientEntity>

    @Query("SELECT * FROM ingredients WHERE ingredient_id IN (:ingredientIds)")
    fun getIngredientsByIds(ingredientIds: List<Int>): MutableList<IngredientEntity>

    @Query("SELECT * FROM ingredients WHERE ingredient_title LIKE '%' || :ingredientTitle || '%'")
    fun findIngredientByTitleFraction(ingredientTitle: String): IngredientEntity

    @Query("""
        SELECT recipe_id
        FROM ingredients
        WHERE ingredient_id IN (:ingredientIds)
        GROUP BY recipe_id
    """)
    fun findRecipeIdsWithMathches(ingredientIds: MutableList<Int>): MutableList<Int>


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(ingredient: IngredientEntity)

    @Delete
    fun delete(ingredient: IngredientEntity)
}
