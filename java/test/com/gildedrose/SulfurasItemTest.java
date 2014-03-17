package com.gildedrose;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

public class SulfurasItemTest {
    @Test
    public void testUpdateQuality() {
        Item item = new SulfurasItem("Sulfuras, Hand of Ragnaros", 10, 10);

        item.updateQuality();

        assertThat(item.getSellIn(), equalTo(10));
        assertThat(item.getQuality(), equalTo(10));
    }
}
