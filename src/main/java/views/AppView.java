package views;

import models.App;

import java.util.Scanner;

public class AppView {
    private final Scanner scanner = new Scanner(System.in);
    public void run() {
        while(!App.shouldTerminate) {
            App.getCurrentMenu().checker(scanner);
        }
    }
    public String inputWithPrompt(String prompt){
        System.out.println(prompt);
        return scanner.nextLine();
    }
    public void log(String string){
        System.out.println(string);
    }
}
