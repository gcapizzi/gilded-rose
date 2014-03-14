package com.gildedrose;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

public class GildedRoseTest {

    @Test
    public void testUpdateQuality() {
        Item item = new Item("foo", 10, 10);
        GildedRose gildedRose = new GildedRose(new Item[]{item});

        gildedRose.updateQuality();

        assertThat(item.sellIn, equalTo(9));
        assertThat(item.quality, equalTo(9));
    }

    @Test
    public void testUpdateQualityWhenSellInHasPassedLowersQualityTwiceAsFast() {
        Item item = new Item("foo", 0, 10);
        GildedRose gildedRose = new GildedRose(new Item[]{item});

        gildedRose.updateQuality();

        assertThat(item.sellIn, equalTo(-1));
        assertThat(item.quality, equalTo(8));
    }

    @Test
    public void testUpdateQualityWhenQualityIsZeroKeepsItZero() {
        Item item = new Item("foo", 10, 0);
        GildedRose gildedRose = new GildedRose(new Item[]{item});

        gildedRose.updateQuality();

        assertThat(item.sellIn, equalTo(9));
        assertThat(item.quality, equalTo(0));
    }

    @Test
    public void testUpdateQualityWhenTheItemIsAgedBrieIncreasesQuality() {
        Item item = new Item("Aged Brie", 10, 10);
        GildedRose gildedRose = new GildedRose(new Item[]{item});

        gildedRose.updateQuality();

        assertThat(item.sellIn, equalTo(9));
        assertThat(item.quality, equalTo(11));
    }

    @Test
    public void testUpdateQualityWhenQualityIsFiftyKeepsItFifty() {
        Item item = new Item("Aged Brie", 10, 50);
        GildedRose gildedRose = new GildedRose(new Item[]{item});

        gildedRose.updateQuality();

        assertThat(item.sellIn, equalTo(9));
        assertThat(item.quality, equalTo(50));
    }

    @Test
    public void testUpdateQualityWhenTheItemIsSulfurasDoesntChangeSellInOrQuality() {
        Item item = new Item("Sulfuras, Hand of Ragnaros", 10, 10);
        GildedRose gildedRose = new GildedRose(new Item[]{item});

        gildedRose.updateQuality();

        assertThat(item.sellIn, equalTo(10));
        assertThat(item.quality, equalTo(10));
    }

    @Test
    public void testUpdateQualityWhenTheItemIsBackstagePassesIncreasesQuality() {
        Item item = new Item("Backstage passes to a TAFKAL80ETC concert", 20, 10);
        GildedRose gildedRose = new GildedRose(new Item[]{item});

        gildedRose.updateQuality();

        assertThat(item.sellIn, equalTo(19));
        assertThat(item.quality, equalTo(11));

        item.sellIn = 10;
        gildedRose.updateQuality();

        assertThat(item.quality, equalTo(13));

        item.sellIn = 5;
        gildedRose.updateQuality();

        assertThat(item.quality, equalTo(16));
    }
}
