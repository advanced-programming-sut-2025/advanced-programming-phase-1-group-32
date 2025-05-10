package models.NPC;

import models.App;
import models.Game;
import models.entities.Entity;
import models.enums.Season;
import models.enums.Weather;
import models.player.Player;

import java.util.ArrayList;

public class NPC {
    private String name;
    private ArrayList<Entity> favorites = new ArrayList<>();
//    private ArrayList<Quest> quests = new ArrayList<>();
    private ArrayList<Dialogue> dialogues = new ArrayList<>();


    public NPC() {};

    public String getName() {
        return name;
    }

    public ArrayList<Entity> getFavorites() {
        return favorites;
    }

    public void setFavorites(ArrayList<Entity> favorites) {
        this.favorites = favorites;
    }


    public ArrayList<Dialogue> getDialogues() {
        return dialogues;
    }

    public void setDialogues(ArrayList<Dialogue> dialogues) {
        this.dialogues = dialogues;
    }

    public void giveQuestToPlayer(Player player){

    }
    public String talk(Player player){
        return null;
    }

    public String getCorrectDialogue(Season season, int friendLevel, Weather weather, boolean isDay) {
        for (Dialogue dialogue : dialogues) {
            if (dialogue.checkConditions(season, friendLevel, weather, isDay)) {
                return dialogue.getDialogue();
            }
        }

        return null;
    }



}