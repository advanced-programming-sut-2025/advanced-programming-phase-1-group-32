import models.App;
import views.AppView;
import views.inGame.Color;
import views.inGame.Renderer;

import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        //App.start();
        int x = 1, y = 1;
        Renderer renderer = new Renderer();
        Scanner scanner = new Scanner(System.in);

        App.getView().getTerminal().enterRawMode();
        while (true){
            int c = 0;
            try {
                if(App.getView().getTerminal().reader().peek(100) > 0){
                    c = App.getView().getTerminal().reader().read();
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            switch (c){
                case 'a':
                    x--;
                    break;
                case 's':
                    y++;
                    break;
                case 'w':
                    y--;
                    break;
                case 'd':
                    x++;
                    break;
                default:
                    break;
            }
            renderer.clear();
            System.out.println("" + App.getView().getTerminalWidth() + " " + App.getView().getTerminalHeight());
            for (int i = 0; i < 20; i++){
                for (int j = 0 ; j < 20; j++){
                    renderer.mvAddchColored(i + x, j + y, 'A', new Color(((double) i) / 20, ((double) j) / 20, 0.5));
                }
            }
        }
    }
}