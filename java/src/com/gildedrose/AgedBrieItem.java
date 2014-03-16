package com.gildedrose;

public class AgedBrieItem extends Item {
    public AgedBrieItem(String name, int sellIn, int quality) {
        super(name, sellIn, quality);
    }

    @Override
    public void updateQuality() {
        decrementSellIn();
        incrementQuality();
        if (sellIn < 0) { incrementQuality(); }
    }
}
