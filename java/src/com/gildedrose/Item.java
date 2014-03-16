package com.gildedrose;

public class Item {
    public static final String AGED_BRIE = "Aged Brie";
    public static final String BACKSTAGE_PASSES = "Backstage passes to a TAFKAL80ETC concert";
    public static final String SULFURAS = "Sulfuras, Hand of Ragnaros";

    public String name;
    public int sellIn;
    public int quality;

    public Item(String name, int sellIn, int quality) {
        this.name = name;
        this.sellIn = sellIn;
        this.quality = quality;
    }

    public String toString() {
        return this.name + ", " + this.sellIn + ", " + this.quality;
    }

    boolean isAgedBrie() {
        return name.equals(AGED_BRIE);
    }

    boolean isBackstagePasses() {
        return name.equals(BACKSTAGE_PASSES);
    }

    public void decrementQuality() {
        if (quality > 0) {
            quality -= 1;
        }
    }

    public void incrementQuality() {
        if (quality < 50) {
            quality = quality + 1;
        }
    }

    public void resetQuality() {
        quality = 0;
    }

    public static Item from(String name, int sellIn, int quality) {
        if (name == AGED_BRIE) return new AgedBrieItem(name, sellIn, quality);
        if (name == BACKSTAGE_PASSES) return new BackstagePassesItem(name, sellIn, quality);
        if (name == SULFURAS) return new SulfurasItem(name, sellIn, quality);
        return new Item(name, sellIn, quality);
    }

    public void decrementSellIn() {
        sellIn = sellIn - 1;
    }
}
