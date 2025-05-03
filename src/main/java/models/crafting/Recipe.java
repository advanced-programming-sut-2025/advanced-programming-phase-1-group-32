package models.crafting;

import models.entities.Entity;

import java.util.ArrayList;
import java.util.HashMap;

public class Recipe {
    private final HashMap<String, Integer> ingredients;
    private boolean isUnlocked;
    public Recipe(boolean isUnlocked, HashMap<String, Integer> ingredients) {
        this.isUnlocked = isUnlocked;
        this.ingredients = new HashMap<>(ingredients);
    }
}
