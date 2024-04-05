package com.example.foodie.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.foodie.R
import com.example.foodie.database.RecipesDatabase

class RecipeInformationFragment : Fragment() {


    private lateinit var recipe: Recipe

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.fragment_recipe_information, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        val recipeId = arguments?.getInt("RECIPE_ID") ?: return
        val recipeDatabase = RecipesDatabase.getInstance(requireContext())
        recipe = recipeDatabase.recipeDao().getRecipeById(recipeId)


        val recipeNameTextView = view.findViewById<TextView>(R.id.recipe_name)
        recipeNameTextView.text = recipe.name


        val caloriesTextView = view.findViewById<TextView>(R.id.recipe_calories)
        caloriesTextView.text = recipe.calories.toString()


        view.findViewById<Button>(R.id.open_full_recipe_button).setOnClickListener {

        }

        view.findViewById<Button>(R.id.add_to_today_button).setOnClickListener {

        }

        view.findViewById<Button>(R.id.add_to_favorites_button).setOnClickListener {

        }
    }


}
