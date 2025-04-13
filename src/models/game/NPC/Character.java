package models.game.NPC;

import models.game.Quest.Quest;
import models.game.Position;
import models.game.player.Player;

import java.util.ArrayList;
import java.util.Map;

public class Character {
    String name;
    private final Map<Integer, Position> schedule;
    private final ArrayList<Quest> quests;

    public void giveQuestToPlayer(Player player){

    }
    public String talk(Player player){

    }
}