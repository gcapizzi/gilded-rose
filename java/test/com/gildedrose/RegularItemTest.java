package com.gildedrose;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.instanceOf;
import static org.junit.Assert.assertThat;

public class RegularItemTest {
    @Test
    public void testFrom() {
        RegularItem item = RegularItem.from("foo", 10, 20);
        assertThat(item, instanceOf(RegularItem.class));
        assertThat(item.getName(), equalTo("foo"));
        assertThat(item.getSellIn(), equalTo(10));
        assertThat(item.getQuality(), equalTo(20));
    }

    @Test
    public void testFromWithAgedBrie() {
        assertThat(RegularItem.from("Aged Brie", 10, 10), instanceOf(AgedBrieItem.class));
    }

    @Test
    public void testFromWithBackstagePasses() {
        assertThat(RegularItem.from("Backstage passes to a TAFKAL80ETC concert", 10, 10), instanceOf(BackstagePassesItem.class));
    }

    @Test
    public void testFromWithSulfuras() {
        assertThat(RegularItem.from("Sulfuras, Hand of Ragnaros", 10, 10), instanceOf(SulfurasItem.class));
    }

    @Test
    public void testUpdateQuality() {
        RegularItem item = new RegularItem("foo", 10, 10);

        item.updateQuality();

        assertThat(item.getSellIn(), equalTo(9));
        assertThat(item.getQuality(), equalTo(9));
    }

    @Test
    public void testUpdateQualityWhenSellInHasPassedLowersQualityTwiceAsFast() {
        RegularItem item = new RegularItem("foo", 0, 10);

        item.updateQuality();

        assertThat(item.getSellIn(), equalTo(-1));
        assertThat(item.getQuality(), equalTo(8));
    }

    @Test
    public void testUpdateQualityWhenQualityIsZeroKeepsItZero() {
        RegularItem item = new RegularItem("foo", 10, 0);

        item.updateQuality();

        assertThat(item.getSellIn(), equalTo(9));
        assertThat(item.getQuality(), equalTo(0));
    }
}
