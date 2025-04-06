package models.player;

import models.Map;
import models.NPC.Character;
import models.enums.SkillType;
import models.items.BackPack;
import models.items.Item;
import models.player.friendship.NpcFriendship;
import models.player.friendship.PlayerFriendship;

public class Player {
    private final Energy energy;
    private final Wallet wallet;
    private final Map<SkillType, Skill> skills;
    private int trashcanLevel;
    private final BackPack backPack;
    private final Map<Character, NpcFriendship> npcFriendships;
    private final Map<Player, PlayerFriendship> playerFriendships;

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
}
