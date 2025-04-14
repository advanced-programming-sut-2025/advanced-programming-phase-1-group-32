package src.main.java.controllers;

import src.main.java.models.Result;

public interface Controller {
    public Result changeMenu();
    default void exit() {
        //TODO
    }

    default Result showCurrentMenu() {
        //TODO
        return null;
    }
}
