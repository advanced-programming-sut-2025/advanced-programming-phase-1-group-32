package src.main.java.models.crafting;

import src.main.java.models.items.Item;
import src.main.java.models.items.workstations.WorkStation;

import java.util.ArrayList;

public class Recipe {
    ArrayList<Item> ingredients;
    private boolean isUnlocked;
    private WorkStation workStation;
}
