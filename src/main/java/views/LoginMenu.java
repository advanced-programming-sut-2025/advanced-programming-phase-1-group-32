package src.main.java.views;

import src.main.java.controllers.LoginMenuController;
import src.main.java.models.Result;

import java.util.Scanner;

public class LoginMenu implements AppMenu {
    private final LoginMenuController controller = new LoginMenuController();

    @Override
    public void checker(Scanner scanner) {
        if(true) {
            Result result = controller.register();
            System.out.println();
            if(result.isSuccessful()) {
                //TODO
            }
        }
    }
}
