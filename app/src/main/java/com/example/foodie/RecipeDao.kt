package com.example.foodie
import androidx.room.Dao
import androidx.room.Query
import androidx.room.Insert
import androidx.room.Delete
import androidx.room.Update
import androidx.room.OnConflictStrategy
@Dao
interface RecipeDao {
    @Query("SELECT * FROM recipes")
    fun getAll(): List<RecipeEntity>

    @Query("SELECT * FROM recipes WHERE recipe_title LIKE '%' || :recipeTitle || '%'")
    fun findRecipeByTitleFraction(recipeTitle: String): MutableList<RecipeEntity>

    @Query("SELECT * FROM recipes \n" +
            "        WHERE recipe_id IN (:recipeIds) \n" +
            "        AND recipe_calories <= :maxCalories ")
    fun findRecipesByIds(recipeIds: List<Int>, maxCalories:Int): MutableList<RecipeEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(recipe: RecipeEntity)

    @Update
    fun update(recipe: RecipeEntity)

    @Delete
    fun delete(recipe: RecipeEntity)
}
