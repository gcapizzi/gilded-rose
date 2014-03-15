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

    boolean isSulfuras() {
        return name.equals(SULFURAS);
    }

    boolean isBackstagePasses() {
        return name.equals(BACKSTAGE_PASSES);
    }

    public void decrementQuality() {
        quality -= 1;
    }
}
