package models;

import models.entities.EntityRegistry;
import models.enums.Menu;
import views.AppView;

import java.util.ArrayList;

public class App {
    private final static ArrayList<Account> accountList = new ArrayList<>();
    private static Account loggedInAccount = null;
    private static Account registeredAccount = null;
    private static boolean stayLoggedIn   = false;
    private static Menu currentMenu = Menu.LOGIN_MENU;
    private static final AppView view = new AppView();
    public static boolean shouldTerminate = false;
    public static EntityRegistry entityRegistry = new EntityRegistry();

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

    public static Account getLoggedInAccount() {
        return loggedInAccount;
    }

    public static void setLoggedInAccount(Account loggedInAccount) {
        App.loggedInAccount = loggedInAccount;
    }

    public static void start(){
        view.run();
    }

    public static AppView getView(){
        return view;
    }

    public static Account getRegisteredAccount() {
        return registeredAccount;
    }

    public static void setRegisteredAccount(Account registeredAccount) {
        App.registeredAccount = registeredAccount;
    }

    public static void addAccount(Account account){
        accountList.add(account);
    }
}