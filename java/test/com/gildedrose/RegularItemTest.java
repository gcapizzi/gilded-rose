package com.gildedrose;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.instanceOf;
import static org.junit.Assert.assertThat;

public class RegularItemTest {
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
