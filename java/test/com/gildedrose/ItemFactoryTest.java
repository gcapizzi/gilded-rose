package com.gildedrose;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.instanceOf;
import static org.junit.Assert.assertThat;

public class ItemFactoryTest {
    private ItemFactory factory;

    @Before
    public void setUp() {
        this.factory = new ItemFactory();
    }

    @Test
    public void testCreateItem() {
        Item item = factory.createItem("foo", 10, 20);

        assertThat(item, instanceOf(RegularItem.class));
        assertThat(item.getName(), equalTo("foo"));
        assertThat(item.getSellIn(), equalTo(10));
        assertThat(item.getQuality(), equalTo(20));
    }

    @Test
    public void testCreateItemWithAgedBrie() {
        assertThat(factory.createItem("Aged Brie", 10, 10), instanceOf(AgedBrieItem.class));
    }

    @Test
    public void testCreateItemWithBackstagePasses() {
        assertThat(factory.createItem("Backstage passes to a TAFKAL80ETC concert", 10, 10), instanceOf(BackstagePassesItem.class));
    }

    @Test
    public void testCreateItemWithSulfuras() {
        assertThat(factory.createItem("Sulfuras, Hand of Ragnaros", 10, 10), instanceOf(SulfurasItem.class));
    }
}
