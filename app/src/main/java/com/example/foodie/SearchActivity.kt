package com.example.foodie

import android.content.Intent
import androidx.fragment.app.Fragment
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.bottomnavigation.BottomNavigationView
import java.util.Locale


class SearchActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val intent = Intent(this, LoginActivity::class.java)
        startActivity(intent)
        setContentView(R.layout.fragment_search)
        val searchField: EditText = findViewById(R.id.searchInput_editText)
        var recipeRecyclerView: RecyclerView = findViewById(R.id.listOfFoundRecipesRecyclerView)
        recipeRecyclerView.layoutManager = LinearLayoutManager(this)
        val searchButton: Button = findViewById(R.id.button_start_search)
        var recipesFound:MutableList<RecipeEntity> = mutableListOf()
        searchButton.setOnClickListener{
            val inputTitle = searchField.text.toString().trim().lowercase(Locale.ROOT)
            recipesFound = App.database.recipeDao().findRecipeByTitleFraction(inputTitle)
            recipeRecyclerView.adapter = RecipeAdapter(recipesFound)
        }
    }
}
