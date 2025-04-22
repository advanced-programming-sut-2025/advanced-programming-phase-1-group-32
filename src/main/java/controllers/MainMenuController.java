package controllers;

import models.App;
import models.Result;
import models.enums.Menu;

public class MainMenuController implements Controller{
    @Override
    public Result changeMenu(String menuName) {
        Menu menu = Menu.getMenu(menuName);
        if(menu == null)
            return new Result(false, "this menu doesn't exist");
        if (menu.equals(Menu.MAIN_MENU))
            return new Result(false, "you are already in Main Menu");
        App.setCurrentMenu(menu);
        return new Result(false, "you are in " + menu + " now");
    }

    public Result logout() {
        if(changeMenu("Login Menu").isSuccessful()) {
            App.setLoggedInAccount(null);
            App.setCurrentMenu(Menu.LOGIN_MENU);
            return new Result(true, "user logged out successfully. you are in Login Menu now");
        }
        return new Result(false, "Something went wrong : couldn't logout");
    }

    public Result showMenus() {
        StringBuilder sb = new StringBuilder("Menus : \n");
        for (Menu menu : Menu.values()) {
            if(menu.equals(Menu.MAIN_MENU)) continue;
            sb.append(menu.toString()).append("\n");
        }
        return new Result(true, sb.toString());
    }

    public Result newGame() {
        //TODO
        return null;
    }

    public Result loadGame() {
        //TODO
        return null;
    }


}
