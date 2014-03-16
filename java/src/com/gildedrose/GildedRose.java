package com.gildedrose;

class GildedRose {
    Item[] items;

    public GildedRose(Item[] items) {
        this.items = items;
    }

    public void updateQuality() {
        for (Item item : items) {
            item.decrementSellIn();

            if (item.isAgedBrie()) {
                item.incrementQuality();
                if (item.sellIn < 0) { item.incrementQuality(); }
            } else if (item.isBackstagePasses()) {
                item.incrementQuality();
                if (item.sellIn < 11) { item.incrementQuality(); }
                if (item.sellIn < 6)  { item.incrementQuality(); }
                if (item.sellIn < 0)  { item.resetQuality(); }
            } else {
                item.decrementQuality();
                if (item.sellIn < 0) { item.decrementQuality(); }
            }
        }
    }
}
