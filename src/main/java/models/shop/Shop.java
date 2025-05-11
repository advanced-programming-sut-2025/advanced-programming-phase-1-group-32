package models.shop;

import models.entities.Entity;
import models.enums.Season;

import java.util.ArrayList;
import java.util.Map;

public class Shop {
    ArrayList<ShopProduct> yearRoundProducts;
    Map<Season, ArrayList<ShopProduct>> seasonalProducts;
}
