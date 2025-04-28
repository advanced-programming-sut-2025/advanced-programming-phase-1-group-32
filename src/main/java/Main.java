import models.App;
import views.AppView;
import views.inGame.Color;
import views.inGame.Renderer;

import java.util.Scanner;

public class    Main {
    public static void main(String[] args) {
        App.entityRegistry.loadEntities("./src/data/entities");
        App.entityRegistry.listEntities();
        System.out.println(App.entityRegistry.makeEntity("Axe").toString());
        (new AppView()).run();
    }
}