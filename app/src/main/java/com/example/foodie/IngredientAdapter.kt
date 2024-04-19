package com.example.foodie

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView


class IngredientAdapter(private val fridgeList: MutableList<FridgeEntity>) :
    RecyclerView.Adapter<IngredientAdapter.IngredientViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): IngredientViewHolder {

        val itemView = LayoutInflater.from(parent.context).inflate(
            R.layout.ingredient_fridge_item,
            parent, false
        )
        return IngredientViewHolder(itemView, ingredientListener)

    }

    private lateinit var ingredientListener: onItemClickListener

    interface onItemClickListener {
        fun onItemClick(position: Int)
    }

    fun setOnItemClickListener(listener: onItemClickListener) {

        ingredientListener = listener
    }

    override fun getItemCount(): Int {
        return fridgeList.size
    }

    override fun onBindViewHolder(holder: IngredientViewHolder, position: Int) {
        val currentItem = fridgeList[position]

        holder.ingredientTitle.text = App.database.fridgeDao()
            .getIngredientItemByIngredientId(currentItem.ingredientId).ingredientTitle
    }
    fun removeAt(position: Int) {
        fridgeList.removeAt(position)
        notifyItemRemoved(position)
        notifyItemRangeChanged(position, fridgeList.size)
    }
    fun insert(fridgeEntity: FridgeEntity) {
        fridgeList.add(fridgeEntity)
        notifyDataSetChanged()
    }

    class IngredientViewHolder(itemView: View, listener: onItemClickListener) :
        RecyclerView.ViewHolder(itemView) {
        val ingredientTitle: TextView = itemView.findViewById(R.id.ingredient_fridge_title)

        init {
            itemView.setOnClickListener {
                listener.onItemClick(adapterPosition)
            }
        }
    }
}