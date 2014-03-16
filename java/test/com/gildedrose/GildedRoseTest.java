package com.gildedrose;

import org.junit.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class GildedRoseTest {
    @Test
    public void testUpdateQuality() {
        Item firstItem = mock(Item.class);
        Item secondItem = mock(Item.class);
        Item thirdItem = mock(Item.class);
        GildedRose gildedRose = new GildedRose(new Item[]{firstItem, secondItem, thirdItem});

        gildedRose.updateQuality();

        verify(firstItem).updateQuality();
        verify(secondItem).updateQuality();
        verify(thirdItem).updateQuality();
    }
}
