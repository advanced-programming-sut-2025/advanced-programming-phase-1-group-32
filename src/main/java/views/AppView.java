package views;

import models.App;
import org.jline.terminal.Size;
import org.jline.terminal.Terminal;
import org.jline.terminal.TerminalBuilder;

import java.io.IOException;
import java.util.Scanner;

public class AppView {
    private final Scanner scanner;
    private final Terminal terminal;
    private Size terminalSize;

    public AppView(){
        try{
            terminal = TerminalBuilder.builder().system(true)
                    .jna(false)
                    .build();
            terminal.handle(Terminal.Signal.WINCH, new Terminal.SignalHandler() {
                @Override
                public void handle(Terminal.Signal signal) {
                    terminalSize = terminal.getSize();
                }
            });
            terminalSize = terminal.getSize();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        scanner = new Scanner(System.in);
    }
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
    public int getTerminalHeight(){
        return terminalSize.getRows();
    }
    public int getTerminalWidth(){
        return terminalSize.getColumns();
    }
    public Terminal getTerminal(){
        return terminal;
    }
}
