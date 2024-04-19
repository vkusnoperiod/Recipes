package com.example.foodie

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.constraintlayout.widget.ConstraintSet
import androidx.core.view.marginTop
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView


class FavoritesActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_favorites)
        val exitImageView: ImageView = findViewById(R.id.favorite_recipes_imageView_exit_to_profile)
        var textViewYourList: TextView = findViewById(R.id.textView_your_list)
        val userId: Int = App.database.userDao().findByUsername(Session.currentUsername).userId
        // Setting up recyclerView
        var recipeRecyclerView: RecyclerView = findViewById(R.id.listOfFoundRecipesRecyclerView)
        recipeRecyclerView.layoutManager = LinearLayoutManager(this)
        val favoriteRecipesList: MutableList<RecipeEntity> =
            App.database.recipeDao()
                .getRecipesByIds(App.database.favoriteRecipeDao().findFavoriteRecipeByUserId(
                    App.database.userDao().findByUsername(Session.currentUsername).userId
                ).map { it.recipeId })
        var adapter = RecipeAdapter(favoriteRecipesList)
        val thisContext = this

        adapter.setOnItemClickListener(object: RecipeAdapter.onItemClickListener{
            override fun onItemClick(position: Int) {
                Session.currentRecipeId = favoriteRecipesList[position].recipeId
                val intent : Intent = Intent(thisContext, RecipeInformationActivity::class.java)
                startActivity(intent)
            }
        })
        recipeRecyclerView.adapter = adapter

        // Check if the list is empty
        if (favoriteRecipesList.isEmpty()) {
            textViewYourList.text = "Your list is empty."

            val toast = Toast.makeText(
                this,
                "You don't have favorite recipes yet. " +
                        "Hit a heart button in the recipe description to add one!",
                Toast.LENGTH_LONG
            )
            toast.show()

        }
        exitImageView.setOnClickListener {
            val intent: Intent = Intent(this, AuthenticatedActivity::class.java)
            startActivity(intent)
        }

    }
}
