package views;

import controllers.ForgotPasswordFlow;
import controllers.LoginMenuController;
import records.Result;
import models.Commands.LoginMenuCommands;

import java.io.IOException;
import java.util.Scanner;
import java.util.regex.Matcher;

public class LoginMenu implements AppMenu {
    private final LoginMenuController controller = new LoginMenuController();

    @Override
    public void checker(Scanner scanner) {
        Matcher matcher;
        String input = scanner.nextLine();

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
                        "this username? \"" + username + "\"\ntype \"go\" if you want to continue!");

                String answer = scanner.nextLine();
                if (!answer.equalsIgnoreCase("go")) return;
            }

            // suggest password if user wanted random
            if (password.equalsIgnoreCase("random")) {
                String answer;
                do {
                    password = controller.generatePassword();
                    System.out.println("Your password is: " + password + "\n type \"random\" for another password\n" +
                            "type \"go\" if you want to continue");

                    // get answers until valid one
                    answer = scanner.nextLine();
                    while (!answer.equalsIgnoreCase("go") && !answer.equalsIgnoreCase("random")) {
                        answer = scanner.nextLine();
                    }


                } while (!answer.equalsIgnoreCase("go"));

                passwordConfirm = password;
            }

            System.out.println(controller.register(username, password, passwordConfirm, name, email, gender));

        } else if ((matcher = LoginMenuCommands.PICK_QUESTION.getMatcher(input)) != null) {
            int questionNumber = Integer.parseInt(matcher.group("number"));
            String answer = matcher.group("answer");
            String answerConfirm = matcher.group("answerConfirm");

            System.out.println(controller.pickQuestion(questionNumber, answer, answerConfirm));
        } else if((matcher = LoginMenuCommands.LOGIN.getMatcher(input)) != null){
            String username = matcher.group("username");
            String password = matcher.group("password");
            boolean stayLogged = matcher.group("stayLogged") != null;

            System.out.println(this.controller.login(username, password, stayLogged));

        }else if((matcher = LoginMenuCommands.FORGOT_PASSWORD.getMatcher(input)) != null){
            ForgotPasswordFlow forgotPasswordFlow = new ForgotPasswordFlow();
            Result result = forgotPasswordFlow.handle(matcher.group("username"));
            while(result.isSuccessful()){
                System.out.println(result);
                result = forgotPasswordFlow.handle(scanner.nextLine());
            }
            System.out.println(result);
        }else if((matcher = LoginMenuCommands.LOAD_GAME.getMatcher(input)) != null){
            try {
                System.out.println(controller.loadGame());
            } catch (IOException e) {
                throw new RuntimeException(e);
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
        }else if((matcher = LoginMenuCommands.EXIT.getMatcher(input)) != null){
            this.controller.exit();
        }

        if(matcher == null) {
            System.out.println("invalid command");
        }
    }
}
