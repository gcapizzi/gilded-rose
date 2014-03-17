package com.gildedrose;

public class RegularItem implements Item {
    protected String name;
    protected int sellIn;
    protected int quality;

    public RegularItem(String name, int sellIn, int quality) {
        this.name = name;
        this.sellIn = sellIn;
        this.quality = quality;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public int getSellIn() {
        return sellIn;
    }

    @Override
    public int getQuality() {
        return quality;
    }

    @Override
    public void updateQuality() {
        decrementSellIn();
        decrementQuality();
        if (sellIn < 0) { decrementQuality(); }
    }

    protected void decrementQuality() {
        if (quality > 0) {
            quality -= 1;
        }
    }

    public String toString() {
        return this.name + ", " + this.sellIn + ", " + this.quality;
    }

    protected void incrementQuality() {
        if (quality < 50) {
            quality = quality + 1;
        }
    }

    protected void resetQuality() {
        quality = 0;
    }

    protected void decrementSellIn() {
        sellIn = sellIn - 1;
    }
}
