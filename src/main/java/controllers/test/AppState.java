package controllers.test;

import models.Account;
import models.App;
import models.Game;
import models.crafting.RecipeRegistry;
import models.entities.EntityRegistry;
import models.enums.Menu;
import models.gameMap.MapRegistry;
import views.AppView;

import java.io.Serializable;
import java.util.ArrayList;

public class AppState implements Serializable {
    private static final long serialVersionUID = 1L;
    private ArrayList<Account> accountList;
    private Account loggedInAccount;
    private Account registeredAccount;
    private boolean stayLoggedIn  ;
    private Menu currentMenu;
//    private AppView view;
//    public EntityRegistry entityRegistry;
//    public RecipeRegistry recipeRegistry;
//    public MapRegistry mapRegistry;
    public Game activeGame;

    public AppState() {

    }
    public AppState(String s) {
        this.accountList = App.getAccountList();
        this.loggedInAccount = App.getLoggedInAccount();
        this.registeredAccount = App.getRegisteredAccount();
        this.stayLoggedIn = App.isStayLoggedIn();
        this.currentMenu = App.getCurrentMenu();
//        this.view = App.getView();
//        this.entityRegistry = App.entityRegistry;
//        this.recipeRegistry = App.recipeRegistry;
//        this.mapRegistry = App.mapRegistry;
        this.activeGame = App.activeGame;
    }

    public ArrayList<Account> getAccountList() {
        return accountList;
    }

    public void setAccountList(ArrayList<Account> accountList) {
        this.accountList = accountList;
    }

    public Account getLoggedInAccount() {
        return loggedInAccount;
    }

    public void setLoggedInAccount(Account loggedInAccount) {
        this.loggedInAccount = loggedInAccount;
    }

    public Account getRegisteredAccount() {
        return registeredAccount;
    }

    public void setRegisteredAccount(Account registeredAccount) {
        this.registeredAccount = registeredAccount;
    }

    public boolean isStayLoggedIn() {
        return stayLoggedIn;
    }

    public void setStayLoggedIn(boolean stayLoggedIn) {
        this.stayLoggedIn = stayLoggedIn;
    }

    public Menu getCurrentMenu() {
        return currentMenu;
    }

    public void setCurrentMenu(Menu currentMenu) {
        this.currentMenu = currentMenu;
    }
//
//    public AppView getView() {
//        return view;
//    }
//
//    public void setView(AppView view) {
//        this.view = view;
//    }
//
//
//
//    public EntityRegistry getEntityRegistry() {
//        return entityRegistry;
//    }
//
//    public void setEntityRegistry(EntityRegistry entityRegistry) {
//        this.entityRegistry = entityRegistry;
//    }
//
//    public RecipeRegistry getRecipeRegistry() {
//        return recipeRegistry;
//    }
//
//    public void setRecipeRegistry(RecipeRegistry recipeRegistry) {
//        this.recipeRegistry = recipeRegistry;
//    }

//    public MapRegistry getMapRegistry() {
//        return mapRegistry;
//    }
//
//    public void setMapRegistry(MapRegistry mapRegistry) {
//        this.mapRegistry = mapRegistry;
//    }

    public Game getActiveGame() {
        return activeGame;
    }

    public void setActiveGame(Game activeGame) {
        this.activeGame = activeGame;
    }
}
