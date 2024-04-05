package com.example.foodie

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import android.widget.EditText
import androidx.recyclerview.widget.RecyclerView
import android.text.TextWatcher
import android.text.Editable

package com.example.foodie.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.foodie.R
import com.example.foodie.adapters.RecipeAdapter
import com.example.foodie.models.Recipe
import com.example.foodie.database.RecipeDatabase

class SearchFragment : Fragment() {

    private lateinit var recipeAdapter: RecipeAdapter
    private lateinit var searchView: SearchView
    private lateinit var recyclerView: RecyclerView
    private lateinit var recipeDatabase: RecipeDatabase

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_search, container, false)

        recyclerView = view.findViewById(R.id.recipesRecyclerView)
        searchView = view.findViewById(R.id.searchView)

        recyclerView.layoutManager = LinearLayoutManager(context)
        recipeAdapter = RecipeAdapter(listOf()) { recipe ->

        }
        recyclerView.adapter = recipeAdapter

        setupSearchView()

        return view
    }

    private fun setupSearchView() {
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                query?.let { searchRecipes(it) }
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                newText?.let { searchRecipes(it) }
                return false
            }
        })
    }

    private fun searchRecipes(query: String) {

        val results = recipeDatabase.searchRecipesByQuery(query)
        recipeAdapter.updateRecipes(results)
    }
}


