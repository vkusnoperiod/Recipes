package com.example.foodie
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import androidx.room.ColumnInfo

@Entity(
    tableName = "ingredients",
    foreignKeys = [ForeignKey(
        entity = RecipeEntity::class,
        parentColumns = arrayOf("recipe_id"),
        childColumns = arrayOf("recipe_id"),
        onDelete = ForeignKey.CASCADE
    )]
)
data class IngredientEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "ingredient_id") val ingredientId: Int,

    @ColumnInfo(name = "recipe_id") val recipeId: Int,

    @ColumnInfo(name = "ingredient") val ingredient: String
)

