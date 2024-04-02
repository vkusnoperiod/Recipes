package com.example.foodie

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.ColumnInfo

@Entity(tableName = "recipes")
data class RecipeEntity(
    @PrimaryKey
    @ColumnInfo(name = "recipe_id") val recipeId: Int,

    @ColumnInfo(name = "title") val title: String,

    @ColumnInfo(name = "image") val image: String,

    @ColumnInfo(name = "instructions") val instructions: String,

    @ColumnInfo(name = "category") val category: String,

    @ColumnInfo(name = "calories") val calories: Int
)
