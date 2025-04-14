package src.main.java.models.Quest;

import src.main.java.models.items.Item;

import java.util.ArrayList;

abstract public class Quest {
    protected final String name = null;
    protected final String description = null;
    protected final Character owner = null;
    protected final ArrayList<Item> rewards = null;
    protected boolean isCompleted;

    abstract void onComplete();
    abstract void isComplete();
}
