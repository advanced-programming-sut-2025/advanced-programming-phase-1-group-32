package controllers;

import models.*;
import models.enums.Menu;
import models.player.Player;
import views.GameMenu;

public class MainMenuController implements Controller{
    @Override
    public Result changeMenu(String menuName) {
        Menu menu = Menu.getMenu(menuName);
        if(menu == null)
            return new Result(false, "This menu doesn't exist");
        if (menu.equals(Menu.MAIN_MENU))
            return new Result(false, "you are already in Main Menu");
        if (menu.equals(Menu.GAME_MENU))
            return new Result(false, "Use \"game new -u <username1> [username2] [username3]\"\n to enter this menu");
        App.setCurrentMenu(menu);
        return new Result(false, "You are in " + menu + " now");
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

    public Result newGame(String input) {
        String[] usernames = input.split("\\s");
        if(usernames.length == 0)
            return new Result(false, "you should enter at least  1 username");
        if(usernames.length > 3)
            return new Result(false, "You can't play with more than players");
        Account[] accounts = new Account[usernames.length + 1];
        accounts[0] = App.getLoggedInAccount();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < usernames.length; i++) {

            accounts[i + 1] = App.getUserByUsername(usernames[i]);
            if (accounts[i + 1] == null)
                sb.append("username ").append(usernames[i]).append(" doesn't exist\n");
        }
        if(!sb.isEmpty())
            return new Result(false, sb.deleteCharAt(sb.length() - 1).toString());

        Game game = new Game();
        for (Account account : accounts) {
//            if(account.getActiveGame() != null)
//                return new Result(false, "user " + account.getUsername() + " is already in a game.");
            game.addPlayer(new Player());
            game.setActiveMap(new GameMap(1000, 1000));
            account.setActiveGame(game);
        }
        game.setCurrentPlayer(game.getPlayers().get(0));
        App.setCurrentMenu(Menu.GAME_MENU);
        return new Result(true, "Game started!, You are in Game Menu now!");

    }

    public Result loadGame() {
        //TODO
        return null;
    }


}
