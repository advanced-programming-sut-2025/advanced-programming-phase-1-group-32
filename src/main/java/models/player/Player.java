package main.java.models.player;

import main.java.models.Quest.Quest;
import main.java.models.Map;
import main.java.models.NPC.Character;
import main.java.models.crafting.Recipe;
import main.java.models.enums.SkillType;
import main.java.models.items.BackPack;
import main.java.models.items.Item;
import main.java.models.player.friendship.NpcFriendship;
import main.java.models.player.friendship.PlayerFriendship;

import java.util.ArrayList;

public class Player {
    private final Energy energy;
    private final Wallet wallet;
    private final Map<SkillType, Skill> skills;
    private int trashcanLevel;
    private final BackPack backPack;
    private final Map<Character, NpcFriendship> npcFriendships;
    private final Map<Player, PlayerFriendship> playerFriendships;
    private Player spouce;
    private final ArrayList<Quest> quests;
    private final ArrayList<Gift> giftLog;
    private final ArrayList<Message> messageLog;
    private final ArrayList<TradeOffer> tradeOfferLog;
    private final ArrayList<Recipe> unlockedRecipes;

    public int getTrashcanLevel() {
        return trashcanLevel;
    }

    public void addTrashcanLevel(int trashcanLevel) {
        //TODO
    }

    public void trashItem(Item item) {

    }

    public Skill getSkill(SkillType type){

    }

    public void addExperince(SkillType type, int amount) {

    }

    public void proposePlayer(Player player){

    }

    public void addQuest(){

    }
    public void addTradeOffer(){

    }
    public void addGift(){

    }
    public void addMessage(){

    }
}
