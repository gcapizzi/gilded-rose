package com.gildedrose;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

public class BackstagePassesItemTest {
    @Test
    public void testUpdateQuality() {
        Item item = new BackstagePassesItem("Backstage passes to a TAFKAL80ETC concert", 20, 10);

        item.updateQuality();

        assertThat(item.sellIn, equalTo(19));
        assertThat(item.quality, equalTo(11));
    }

    @Test
    public void testUpdateQualityWhenSellInIsTenItIncreasesQualityByTwo() {
        Item item = new BackstagePassesItem("Backstage passes to a TAFKAL80ETC concert", 10, 10);

        item.updateQuality();

        assertThat(item.quality, equalTo(12));
    }

    @Test
    public void testUpdateQualityWhenSellInIsFiveItIncreasesQualityByThree() {
        Item item = new BackstagePassesItem("Backstage passes to a TAFKAL80ETC concert", 5, 10);

        item.updateQuality();

        assertThat(item.quality, equalTo(13));
    }

    @Test
    public void testUpdateQualityWhenSellInIsZeroResetsQualityToZero() {
        Item item = new BackstagePassesItem("Backstage passes to a TAFKAL80ETC concert", 0, 10);

        item.updateQuality();

        assertThat(item.quality, equalTo(0));
    }
}
