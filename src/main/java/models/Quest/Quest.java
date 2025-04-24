package models.Quest;

import models.entities.Entity;

import java.util.ArrayList;

abstract public class Quest {
    protected final String name = null;
    protected final String description = null;
    protected final Character owner = null;
    protected final ArrayList<Entity> rewards = null;
    protected boolean isCompleted;

    abstract void onComplete();
    abstract void isComplete();
}
