package views;

import controllers.MainMenuController;
import models.Commands.MainMenuCommands;

import java.util.Scanner;
import java.util.regex.Matcher;

public class MainMenu implements AppMenu {
    private final MainMenuController controller = new MainMenuController();

    @Override
    public void checker(Scanner scanner) {
        String input = scanner.nextLine().trim();
        Matcher matcher;
        if ((matcher = MainMenuCommands.SHOW_CURRENT_MENU.getMatcher(input)) != null) {
            System.out.println(controller.showCurrentMenu());
        } else if ((matcher = MainMenuCommands.MENU_ENTER.getMatcher(input)) != null) {
            System.out.println(controller.changeMenu(matcher.group("menuName")));
        } else if ((matcher = MainMenuCommands.USER_LOGOUT.getMatcher(input)) != null) {
            System.out.println(controller.logout());
        } else if ((matcher = MainMenuCommands.SHOW_MENUS.getMatcher(input)) != null) {
            System.out.println(controller.showMenus());
        } else if ((matcher = MainMenuCommands.NEW_GAME.getMatcher(input)) != null) {
            System.out.println(controller.newGame(matcher.group("usernames")));
        } else if ((matcher = MainMenuCommands.LOAD_GAME.getMatcher(input)) != null) {
            System.out.println(controller.loadGame());
        } else {
            System.out.println("invalid command");
        }
    }
}
