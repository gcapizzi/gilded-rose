package com.gildedrose;

class GildedRose {
    Item[] items;

    public GildedRose(Item[] items) {
        this.items = items;
    }

    public void updateQuality() {
        for (Item item : items) {
            if (!item.isAgedBrie() && !item.isBackstagePasses()) {
                item.decrementQuality();
            } else {
                item.incrementQuality();

                if (item.isBackstagePasses()) {
                    if (item.sellIn < 11) {
                        item.incrementQuality();
                    }

                    if (item.sellIn < 6) {
                        item.incrementQuality();
                    }
                }
            }

            if (!item.isSulfuras()) {
                item.sellIn = item.sellIn - 1;
            }

            if (item.sellIn < 0) {
                if (!item.isAgedBrie()) {
                    if (!item.isBackstagePasses()) {
                        item.decrementQuality();
                    } else {
                        item.decrementQuality();
                    }
                } else {
                    item.incrementQuality();
                }
            }
        }
    }
}
