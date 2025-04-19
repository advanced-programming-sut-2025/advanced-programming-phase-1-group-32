package models.enums;

import views.AppMenu;
import views.LoginMenu;
import views.MainMenu;

import java.util.Scanner;

public enum Menu {
    LOGIN_MENU(new LoginMenu()),
    MAIN_MENU(new MainMenu()),
    ;
    private final AppMenu menu;
    Menu(AppMenu menu){
        this.menu = menu;
    }

    public void checker(Scanner scanner){
        this.menu.checker(scanner);
    }
}
