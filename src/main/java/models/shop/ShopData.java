package models.shop;

import models.enums.Season;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ShopData {
    String name;
    List<ShopProduct> permanentProducts = new ArrayList<>();
    HashMap<Season, List<ShopProduct>> seasonalProducts = new HashMap<>();
    private int startHour;
    private int endHour;

    public ShopData() {

    }





}
