package views;

import controllers.LoginMenuController;
import models.Account;
import models.Result;
import models.enums.LoginMenuCommands;

import java.util.Scanner;
import java.util.regex.Matcher;

public class LoginMenu implements AppMenu {
    private final LoginMenuController controller = new LoginMenuController();

    @Override
    public void checker(Scanner scanner) {
        String input = scanner.nextLine();
        Matcher matcher;
        if ((matcher = LoginMenuCommands.REGISTER.getMatcher(input)) != null) {
            String username = matcher.group("username");
            String password = matcher.group("password");
            String passwordConfirm = matcher.group("passwordConfirm");
            String name = matcher.group("name");
            String email = matcher.group("email");
            String gender = matcher.group("gender");

            // check if username is new and suggest new one
            Result isNewUsername = controller.suggestUsername(username);
            if (!isNewUsername.isSuccessful()) {
                username = isNewUsername.message();
                System.out.println("you should choose a new username, do you want to continue with" +
                        "this username? \"" + username + "\"\ntype \"yes\" if you want to continue!");

                String answer = scanner.nextLine();
                if (!answer.equalsIgnoreCase("yes")) return;
            }

            // suggest password if user wanted random
            if (password.equalsIgnoreCase("random")) {
                password = Account.generatePassword();
                System.out.println("Your password is: " + password + "\n type \"random\" for another password");
                String answer = scanner.nextLine();
                while (answer.equalsIgnoreCase("random")) {
                    password = Account.generatePassword();
                    System.out.println("Your password is: " + password + "\n type \"random\" for another password");
                    answer = scanner.nextLine();
                }

                passwordConfirm = password;
            }

            System.out.println(controller.register(username, password, passwordConfirm, name, email, gender));

        }

    }
}
