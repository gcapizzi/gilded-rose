package com.gildedrose;

public class ItemFactory {
    public static final String AGED_BRIE = "Aged Brie";
    public static final String BACKSTAGE_PASSES = "Backstage passes to a TAFKAL80ETC concert";
    public static final String SULFURAS = "Sulfuras, Hand of Ragnaros";

    public Item createItem(String name, int sellIn, int quality) {
        if (name == AGED_BRIE) return new AgedBrieItem(name, sellIn, quality);
        if (name == BACKSTAGE_PASSES) return new BackstagePassesItem(name, sellIn, quality);
        if (name == SULFURAS) return new SulfurasItem(name, sellIn, quality);
        return new RegularItem(name, sellIn, quality);
    }
}
