package models.entities.systems;

import models.App;
import models.Position;
import models.entities.Entity;
import models.entities.components.Pickable;
import models.entities.components.Placeable;
import models.entities.components.inventory.Inventory;
import models.player.Player;
import models.player.Wallet;
import models.shop.ShopProduct;
import records.Result;

public class ShopSystem {
    public static Result buyProduct(ShopProduct product, int amount) {
        if(amount > product.getStock() && product.getStock() >= 0)
            return new Result(false, "There isn't enough stock! go come tomorrow:)");
        Entity productEntity = product.getEntity();



        if(productEntity.getComponent(Pickable.class) != null) {
            return buyPickable(product, amount);
        }

        if(productEntity.getComponent(Placeable.class) != null) {
            return new Result(true, null);
        }
        App.getView().err("error: product " + productEntity.getName() + " doesn't have placeable and pickable component");
        return new Result(false, "invalid Product!");
    }

    private static Result buyPickable(ShopProduct p, int amount) {
        Entity e = p.getEntity();
        e.getComponent(Pickable.class).setStackSize(amount);
        Inventory inventory = App.getActiveGame().getCurrentPlayer().getComponent(Inventory.class);
        if(inventory.canAddItem(e, amount)){
            Result result = handlePay(p, amount);
            if(!result.isSuccessful())
                return result;
            p.addSold(amount);
            inventory.addItem(e);
            return new Result(true, e.getName() +  " x(" + amount + ")" +  " added to your inventory!");
        }
        return new Result(false, "Your inventory doesn't have enough space");
    }


    public static Result buildPlaceable(ShopProduct p, int x, int y) {
        Entity e = p.getEntity();
        Placeable placeable = e.getComponent(Placeable.class);
        EntityPlacementSystem.placeEntity(e, new Position(x, y));
        Player player = App.getActiveGame().getCurrentPlayer();
        player.addOwnedBuilding(e);
        //TODO: if can't place gets error else reduce costs
        Result result = handlePay(p, 1);
        if(!result.isSuccessful())
            return result;
        return null;


    }



    private static Result handlePay(ShopProduct p, int amount) {
        Wallet wallet = App.getActiveGame().getCurrentPlayer().getWallet();
        Inventory inventory = App.getActiveGame().getCurrentPlayer().getComponent(Inventory.class);
        if(wallet.getBalance() < amount * p.getPrice())
            return new Result(false, "You don't have enough money!");
        if(!inventory.doesHaveItem("Wood", amount * p.getWoodCost()))
            return new Result(false, "You don't have enough wood!");
        if(!inventory.doesHaveItem("Stone", amount * p.getStoneCost()))
            return new Result(false, "You don't have stone!");
        wallet.changeBalance(-amount * p.getPrice());
        inventory.takeFromInventory("Wood", amount * p.getWoodCost());
        inventory.takeFromInventory("Stone", amount * p.getStoneCost());
        return new Result(true, "paid successfully");
    }

}
