package com.example.tipper

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.model.Recipe
import com.example.utils.RecipeUtil
//Autor Karol Kuchnio s21912
class ActivityRecipe : AppCompatActivity() {

    var recyclerView: RecyclerView? = null
    var recipeListAdapter: RecipeListAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recipe)
        recyclerView = findViewById<RecyclerView>(R.id.recipe_recycler_view)
        recyclerView!!.layoutManager = LinearLayoutManager(this)
        val recipeList: List<Recipe>? = RecipeUtil.loadRecipesFromFile(this, "recipes.json")
        if (recipeList != null) {
            recipeListAdapter = RecipeListAdapter(recipeList)
            recyclerView!!.adapter = recipeListAdapter
        }
    }
}