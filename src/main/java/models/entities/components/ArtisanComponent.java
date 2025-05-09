package models.entities.components;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.jsonschema.JsonSerializableSchema;
import models.App;
import models.crafting.Recipe;

import java.util.ArrayList;
import java.util.List;

public class ArtisanComponent {
    private List<Recipe> recipes;

    @JsonCreator
    ArtisanComponent(@JsonProperty("recipes")
                     ArrayList<String> recipes) {
        recipes.forEach(name -> this.recipes.add(App.recipeRegistry.getRecipe(name)));
    }





}
