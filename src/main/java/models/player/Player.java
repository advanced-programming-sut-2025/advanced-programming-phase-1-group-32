package models.player;

import models.Position;
import models.Quest.Quest;
import models.NPC.Character;
import models.Result;
import models.crafting.Recipe;
import models.entities.Entity;
import models.entities.components.Inventory;
import models.entities.components.Pickable;
import models.enums.SkillType;
import models.player.friendship.NpcFriendship;
import models.player.friendship.PlayerFriendship;

import java.util.ArrayList;
import java.util.Map;

public class Player extends Entity{
    private Energy energy = new Energy();
    private final Wallet wallet = null;
    private final Map<SkillType, Skill> skills = null;
    private int trashcanLevel;
    private final Entity backPack = null;
    private final Map<Character, NpcFriendship> npcFriendships = null;
    private final Map<Player, PlayerFriendship> playerFriendships = null;
    private Player spouce;
    private final ArrayList<Quest> quests = null;
    private final ArrayList<Gift> giftLog = null;
    private final ArrayList<Message> messageLog = null;
    private final ArrayList<TradeOffer> tradeOfferLog = null;
    private final ArrayList<Recipe> unlockedRecipes = null;

    public Player(){
        super("Player", new Inventory(12));
    }

    //TODO: this should change. Position will become a component
    private Position position = new Position(0, 0);

    public int getTrashcanLevel() {
        return trashcanLevel;
    }

    public void addTrashcanLevel(int trashcanLevel) {
        //TODO
    }

    public void trashItem(Entity item) {

    }

    public Energy getEnergy() {
        return energy;
    }

    public void setEnergy(Energy energy) {
        this.energy = energy;
    }

    public Wallet getWallet() {
        return this.wallet;
    }

    public Entity getBackPack() {
        return backPack;
    }

    public Player getSpouse() {
        return spouce;
    }

    public void setSpouce(Player spouce) {
        this.spouce = spouce;
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
    public void changePosition(int x, int y){
        this.position.change(x, y);
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

}
