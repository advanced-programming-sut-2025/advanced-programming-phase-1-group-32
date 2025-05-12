package models.shop;

import models.App;
import models.entities.components.EntityComponent;
import models.enums.Season;
import records.Result;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Shop extends EntityComponent
{

    String name;
    private List<ShopProduct> permanentProducts;
    private HashMap<Season, List<ShopProduct>> seasonalProducts;



    Shop() {

    }

    public Shop(ShopData data) {
        permanentProducts = new ArrayList<>(data.permanentProducts);
        seasonalProducts = new HashMap<>(data.seasonalProducts);
    }

    private Shop(Shop other) {
        this.permanentProducts = new ArrayList<>(other.permanentProducts);
        this.seasonalProducts = new HashMap<>(other.seasonalProducts);
    }


    public ArrayList<ShopProduct> getAvailableProducts(Season season) {
        ArrayList<ShopProduct> res = new ArrayList<>();
        res.addAll(permanentProducts.stream().filter(p -> !(p.getStock() == 0)).toList());
        if(season == null)
            return res;
        res.addAll(seasonalProducts.get(season).stream().filter(p -> !(p.getStock() == 0)).toList());
        return res;
    }

    public ShopProduct getProductByName(String name) {
        for (ShopProduct product : permanentProducts) {
            if(product.getEntity().getName().equalsIgnoreCase(name))
                return product;
        }
        for (List<ShopProduct> value : seasonalProducts.values()) {
            for (ShopProduct product : value) {
                if(product.getEntity().getName().equalsIgnoreCase(name))
                    return product;
            }
        }
        return null;
    }


    public String allProducts(){
        StringBuilder sb = new StringBuilder();
        sb.append("Permanent Products : \n");
        for (ShopProduct permanentProduct : permanentProducts) {
            sb.append(permanentProduct.toString()).append("\n");
        }
        sb.append("----------------------------------------------------------\n");
        for (Season value : Season.values()) {

            if(seasonalProducts.get(value) != null) {
                sb.append(value.toString().charAt(0)
                        + value.toString().substring(1).toLowerCase() + " Products : \n");
                for (ShopProduct shopProduct : seasonalProducts.get(value)) {
                    sb.append(shopProduct.toString()).append("\n");
                }
                sb.append("----------------------------------------------------------\n");
            }
        }
        return sb.toString();
    }



    @Override
    public EntityComponent clone() {
        return new Shop(this);

    }


}
