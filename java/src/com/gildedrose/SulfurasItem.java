package com.gildedrose;

public class SulfurasItem extends RegularItem {
    public SulfurasItem(String name, int sellIn, int quality) {
        super(name, sellIn, quality);
    }

    @Override
    public void updateQuality() {
        // nope
    }
}
