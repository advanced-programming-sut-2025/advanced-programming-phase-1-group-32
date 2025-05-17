package models.player.friendship;

public class AnimalFriendship extends Friendship implements Serializable {
    boolean wasPetToday;
    boolean wasFedToday;

    @Override
    void changeAmount(int amount) {

    }
}
