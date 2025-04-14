package src.main.java.models.NPC;

import src.main.java.models.Quest.Quest;
import src.main.java.models.Position;
import src.main.java.models.player.Player;

import java.util.ArrayList;
import java.util.Map;

public class Character {
    String name;
    private final Map<Integer, Position> schedule;
    private final ArrayList<Quest> quests;

    public Character(Map<Integer, Position> schedule, ArrayList<Quest> quests) {
        this.schedule = schedule;
        this.quests = quests;
    }

    public void giveQuestToPlayer(Player player){

    }
    public String talk(Player player){
        return null;
    }
}