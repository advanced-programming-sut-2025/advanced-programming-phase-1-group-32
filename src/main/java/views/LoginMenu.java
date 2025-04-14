package main.java.views;

import main.java.controllers.LoginMenuController;
import main.java.models.Result;

import java.util.Scanner;

public class LoginMenu implements AppMenu {
    private final LoginMenuController controller = new LoginMenuController();

    @Override
    public void checker(Scanner scanner) {
        if(/*register*/) {
            Result result = controller.register();
            System.out.println();
            if(result.isSuccessful()) {
                //TODO
            }
        }
    }
}
