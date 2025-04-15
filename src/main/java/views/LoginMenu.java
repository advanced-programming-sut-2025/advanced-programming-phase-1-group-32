package views;

import controllers.LoginMenuController;
import models.Result;

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
