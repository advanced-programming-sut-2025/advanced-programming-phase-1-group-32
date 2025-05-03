package models.player.friendship;

import models.player.Player;

import java.util.ArrayList;
import java.util.List;

public class PlayerFriendship {
    private final ArrayList<Player> friends;
    private int level;
    private int xp = 0;
    private boolean hadMessageToday = false;


    public boolean isHadMessageToday() {
        return hadMessageToday;
    }

    public void setHadMessageToday(boolean hadMessageToday) {
        this.hadMessageToday = hadMessageToday;
    }

    public ArrayList<Player> getFriends() {
        return friends;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getXp() {
        return xp;
    }

    public void setXp(int xp) {
        this.xp = xp;
    }

    public PlayerFriendship(Player player1, Player player2) {
        friends = new ArrayList<>(List.of(new Player[]{player1, player2}));
    }

    public void addXp(int xp) {
        this.xp += xp;
        if (this.xp >= 100 * (level + 1)) {
            if (level < 2) {
                level++;
                xp -= 100 * level;
            } else if (level == 2) {
                xp = 300;
            } else if (level == 3) {
                xp = 400;
            }
        }
    }

    public static String buildFriendshipMessage(Player currentPlayer,ArrayList<PlayerFriendship> playerFriendships) {
        StringBuilder friendshipMessage = new StringBuilder("Your friendships: \n");

        for (PlayerFriendship playerFriendship : playerFriendships) {
            friendshipMessage.append(buildFriendshipDetailMessage(currentPlayer, playerFriendship));
        }

        return friendshipMessage.toString();
    }

    public static String buildFriendshipDetailMessage(Player currentPlayer, PlayerFriendship playerFriendship) {
        StringBuilder result = new StringBuilder();
        Player friend = playerFriendship.getFriends().get(0);
        if (friend == currentPlayer) {
            friend = playerFriendship.getFriends().get(1);
        }

        result.append("Friend Name: ").append(friend.getUsername()).append("\n");
        result.append("Friendship level: ").append(playerFriendship.getLevel()).append("\n");
        result.append("Friendship XP: ").append(playerFriendship.getXp()).append("\n");
        result.append(".............................................................\n");

        return result.toString();
    }

    public void updateDaily() {
        hadMessageToday = false;
    }
}
