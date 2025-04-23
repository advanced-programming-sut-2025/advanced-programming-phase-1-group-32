import views.AppView;
import views.inGame.Color;
import views.inGame.Renderer;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        int x = 1, y = 1;
        Renderer renderer = new Renderer();
        Scanner scanner = new Scanner(System.in);

        while (true){
            char c = scanner.next().charAt(0);
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
            for (int i = 0; i < 20; i++){
                for (int j = 0 ; j < 20; j++){
                    renderer.mvAddchColored(i + x, j + y, 'A', new Color(((double) i) / 20, ((double) j) / 20, 0.5));
                }
            }
        }
    }
}