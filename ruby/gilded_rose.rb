class GildedRose
  def initialize(items)
    @items = items
  end

  def update_quality
    @items.each do |item|
      item.update_quality
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

  def update_quality
    if aged_brie?
      decrement_sell_in

      increment_quality
      increment_quality if sell_in < 0
    elsif backstage_passes?
      decrement_sell_in

      increment_quality
      increment_quality if sell_in < 11
      increment_quality if sell_in < 6
      reset_quality if sell_in < 0
    elsif sulfuras?
      # nope
    else
      decrement_sell_in

      decrement_quality
      decrement_quality if sell_in < 0
    end
  end
end
