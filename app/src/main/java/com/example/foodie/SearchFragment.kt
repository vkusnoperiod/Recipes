package com.example.foodie

import android.content.Intent
import androidx.fragment.app.Fragment
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import java.util.Locale

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [SearchFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class SearchFragment : Fragment() {
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
        return inflater.inflate(R.layout.fragment_search, container, false)
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        /*
        val searchField: EditText = view.findViewById(R.id.searchInput_editText)
        val recipesView:ListView = view.findViewById(R.id.listOfFoundRecipes)
        val searchButton:Button = view.findViewById(R.id.start_search_button)
        var recipesFound:MutableList<RecipeEntity> = mutableListOf()
        val adapter = ArrayAdapter(requireContext(),android.R.layout.simple_list_item_1, recipesFound)
        recipesView.adapter = adapter
        searchButton.setOnClickListener{
            val inputTitle = searchField.text.toString().trim().lowercase(Locale.ROOT)
            recipesFound = App.database.recipeDao().findByTitle(inputTitle)
            adapter.addAll(recipesFound)
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
        */
        super.onViewCreated(view, savedInstanceState)
        val searchField: EditText = view.findViewById(R.id.searchInput_editText)
        var recipeRecyclerView:RecyclerView = view.findViewById(R.id.listOfFoundRecipesRecyclerView)
        recipeRecyclerView.layoutManager = LinearLayoutManager(context)
        val searchButton:Button = view.findViewById(R.id.button_start_search)
        var recipesFound:MutableList<RecipeEntity> = mutableListOf()
        searchButton.setOnClickListener{
            val inputTitle = searchField.text.toString().trim().lowercase(Locale.ROOT)
            recipesFound = App.database.recipeDao().findRecipeByTitleFraction(inputTitle)
            var adapter = RecipeAdapter(recipesFound)
            adapter.setOnItemClickListener(object: RecipeAdapter.onItemClickListener{
                override fun onItemClick(position: Int) {
                    Session.currentRecipeId = recipesFound[position].recipeId
                    val intent : Intent = Intent(context, RecipeInformationActivity::class.java)
                    startActivity(intent)
                }
            })
            recipeRecyclerView.adapter = adapter
        }



    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment SearchFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            SearchFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}