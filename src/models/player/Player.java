package models.player;

import models.Quest.Quest;
import models.Map;
import models.NPC.Character;
import models.crafting.Recipe;
import models.enums.SkillType;
import models.items.BackPack;
import models.items.Item;
import models.player.friendship.NpcFriendship;
import models.player.friendship.PlayerFriendship;

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
