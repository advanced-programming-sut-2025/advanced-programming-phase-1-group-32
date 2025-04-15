package models.crafting;

import models.items.Item;
import models.items.workstations.WorkStation;

import java.util.ArrayList;

public class Recipe {
    ArrayList<Item> ingredients;
    private boolean isUnlocked;
    private WorkStation workStation;
}
