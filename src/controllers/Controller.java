package controllers;

import models.Result;

public interface Controller {
    public Result changeMenu();
    default void exit() {
        //TODO
    }

    default Result showCurrentMenu() {
        //TODO
    }
}
