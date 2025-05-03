package models.player.friendship;

import models.player.Player;

import java.util.ArrayList;
import java.util.List;

public class PlayerFriendship {
    private ArrayList<Player> friends;
    int level;


    public PlayerFriendship(Player player1, Player player2) {
        friends = new ArrayList<>(List.of(new Player[]{player1, player2}));
    }
}
