package main.java.controllers;

import main.java.models.Result;

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
