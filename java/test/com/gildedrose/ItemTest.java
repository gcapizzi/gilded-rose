package com.gildedrose;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.instanceOf;
import static org.junit.Assert.assertThat;

public class ItemTest {
    @Test
    public void testFrom() {
        Item item = Item.from("foo", 10, 20);
        assertThat(item, instanceOf(Item.class));
        assertThat(item.name, equalTo("foo"));
        assertThat(item.sellIn, equalTo(10));
        assertThat(item.quality, equalTo(20));
    }

    @Test
    public void testFromWithAgedBrie() {
        assertThat(Item.from("Aged Brie", 10, 10), instanceOf(AgedBrieItem.class));
    }

    @Test
    public void testFromWithBackstagePasses() {
        assertThat(Item.from("Backstage passes to a TAFKAL80ETC concert", 10, 10), instanceOf(BackstagePassesItem.class));
    }

    @Test
    public void testFromWithSulfuras() {
        assertThat(Item.from("Sulfuras, Hand of Ragnaros", 10, 10), instanceOf(SulfurasItem.class));
    }

    @Test
    public void testUpdateQuality() {
        Item item = new Item("foo", 10, 10);

        item.updateQuality();

        assertThat(item.sellIn, equalTo(9));
        assertThat(item.quality, equalTo(9));
    }

    @Test
    public void testUpdateQualityWhenSellInHasPassedLowersQualityTwiceAsFast() {
        Item item = new Item("foo", 0, 10);

        item.updateQuality();

        assertThat(item.sellIn, equalTo(-1));
        assertThat(item.quality, equalTo(8));
    }

    @Test
    public void testUpdateQualityWhenQualityIsZeroKeepsItZero() {
        Item item = new Item("foo", 10, 0);

        item.updateQuality();

        assertThat(item.sellIn, equalTo(9));
        assertThat(item.quality, equalTo(0));
    }
}
