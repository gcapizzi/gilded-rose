package com.gildedrose;

class GildedRose {
    Item[] items;

    public GildedRose(Item[] items) {
        this.items = items;
    }

    public void updateQuality() {
        for (Item item : items) {
            if (!item.isAgedBrie()
                    && !item.isBackstagePasses()) {
                if (item.quality > 0) {
                    if (!item.isSulfuras()) {
                        item.decrementQuality();
                    }
                }
            } else {
                if (item.quality < 50) {
                    item.quality = item.quality + 1;

                    if (item.isBackstagePasses()) {
                        if (item.sellIn < 11) {
                            if (item.quality < 50) {
                                item.quality = item.quality + 1;
                            }
                        }

                        if (item.sellIn < 6) {
                            if (item.quality < 50) {
                                item.quality = item.quality + 1;
                            }
                        }
                    }
                }
            }

            if (!item.isSulfuras()) {
                item.sellIn = item.sellIn - 1;
            }

            if (item.sellIn < 0) {
                if (!item.isAgedBrie()) {
                    if (!item.isBackstagePasses()) {
                        if (item.quality > 0) {
                            if (!item.isSulfuras()) {
                                item.decrementQuality();
                            }
                        }
                    } else {
                        item.decrementQuality();
                    }
                } else {
                    if (item.quality < 50) {
                        item.quality = item.quality + 1;
                    }
                }
            }
        }
    }
}
