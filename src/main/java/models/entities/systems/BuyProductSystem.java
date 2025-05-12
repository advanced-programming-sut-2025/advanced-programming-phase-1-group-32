package models.entities.systems;

import models.App;
import models.Vec2;
import models.entities.Entity;
import models.entities.components.Pickable;
import models.entities.components.Placeable;
import models.entities.components.PositionComponent;
import models.entities.components.inventory.Inventory;
import models.shop.ShopProduct;
import records.Result;

public class BuyProductSystem {
    public static Result buyProduct(ShopProduct product, int amount) {
        if(amount > product.getStock() && product.getStock() >= 0)
            return new Result(false, "There isn't enough stock! go come tomorrow:)");
        Entity productEntity = product.getEntity();

        if(productEntity.getComponent(Pickable.class) != null) {
            return buyPickable(productEntity, amount);
        }

        if(productEntity.getComponent(Placeable.class) != null) {
            return new Result(true, null);
        }
        App.getView().err("error: product " + productEntity.getName() + " doesn't have placeable and pickable component");
        return new Result(false, "invalid Product!");
    }

    private static Result buyPickable(Entity e, int amount) {
        e.getComponent(Pickable.class).setStackSize(amount);
        Inventory inventory = App.getActiveGame().getCurrentPlayer().getComponent(Inventory.class);
        if(inventory.canAddItem(e, amount)){
            inventory.addItem(e);
            //TODO: handle costs
            return new Result(true, e.getName() +  " x(" + amount + ")" +  " added to your inventory!");
        }
        return new Result(false, "Your inventory doesn't have enough space");
    }


    public static Result buildPlaceable(Entity e, int x, int y) {
        Placeable placeable = e.getComponent(Placeable.class);
        e.addComponent(new PositionComponent(x, y));
        EntityPlacementSystem.placeEntity(e);
        //TODO: if can't place gets error else reduce costs
        return null;
    }


}
