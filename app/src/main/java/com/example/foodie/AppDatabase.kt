package com.example.foodie
import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [RecipeEntity::class, IngredientEntity::class, UserEntity::class,
                     FridgeEntity::class, AllergyEntity::class,
                     FavoriteRecipeEntity::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun recipeDao(): RecipeDao

    abstract fun ingredientDao(): IngredientDao

    abstract fun userDao(): UserDao

    abstract fun favoriteRecipeDao(): FavoriteRecipeDao

    abstract fun fridgeDao(): FridgeDao

    abstract fun allergyDao(): AllergyDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "database"
                )
                    .createFromAsset("recipes.db")
                    .allowMainThreadQueries()
                    .build()
                INSTANCE = instance
                instance
            }
        }
    }
}

