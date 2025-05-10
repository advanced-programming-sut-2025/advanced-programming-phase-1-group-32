package models.player;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import models.Account;
import models.App;
import models.Position;
import models.Quest.Quest;
import models.NPC.Character;
import models.Tile;
import models.crafting.Recipe;
import models.entities.Entity;
import models.entities.components.inventory.Inventory;
import models.entities.components.inventory.InventorySlot;
import models.enums.SkillType;
import models.gameMap.MapRegion;
import models.player.friendship.NpcFriendship;
import models.player.friendship.PlayerFriendship;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Player extends Entity implements Serializable {
    private Energy energy = new Energy();
    private Wallet wallet = new Wallet();
    private final Map<SkillType, Skill> skills = new HashMap<>();
    private int trashcanLevel;
    private final Map<Character, NpcFriendship> npcFriendships = null;
    private final Map<Player, PlayerFriendship> playerFriendships = null;
    private HashMap<Player, Entity> suitors = new HashMap<>();
    private Player spouse;
    private final ArrayList<Quest> quests = null;
    private ArrayList<Gift> giftLog = new ArrayList<>();
    private int giftId = 1;
    private ArrayList<Message> messageLog = new ArrayList<>();
    private final ArrayList<TradeOffer> tradeOfferLog = null;
    private final ArrayList<Recipe> unlockedRecipes;
    private ArrayList<TradeOffer> trades = new ArrayList<>();
    private final Account account;
    private InventorySlot activeSlot;
    @JsonManagedReference
    private final ArrayList<MapRegion> ownedRegions = new ArrayList<>();

    // boolean for messages
    private boolean haveNewMessage = false;
    private boolean haveNewGift = false;
    private boolean haveNewTrade = false;
    private boolean haveNewSuitor = false;

    public Player(Account account){
        super("Player", new Inventory(12));
        unlockedRecipes = new ArrayList<>(App.recipeRegistry.getUnlockedRecipes());
        for(SkillType s : SkillType.values()){
            skills.put(s, new Skill());
        }

        this.account = account;
    }

    //TODO: this should change. Position will become a component
    private Position position = new Position(0, 0);

    public int getTrashcanLevel() {
        return trashcanLevel;
    }

    public void addTrashcanLevel(int trashcanLevel) {
        //TODO
    }

    public HashMap<Player, Entity> getSuitors() {
        return suitors;
    }

    public void setSuitors(HashMap<Player, Entity> suitors) {
        this.suitors = suitors;
    }

    public ArrayList<Gift> getGiftLog() {
        return giftLog;
    }

    public ArrayList<TradeOffer> getTrades() {
        return trades;
    }

    public void setGiftLog(ArrayList<Gift> giftLog) {
        this.giftLog = giftLog;
    }

    public boolean isHaveNewGift() {
        return haveNewGift;
    }

    public void setHaveNewGift(boolean haveNewGift) {
        this.haveNewGift = haveNewGift;
    }

    public void trashItem(Entity item) {

    }

    public Energy getEnergy() {
        return energy;
    }

    public void setWallet(Wallet wallet) {
        this.wallet = wallet;
    }

    public void setEnergy(Energy energy) {
        this.energy = energy;
    }

    public void reduceEnergy(int energyCost) {
        this.energy.setAmount(energy.getAmount() - energyCost);
        if(energy.getAmount() < 0)
            energy.setAmount(0);
    }

    public Wallet getWallet() {
        return this.wallet;
    }

    public Player getSpouse() {
        return spouse;
    }

    public void setSpouse(Player spouse) {
        this.spouse = spouse;
    }

    public Skill getSkill(SkillType type){
        return this.skills.get(type);
    }

    public void addExperince(SkillType type, int amount) {
        //TODO

    }

    public void addSuitor(Player suitor, Entity ring) {
        this.suitors.put(suitor, ring);
        haveNewSuitor = true;
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

    public Account getAccount() {
        return account;
    }

    public InventorySlot getActiveSlot() {
        return activeSlot;
    }

    public void setActiveSlot(InventorySlot activeSlot) {
        this.activeSlot = activeSlot;
    }

    public ArrayList<Message> getMessageLog() {
        return messageLog;
    }

    public void setMessageLog(ArrayList<Message> messageLog) {
        this.messageLog = messageLog;
    }

    public boolean doesHaveNewMessage() {
        return haveNewMessage;
    }

    public boolean isHaveNewMessage() {
        return haveNewMessage;
    }

    public void setHaveNewMessage(boolean haveNewMessage) {
        this.haveNewMessage = haveNewMessage;
    }

    public boolean isHaveNewTrade() {
        return haveNewTrade;
    }

    public void setHaveNewTrade(boolean haveNewTrade) {
        this.haveNewTrade = haveNewTrade;
    }

    public void makeMessagesSeen() {
        for(Message message : messageLog){
            if (message.getReceiver() == this){
                message.setSeen(true);
            }
        }
    }

    public void receiveGift(Gift gift) {
        giftLog.add(gift);
        gift.setId(giftId);
        giftId++;
        haveNewGift = true;
        // TODO: add to box
    }

    public void receiveFlower() {


    }

    public TradeOffer findTradeOffer(int id) {
        for(TradeOffer tradeOffer : trades){
            if(tradeOffer.getId() == id){
                return tradeOffer;
            }
        }
        return null;
    }


    public Gift findGift(int giftId) {
        for (Gift gift : giftLog) {
            if (gift.getId() == giftId) {
                return gift;
            }
        }
        return null;
    }

    public String getUsername() {
        return account.getUsername();
    }

    public ArrayList<Recipe> getUnlockedRecipes() {
        return new ArrayList<>(unlockedRecipes);
    }

    public boolean hasRecipe(String name) {
        return hasRecipe(App.recipeRegistry.getRecipe(name));
    }

    public boolean hasRecipe(Recipe recipe) {
        return unlockedRecipes.contains(recipe);
    }

    public String newMessages() {
        StringBuilder result = new StringBuilder();
        if(haveNewMessage){
            result.append("You have new Messages!\n");
            haveNewMessage = false;
        }
        if(haveNewTrade){
            result.append("You have new Trade offers!\n");
            haveNewTrade = false;
        }
        if(haveNewGift){
            result.append("You have new Gift!\n");
            haveNewGift = false;
        }
        if(haveNewSuitor){
            result.append("You have new Suitor! Your suitors:");
            haveNewSuitor = false;
            for (Map.Entry<Player, Entity> entry : suitors.entrySet()) {
                result.append("Player: ").append(entry.getKey().getUsername());
                result.append("Ring: ").append(entry.getValue()).append("\n");
            }
        }

        return result.toString();
    }

    public boolean doesOwnTile(Tile tile){
        //TODO: should this return true? what should the region of tiles be in a building? null?
        if(tile.getRegion() == null) return true;

        for(MapRegion r : ownedRegions){
            if(r.hasTile(tile)) return true;
        }

        if(spouse == null) return false;

        for(MapRegion r : spouse.ownedRegions){
            if(r.hasTile(tile)) return true;
        }

        return false;
    }

    public void addRegion(MapRegion region){
        this.ownedRegions.add(region);
        region.setOwner(this);
    }
    public void removeRegion(MapRegion region){
        this.ownedRegions.remove(region);
    }

    public void updatePerDay() {
        getEnergy().updatePerDay();
    }
}
