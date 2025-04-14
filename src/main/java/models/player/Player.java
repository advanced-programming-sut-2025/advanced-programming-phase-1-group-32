package src.main.java.models.player;

import src.main.java.models.Quest.Quest;
import src.main.java.models.NPC.Character;
import src.main.java.models.crafting.Recipe;
import src.main.java.models.enums.SkillType;
import src.main.java.models.items.BackPack;
import src.main.java.models.items.Item;
import src.main.java.models.player.friendship.NpcFriendship;
import src.main.java.models.player.friendship.PlayerFriendship;

import java.util.ArrayList;
import java.util.Map;

public class Player {
    private final Energy energy = null;
    private final Wallet wallet = null;
    private final Map<SkillType, Skill> skills = null;
    private int trashcanLevel;
    private final BackPack backPack = null;
    private final Map<Character, NpcFriendship> npcFriendships = null;
    private final Map<Player, PlayerFriendship> playerFriendships = null;
    private Player spouce;
    private final ArrayList<Quest> quests = null;
    private final ArrayList<Gift> giftLog = null;
    private final ArrayList<Message> messageLog = null;
    private final ArrayList<TradeOffer> tradeOfferLog = null;
    private final ArrayList<Recipe> unlockedRecipes = null;

    public int getTrashcanLevel() {
        return trashcanLevel;
    }

    public void addTrashcanLevel(int trashcanLevel) {
        //TODO
    }

    public void trashItem(Item item) {

    }

    public Skill getSkill(SkillType type){
        return null;
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
