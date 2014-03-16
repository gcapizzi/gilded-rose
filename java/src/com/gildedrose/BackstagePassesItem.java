package com.gildedrose;

public class BackstagePassesItem extends Item {
    public BackstagePassesItem(String name, int sellIn, int quality) {
        super(name, sellIn, quality);
    }

    @Override
    public void updateQuality() {
        decrementSellIn();
        incrementQuality();
        if (sellIn < 11) { incrementQuality(); }
        if (sellIn < 6)  { incrementQuality(); }
        if (sellIn < 0)  { resetQuality(); }
    }
}
