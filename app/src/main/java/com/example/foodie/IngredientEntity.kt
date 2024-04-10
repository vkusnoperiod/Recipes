package com.example.foodie
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import androidx.room.ColumnInfo
import androidx.room.Index

@Entity(
    tableName = "ingredients",
    foreignKeys = [ForeignKey(
        entity = RecipeEntity::class,
        parentColumns = arrayOf("recipe_id"),
        childColumns = arrayOf("recipe_id"),
    )]
)
data class IngredientEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo("ingredient_id")val ingredientId: Int,

    @ColumnInfo("recipe_id")val recipeId: Int,

    @ColumnInfo("ingredient_title")val ingredient: String
)

