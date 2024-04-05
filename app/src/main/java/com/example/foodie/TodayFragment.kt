package com.example.foodie.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.foodie.R
import com.example.foodie.adapters.RecipeAdapter
import com.example.foodie.models.FridgeItem
import com.example.foodie.models.Recipe
import com.example.foodie.database.AppDatabase
import com.google.android.material.chip.Chip
import com.google.android.material.chip.ChipGroup

class TodayFragment : Fragment() {

    private lateinit var fridgeChipGroup: ChipGroup
    private lateinit var autoCompleteTextView: AutoCompleteTextView
    private lateinit var recipesRecyclerView: RecyclerView
    private lateinit var matchRecipesButton: Button
    private lateinit var caloriesTextView: TextView
    private val fridgeItems = mutableListOf<FridgeItem>()
    private val matchedRecipes = mutableListOf<Recipe>()
    private lateinit var recipeAdapter: RecipeAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_today, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        fridgeChipGroup = view.findViewById(R.id.fridge_chip_group)
        autoCompleteTextView = view.findViewById(R.id.auto_complete_product)
        recipesRecyclerView = view.findViewById(R.id.recipes_recyclerview)
        matchRecipesButton = view.findViewById(R.id.match_recipes_button)
        caloriesTextView = view.findViewById(R.id.calories_text_view)

        setupAutoCompleteTextView()
        setupRecipesRecyclerView()
        matchRecipesButton.setOnClickListener { matchRecipes() }


        displayFridgeItems()
    }

    private fun setupAutoCompleteTextView() {

        val productNames = getProductNames()
        val adapter = ArrayAdapter(requireContext(), android.R.layout.simple_dropdown_item_1line, productNames)
        autoCompleteTextView.setAdapter(adapter)


        autoCompleteTextView.setOnItemClickListener { _, _, position, _ ->
            val productName = adapter.getItem(position) ?: return@setOnItemClickListener
            val fridgeItem = FridgeItem(productName)
            fridgeItems.add(fridgeItem)
            addChipToFridgeGroup(fridgeItem)
            autoCompleteTextView.text = null
        }
    }

    private fun setupRecipesRecyclerView() {
        recipesRecyclerView.layoutManager = LinearLayoutManager(context)
        recipeAdapter = RecipeAdapter(matchedRecipes) { recipe ->
            // TODO: Handle recipe click, possibly opening RecipeInformationFragment
        }
        recipesRecyclerView.adapter = recipeAdapter
    }

    private fun displayFridgeItems() {
        fridgeItems.forEach { addChipToFridgeGroup(it) }
    }

    private fun addChipToFridgeGroup(fridgeItem: FridgeItem) {
        val chip = Chip(context).apply {
            text = fridgeItem.name
            isCloseIconVisible = true
            setOnCloseIconClickListener {
                fridgeItems.remove(fridgeItem)
                fridgeChipGroup.removeView(this)
            }
        }
        fridgeChipGroup.addView(chip)
    }

    private fun matchRecipes() {

    }

    private fun getProductNames(): List<String> {

    }
}
