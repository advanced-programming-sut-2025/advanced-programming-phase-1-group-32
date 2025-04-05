package views;

import controllers.LoginMenuController;
import models.App;
import models.Result;

import javax.swing.plaf.IconUIResource;
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
