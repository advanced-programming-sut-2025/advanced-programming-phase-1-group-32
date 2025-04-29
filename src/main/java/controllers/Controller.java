package controllers;

import models.App;
import records.Result;

public interface Controller {
    public Result changeMenu(String menuName);
    default void exit() {
        App.shouldTerminate = true;
    }

    default Result showCurrentMenu() {
        return new Result(true, App.getCurrentMenu().toString());
    }
}
