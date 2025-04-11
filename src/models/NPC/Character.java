package models.NPC;

import models.Quest.Quest;
import models.Position;
import models.player.Player;

import java.util.ArrayList;
import java.util.Map;

public class Character {
    String name;
    private final Map<Integer, Position> schedule;
    private final ArrayList<Quest> quests;

    public void giveQuestToPlayer(Player player){

    }
}
