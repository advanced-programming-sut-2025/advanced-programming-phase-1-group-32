package views;

import controllers.LoginMenuController;
import models.Result;
import models.enums.LoginMenuCommands;

import java.util.Scanner;
import java.util.regex.Matcher;

public class LoginMenu implements AppMenu {
    private final LoginMenuController controller = new LoginMenuController();

    @Override
    public void checker(Scanner scanner) {
        Matcher matcher;
        String input = scanner.nextLine();
        if((matcher = LoginMenuCommands.LOGIN.getMatcher(input)) != null){
            String username = matcher.group("username");
            String password = matcher.group("password");
            boolean stayLogged = matcher.group("stayLogged") != null;

            System.out.println(this.controller.login(username, password, stayLogged));
        }else if((matcher = LoginMenuCommands.EXIT.getMatcher(input)) != null){
            this.controller.exit();
        }

        if(matcher == null){
            System.out.println("invalid command");
        }
    }
}
