package models.crafting;

import models.items.Entity;
import models.items.workstations.WorkStation;

import java.util.ArrayList;

public class Recipe {
    ArrayList<Entity> ingredients;
    private boolean isUnlocked;
    private WorkStation workStation;
}
