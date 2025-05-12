package views;

import models.App;
import org.jline.terminal.Attributes;
import org.jline.terminal.Size;
import org.jline.terminal.Terminal;
import org.jline.terminal.TerminalBuilder;
import views.inGame.Renderer;

import java.io.IOException;
import java.util.Observable;
import java.util.Scanner;

public class AppView {
    private final Scanner scanner;
    private final Terminal terminal;
    private Size terminalSize;
    private final Renderer renderer;
    //false is the typical terminal mode
    private boolean rawMode = false;

    public AppView(){
        try{
            terminal = TerminalBuilder.builder().system(true)
                    .jna(false)
                    .build();
            terminal.handle(Terminal.Signal.WINCH, new Terminal.SignalHandler() {
                @Override
                public void handle(Terminal.Signal signal) {
                    terminalSize = terminal.getSize();
                    renderer.updateSize();
                }
            });
            terminalSize = terminal.getSize();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        scanner = new Scanner(System.in);
        renderer = new Renderer(this);
    }
    public void run() {
        while(!App.shouldTerminate) {
            App.getCurrentMenu().checker(scanner);
        }
    }

    public boolean isRawMode() {
        return rawMode;
    }

    public void switchInputType() {
        try{
            if(this.rawMode){
                Attributes attributes = terminal.getAttributes();
                this.terminal.close();
            }else{
                terminal.enterRawMode();
            }
            this.rawMode = !this.rawMode;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public String inputWithPrompt(String prompt){
        System.out.println(prompt);
        return scanner.nextLine();
    }
    public void log(String string){
        System.out.println(string);
    }
    public void err(String string) {
        System.err.println(string);
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

    public Renderer getRenderer() {
        return renderer;
    }

    public Scanner getScanner(){
        return scanner;
    }
}
