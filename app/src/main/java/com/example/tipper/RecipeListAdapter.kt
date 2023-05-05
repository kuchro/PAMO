package com.example.tipper

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.model.Recipe
//Autor Karol Kuchnio s21912
class RecipeListAdapter(private val recipeList: List<Recipe>) : RecyclerView.Adapter<RecipeListAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.recipe_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val recipe: Recipe = recipeList[position]
        holder.title.text = recipe.title
        holder.ingredients.text = recipe.ingredients
        holder.instructions.text = recipe.instructions
    }

    override fun getItemCount(): Int {
        return recipeList.size
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var title: TextView = itemView.findViewById(R.id.recipe_title)
        var ingredients: TextView = itemView.findViewById(R.id.recipe_ingredients)
        var instructions: TextView = itemView.findViewById(R.id.recipe_instructions)
    }
}