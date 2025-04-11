package models.Quest;

import models.items.Item;

import java.util.ArrayList;

abstract public class Quest {
    protected final String name;
    protected final String description;
    protected final Character owner;
    protected final ArrayList<Item> rewards;
    protected boolean isCompleted;

    abstract void onComplete();
    abstract void isComplete();
}
