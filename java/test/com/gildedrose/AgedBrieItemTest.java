package com.gildedrose;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

public class AgedBrieItemTest {
    @Test
    public void testUpdateQuality() {
        Item item = new AgedBrieItem("Aged Brie", 10, 10);

        item.updateQuality();

        assertThat(item.sellIn, equalTo(9));
        assertThat(item.quality, equalTo(11));
    }

    @Test
    public void testUpdateQualityWhenQualityIsFiftyKeepsItFifty() {
        Item item = new AgedBrieItem("Aged Brie", 10, 50);

        item.updateQuality();

        assertThat(item.sellIn, equalTo(9));
        assertThat(item.quality, equalTo(50));
    }
}
