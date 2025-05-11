package models.shop;

import models.enums.Season;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ShopData {
    String name;
    private List<ShopProduct> permanentProducts = new ArrayList<>();
    private HashMap<Season, List<ShopProduct>> seasonalProducts = new HashMap<>();


    public ShopData() {

    }

    public ArrayList<ShopProduct> getSeasonProducts(Season season) {
        ArrayList<ShopProduct> res = new ArrayList<>(permanentProducts);
        if(season == null)
            return res;
        res.addAll(seasonalProducts.get(season));
        return res;
    }



}
