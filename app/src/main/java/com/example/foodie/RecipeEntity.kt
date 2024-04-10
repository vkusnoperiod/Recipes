package com.example.foodie

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.ColumnInfo

@Entity(tableName = "recipes")
data class RecipeEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo("recipe_id")val recipeId: Int,

    @ColumnInfo("recipe_title")val recipeTitle: String,

    @ColumnInfo("recipe_image")val recipeImage: String?,

    @ColumnInfo("recipe_instruction")val recipeInstruction: String,

    @ColumnInfo("recipe_category")val recipeCategory: String?,

    @ColumnInfo("recipe_calories")val recipeCalories: Int?,

    )
