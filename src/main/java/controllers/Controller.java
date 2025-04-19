package controllers;

import models.App;
import models.Result;

public interface Controller {
    public Result changeMenu();
    default void exit() {
        App.shouldTerminate = true;
    }

    default Result showCurrentMenu() {
        //TODO
        return null;
    }
}
