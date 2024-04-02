package com.example.foodie
import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [RecipeEntity::class, IngredientEntity::class, UserEntity::class, FridgeEntity::class],
    version = 1,
    exportSchema = false // Установите true, если вы хотите сохранять схемы в папке версий
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun recipeDao(): RecipeDao
    abstract fun ingredientDao(): IngredientDao
    abstract fun userDao(): UserDao
    abstract fun fridgeDao(): FridgeDao
    // Остальные DAO по мере необходимости
}
