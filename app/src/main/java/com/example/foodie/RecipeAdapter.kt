package com.example.foodie

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import com.google.android.material.imageview.ShapeableImageView


class RecipeAdapter(private val recipeList: MutableList<RecipeEntity>) :
    RecyclerView.Adapter<RecipeAdapter.RecipeViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecipeViewHolder {

        val itemView = LayoutInflater.from(parent.context).inflate(
            R.layout.recipe_found_item,
            parent, false
        )
        return RecipeViewHolder(itemView, recipeListener)

    }

    private lateinit var recipeListener: onItemClickListener

    interface onItemClickListener {
        fun onItemClick(position: Int)
    }

    fun setOnItemClickListener(listener: onItemClickListener) {


        recipeListener = listener

    }

    override fun getItemCount(): Int {
        return recipeList.size
    }

    override fun onBindViewHolder(holder: RecipeViewHolder, position: Int) {
        val currentItem = recipeList[position]

        // Using Picasso for image upload
        Picasso.get()
            .load(currentItem.recipeImage)
            .into(holder.recipeImage)
        holder.recipeTitle.text = currentItem.recipeTitle
        var ingredientsAvailable: MutableList<Int> = App.database.fridgeDao()
            .getFridgeProductsByUserId(
                App.database.userDao().findByUsername(Session.currentUsername).userId
            )
        var ingredientsRequired: List<Int> = App.database.ingredientDao().findIngredientsIdsByRecipeId(currentItem.recipeId)
        if(hasCommonElement(ingredientsAvailable, ingredientsRequired)){
            holder.isAvailable.setBackgroundColor(Color.parseColor("#24DA10"))
        }
        else{
            holder.isAvailable.setBackgroundColor(Color.parseColor("#ED0808"))
        }
    }

    class RecipeViewHolder(itemView: View, listener: onItemClickListener) :
        RecyclerView.ViewHolder(itemView) {

        val recipeImage: ShapeableImageView = itemView.findViewById(R.id.recipe_image)
        val recipeTitle: TextView = itemView.findViewById(R.id.recipe_title)
        val isAvailable: Button = itemView.findViewById(R.id.found_item_button_available)

        init {
            itemView.setOnClickListener {
                listener.onItemClick(adapterPosition)
            }
        }
    }
    fun hasCommonElement(list1: List<Int>, list2: List<Int>): Boolean {
        val set1 = list1.toSet()
        val set2 = list2.toSet()
        val commonElements = set1.intersect(set2)
        return commonElements.size >= 1
    }

}