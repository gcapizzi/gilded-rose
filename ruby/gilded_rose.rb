class GildedRose
  def initialize(items)
    @items = items
  end

  def update_quality
    @items.each do |item|
      if item.aged_brie?
        item.decrement_sell_in

        item.increment_quality
        item.increment_quality if item.sell_in < 0
      elsif item.backstage_passes?
        item.decrement_sell_in

        item.increment_quality
        item.increment_quality if item.sell_in < 11
        item.increment_quality if item.sell_in < 6
        item.reset_quality if item.sell_in < 0
      elsif item.sulfuras?
        # nope
      else
        item.decrement_sell_in

        item.decrement_quality
        item.decrement_quality if item.sell_in < 0
      end
    end
  end
end

class Item
  AGED_BRIE = 'Aged Brie'
  BACKSTAGE_PASSES = 'Backstage passes to a TAFKAL80ETC concert'
  SULFURAS = 'Sulfuras, Hand of Ragnaros'

  attr_accessor :name, :sell_in, :quality

  def initialize(name, sell_in, quality)
    @name = name
    @sell_in = sell_in
    @quality = quality
  end

  def to_s
    "#{@name}, #{@sell_in}, #{@quality}"
  end

  def aged_brie?
    name == AGED_BRIE
  end

  def backstage_passes?
    name == BACKSTAGE_PASSES
  end

  def sulfuras?
    name == SULFURAS
  end

  def decrement_quality
    @quality -= 1 if @quality > 0
  end

  def increment_quality
    @quality += 1 if @quality < 50
  end

  def reset_quality
    @quality = 0
  end

  def decrement_sell_in
    @sell_in -= 1
  end
end
