package com.example.foodie

package com.example.foodie.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.foodie.R
import com.example.foodie.models.Recipe

class RecipeAdapter(
    private var recipes: List<Recipe>,
    private val onRecipeClicked: (Recipe) -> Unit
) : RecyclerView.Adapter<RecipeAdapter.RecipeViewHolder>() {

    class RecipeViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val imageView: ImageView = view.findViewById(R.id.recipeImage)
        val nameView: TextView = view.findViewById(R.id.recipeName)
        val availabilityIndicator: View = view.findViewById(R.id.availabilityIndicator)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecipeViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_recipe, parent, false)
        return RecipeViewHolder(view)
    }

    override fun onBindViewHolder(holder: RecipeViewHolder, position: Int) {
        val recipe = recipes[position]
        holder.nameView.text = recipe.name

        holder.availabilityIndicator.setBackgroundResource(
            if (recipe.canPrepareNow) R.drawable.bg_circle_green else R.drawable.bg_circle_red
        )
        holder.itemView.setOnClickListener { onRecipeClicked(recipe) }
    }

    override fun getItemCount() = recipes.size

    fun updateRecipes(newRecipes: List<Recipe>) {
        this.recipes = newRecipes
        notifyDataSetChanged()
    }
}
