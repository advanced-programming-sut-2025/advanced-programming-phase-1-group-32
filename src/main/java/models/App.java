package models;

import models.enums.Menu;

import java.util.ArrayList;

public class App {
    private final static ArrayList<Account> accountList = new ArrayList<>();
    private static Account currentAccount = null;
    private static boolean stayLoggedIn   = false;
    private static Menu currentMenu = Menu.LOGIN_MENU;
    public static boolean shouldTerminate = false;

    /***
     * Returns null if the username doesn't exist.
     */
    public static Account getUserByUsername(String username){
        for(Account a : accountList){
            if(a.getUsername().equals(username)){
                return a;
            }
        }
        return null;
    }

    public static Account getCurrentAccount() {
        return currentAccount;
    }

    public static void setCurrentAccount(Account currentAccount) {
        App.currentAccount = currentAccount;
    }

    public static boolean getStayLoggedIn() {
        return stayLoggedIn;
    }

    public static void setStayLoggedIn(boolean stayLoggedIn) {
        App.stayLoggedIn = stayLoggedIn;
    }

    public static Menu getCurrentMenu() {
        return currentMenu;
    }

    public static void setCurrentMenu(Menu currentMenu) {
        App.currentMenu = currentMenu;
    }

    public static boolean doesUsernameExist(String username){
        return getUserByUsername(username) != null;
    }
}