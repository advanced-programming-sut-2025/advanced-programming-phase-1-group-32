import models.App;
import views.AppView;
import views.inGame.CharacterTexture;
import views.inGame.Color;
import views.inGame.Renderer;
import views.inGame.StyledCharacter;

import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        /* load Jsons */
        loadDatas();
        /* Start game */
        App.start();
        int x = 1, y = 1;
        Scanner scanner = new Scanner(System.in);

        App.getView().getTerminal().enterRawMode();
        CharacterTexture map = new CharacterTexture(200, 50);
        for (int i = 0; i < 200; i++){
            for (int j = 0 ; j < 50; j++){
                if(Math.random() > 0.9){
                    map.data[j][i] = new StyledCharacter('A', new Color((int)(((double)i / 200) * 255), (int)(((double)j / 50) * 255), 0));
                }else{
                    map.data[j][i] = new StyledCharacter('A', null);
                }
            }
        }
        while (true){
            int c = 0;
            try {
                if(App.getView().getTerminal().reader().peek(1000) > 0){
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
            App.getView().getRenderer().clear();
            App.getView().getRenderer().render();
        }
    }

    private static void loadDatas() {
        App.getView().log("Loading Game...");
        /* should load recipes first (because artisan has recipes) */
        App.recipeRegistry.loadRecipes("./src/data/recipes");
        App.entityRegistry.loadEntities("./src/data/entities");
        /* to check is Json entities ok or not */
        App.recipeRegistry.checkIngredients();
//        App.entityRegistry.listEntities();

        App.getView().log("Done.");
    }
}