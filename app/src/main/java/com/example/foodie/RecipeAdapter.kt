package com.example.foodie

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import com.google.android.material.imageview.ShapeableImageView


class RecipeAdapter(private val recipeList: MutableList<RecipeEntity>):
    RecyclerView.Adapter<RecipeAdapter.RecipeViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecipeViewHolder {

        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.recipe_found_item,
            parent, false)
        return RecipeViewHolder(itemView)

    }

    override fun getItemCount(): Int {
        return recipeList.size
    }

    override fun onBindViewHolder(holder: RecipeViewHolder, position: Int) {
        val currentItem = recipeList[position]

        // Используйте Picasso для загрузки изображения
        Picasso.get()
            .load(currentItem.recipeImage)
            .into(holder.recipeImage)

        holder.recipeTitle.text = currentItem.recipeTitle
    }
    class RecipeViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){

        val recipeImage : ShapeableImageView = itemView.findViewById(R.id.recipe_image)
        val recipeTitle : TextView = itemView.findViewById(R.id.recipe_title)

    }
}