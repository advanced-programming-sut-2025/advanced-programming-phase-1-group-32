package models.NPC;

import models.entities.Entity;
import models.player.Player;

import java.util.ArrayList;

public class NPC {
    private final String name;
    private ArrayList<Entity> favorites;
    private ArrayList<Quest> quests;
    private ArrayList<String> dialogs;


    public NPC(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public ArrayList<Entity> getFavorites() {
        return favorites;
    }

    public void setFavorites(ArrayList<Entity> favorites) {
        this.favorites = favorites;
    }

    public ArrayList<Quest> getQuests() {
        return quests;
    }

    public void setQuests(ArrayList<Quest> quests) {
        this.quests = quests;
    }

    public ArrayList<String> getDialogs() {
        return dialogs;
    }

    public void setDialogs(ArrayList<String> dialogs) {
        this.dialogs = dialogs;
    }

    public void giveQuestToPlayer(Player player){

    }
    public String talk(Player player){
        return null;
    }


}