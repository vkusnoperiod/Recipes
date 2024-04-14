package com.example.foodie

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.ListView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [TodayFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class TodayFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_today, container, false)
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {


        super.onViewCreated(view, savedInstanceState)
        val searchField: EditText = view.findViewById(R.id.searchInput_editText)
        val fridgeView: ListView = view.findViewById(R.id.listOfIngredients)
        val addToFridgeButton:Button = view.findViewById(R.id.button_add_ingredient_to_fridge)
        var ingredientsAvailable:MutableList<String> = mutableListOf()
        val adapterFridge = ArrayAdapter(requireContext(),android.R.layout.simple_list_item_1, ingredientsAvailable)
        fridgeView.adapter = adapterFridge
        addToFridgeButton.setOnClickListener{
            val inputTitle = searchField.text.toString().trim()
            adapterFridge.add(inputTitle)
        }

        var recipeRecyclerView: RecyclerView = view.findViewById(R.id.listOfFoundRecipesRecyclerView)
        recipeRecyclerView.layoutManager = LinearLayoutManager(context)
        var calories_param:Int = 3000
        var caloriesField = view.findViewById<EditText>(R.id.calories_field)
        val searchButton:Button = view.findViewById(R.id.button_start_search)
        var recipesFound:MutableList<RecipeEntity> = mutableListOf()
        searchButton.setOnClickListener{
            calories_param = caloriesField.text.toString().toInt()
            val filteredIngredients = App.database.ingredientDao().filterIngredientsByTitle(ingredientsAvailable)

            val recipeIdsWithEnoughMatches = findRecipesWithEnoughMatches(filteredIngredients)

            var matchingRecipes = App.database.recipeDao().findRecipesByIds(recipeIdsWithEnoughMatches, calories_param)

            recipeRecyclerView.adapter = RecipeAdapter(matchingRecipes)
        }

        /*
        searchField.addTextChangedListener {
            fun afterTextChanged(s: Editable) {
                val foundRecipes:List<RecipeEntity> = App.database.recipeDao().findByTitle(s.toString())
                if(foundRecipes.isNotEmpty()){
                    Toast.makeText(context,"All good",Toast.LENGTH_SHORT).show()
                }
                if(foundRecipes.isNotEmpty()){
                    Toast.makeText(context,"All bad",Toast.LENGTH_SHORT).show()
                }
            }
        }
        */


    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment TodayFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            TodayFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
        fun findRecipesWithEnoughMatches(filteredIngredients: List<IngredientEntity>): List<Int> {
            val recipeCounts = filteredIngredients.groupBy { it.recipeId }
                .mapValues { (_, ingredients) -> ingredients.size }
                .filter { it.value >= 5  }
            return recipeCounts.keys.toList()
        }
    }
}