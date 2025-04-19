package views;

import models.App;

import java.util.Scanner;

public class AppView {
    public void run() {
        Scanner scanner = new Scanner(System.in);
        while(!App.shouldTerminate) {
            App.getCurrentMenu().checker(scanner);
        }
    }
}
