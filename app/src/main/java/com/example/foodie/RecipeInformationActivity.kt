package com.example.foodie

import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.squareup.picasso.Picasso


class RecipeInformationActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recipe_information)
        val currentRecipe: RecipeEntity =
            App.database.recipeDao().getRecipeById(Session.currentRecipeId)
        val recipeInformationTextViewRecipeTitle: TextView =
            findViewById(R.id.recipe_information_textView_recipe_title)
        val recipeInformationTextViewRecipeInformation: TextView =
            findViewById(R.id.recipe_information_textView_recipe_information)
        val recipeInformationTextViewCaloriesNumber: TextView =
            findViewById(R.id.recipe_information_textView_calories_number)
        val recipeInformationImageViewRecipeImage: ImageView =
            findViewById(R.id.recipe_information_image_view_recipe_image)
        val recipeInformationImageViewAddToFavorites: ImageView =
            findViewById(R.id.recipe_information_imageView_add_to_favorites)
        val recipeInformationImageViewReturnToProfile:ImageView = findViewById(R.id.recipe_information_imageView_exit_to_profile)

        recipeInformationImageViewAddToFavorites.setOnClickListener {
            if (App.database.favoriteRecipeDao().recipeExists(Session.currentRecipeId)) {
                App.database.favoriteRecipeDao().delete(
                    FavoriteRecipeEntity(
                        App.database.favoriteRecipeDao().findFavoriteRecipeByRecipeId(Session.currentRecipeId).favoriteRecipeId,
                        App.database.userDao().findByUsername(Session.currentUsername).userId,
                        Session.currentRecipeId
                    )
                )
                val toast = Toast.makeText(
                    this,
                    "Item removed from favorites!",
                    Toast.LENGTH_LONG
                )
                toast.show()
            }
            else {
                val currentUserId: Int =
                    App.database.userDao().findByUsername(Session.currentUsername).userId
                val currentFavoriteRecipeId: Int =
                    App.database.favoriteRecipeDao().getLastFavoriteRecipeId() + 1
                App.database.favoriteRecipeDao().insert(
                    FavoriteRecipeEntity(
                        currentFavoriteRecipeId,
                        currentUserId,
                        Session.currentRecipeId
                    )
                )
                val toast = Toast.makeText(
                    this,
                    "Item added to favorites!",
                    Toast.LENGTH_LONG
                )
                toast.show()
            }
        }
        // Using Picasso for image upload
        Picasso.get()
            .load(currentRecipe.recipeImage)
            .into(recipeInformationImageViewRecipeImage)

        recipeInformationTextViewRecipeTitle.text = currentRecipe.recipeTitle
        recipeInformationTextViewRecipeInformation.text = currentRecipe.recipeInstruction
        recipeInformationTextViewCaloriesNumber.text = currentRecipe.recipeCalories.toString()

        recipeInformationImageViewReturnToProfile.setOnClickListener {
            val intent: Intent = Intent(this, AuthenticatedActivity::class.java)
            startActivity(intent)
        }

    }
}
