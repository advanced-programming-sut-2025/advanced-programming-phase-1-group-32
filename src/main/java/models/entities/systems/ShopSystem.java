package models.entities.systems;

import models.App;
import models.Position;
import models.entities.Entity;
import models.entities.components.Pickable;
import models.entities.components.Placeable;
import models.entities.components.inventory.Inventory;
import models.player.Player;
import models.player.Wallet;
import models.shop.*;
import records.Result;

public class ShopSystem {
    public static Result buyProduct(ShopProduct product, int amount) {
        if(amount > product.getStock() && product.getStock() >= 0)
            return new Result(false, "There isn't enough stock! go come tomorrow:)");
        Entity productEntity = product.getEntity();



        if(productEntity.getComponent(Pickable.class) != null && product instanceof OtherShopProduct) {
            return buyPickable(product, amount);
        }
/*
        if(productEntity.getComponent(Placeable.class) != null && product instanceof BuildingShopProduct) {
            return new Result(true, null);
        }

        if(product instanceof AnimalShopProduct) {
            return new Result(true, null);
        }*/

        App.getView().err("error: product " + productEntity.getEntityName() + " doesn't belong to any states");
        return new Result(false, "invalid Product!");
    }

    public static Result buyPickable(ShopProduct p, int amount) {
        Entity e = p.getEntity();
        e.getComponent(Pickable.class).setStackSize(amount);
        Inventory inventory = App.getActiveGame().getCurrentPlayer().getComponent(Inventory.class);
        if(inventory.canAddItem(e, amount)){
            Result result = handlePay(p, amount);
            if(!result.isSuccessful())
                return result;
            p.addSold(amount);
            inventory.addItem(e);
            return new Result(true, e.getEntityName() +  " x(" + amount + ")" +  " added to your inventory!");
        }
        return new Result(false, "Your inventory doesn't have enough space");
    }


    public static Result buildPlaceable(ShopProduct p, int x, int y) {
        if(p == null)
            return new Result(false, "this building isn't available in this shop");
        if(!(p instanceof BuildingShopProduct))
            return new Result(false, "this can not be placed");
        Result result = handlePay(p, 1);
        if(!result.isSuccessful())
            return result;
        Entity building = p.getEntity();
        EntityPlacementSystem.placeEntity(building, new Position(x, y));
        //TODO: if can't place gets error
        p.addSold(1);
        Player player = App.getActiveGame().getCurrentPlayer();
        player.addOwnedBuilding(building);
        return new Result(true, "building build successfully!");
    }


    public static Result buyAnimal() {
        //TODO
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
