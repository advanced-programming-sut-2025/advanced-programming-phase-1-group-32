package records;

import models.animal.AnimalType;
import models.player.Player;

public record AnimalPurchaseDetails(boolean canBuy, String message,String animalName, AnimalType animalType) {
    public AnimalPurchaseDetails(boolean canBuy, String message ) {
        this(canBuy, message, null, null);
    }
}
