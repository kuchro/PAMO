package com.example.tipper;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.os.Bundle;

import com.example.model.Recipe;
import com.example.utils.RecipeUtil;

import java.util.List;
//Autor Karol Kuchnio s21912
public class ActivityRecipe  extends AppCompatActivity {

    private RecyclerView recyclerView;
    private RecipeListAdapter recipeListAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe);

        recyclerView = findViewById(R.id.recipe_recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        List<Recipe> recipeList = RecipeUtil.loadRecipesFromFile(this, "recipes.json");
        if (recipeList != null) {
            recipeListAdapter = new RecipeListAdapter(recipeList);
            recyclerView.setAdapter(recipeListAdapter);
        }
    }
}