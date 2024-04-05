package com.example.foodie.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.foodie.R
import com.example.foodie.adapters.FavoriteRecipeAdapter
import com.example.foodie.database.FavoriteRecipesDatabase

class FavoriteRecipesFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var favoriteRecipeAdapter: FavoriteRecipeAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_favorite_recipes, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recyclerView = view.findViewById(R.id.favorite_recipes_recyclerview)
        recyclerView.layoutManager = LinearLayoutManager(context)
        favoriteRecipeAdapter = FavoriteRecipeAdapter { recipe ->

            val fragment = RecipeInformationFragment().apply {
                arguments = Bundle().apply {
                    putInt("RECIPE_ID", recipe.id)
                }
            }
            requireActivity().supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, fragment)
                .addToBackStack(null)
                .commit()
        }
        recyclerView.adapter = favoriteRecipeAdapter

        loadFavoriteRecipes()
    }

    private fun loadFavoriteRecipes() {
        val favoriteRecipes = FavoriteRecipesDatabase.getInstance(requireContext()).favoriteRecipeDao().getAll()
        favoriteRecipeAdapter.submitList(favoriteRecipes)
    }
}
