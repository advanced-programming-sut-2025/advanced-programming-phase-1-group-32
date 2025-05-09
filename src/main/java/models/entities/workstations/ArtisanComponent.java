package models.entities.workstations;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import models.App;
import models.crafting.Recipe;
import models.entities.Entity;
import models.entities.components.EntityComponent;
import records.Result;

import java.util.ArrayList;
import java.util.List;

public class ArtisanComponent extends EntityComponent {
    private final List<Recipe> recipes = new ArrayList<>();
    private ItemProcess activeProcess;

    @JsonCreator
    ArtisanComponent(@JsonProperty("recipes")
                     ArrayList<String> recipes) {

        recipes.forEach(name -> this.recipes.add(App.recipeRegistry.getRecipe(name)));
    }

    ArtisanComponent(List<Recipe> recipes, ItemProcess process) {
        this.recipes.addAll(recipes);
        this.activeProcess = process;
    }


    @Override
    public EntityComponent clone() {
        return new ArtisanComponent(new ArrayList<>(this.recipes), this.activeProcess);
    }

    @Override
    public String toString() {
        return "recipes : " + recipes + "\n";
    }

    public boolean isInProcess() {
        return activeProcess != null;
    }

    public Entity addProcess(String name) {
        for (Recipe recipe : recipes) {
            if(recipe.getName().equals(name)) {

                activeProcess = new ItemProcess()
            }
        }
    }




}
