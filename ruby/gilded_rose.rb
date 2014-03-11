class GildedRose
  def initialize(items)
    @items = items
  end

  def update_quality
    @items.each do |item|
      if !item.aged_brie? && !item.backstage_passes?
        if !item.sulfuras?
          item.decrement_quality
        end
      else
        item.increment_quality
        if item.backstage_passes?
          item.increment_quality if item.sell_in < 11
          item.increment_quality if item.sell_in < 6
        end
      end

      if !item.sulfuras?
        item.sell_in = item.sell_in - 1
      end

      if item.sell_in < 0
        if !item.aged_brie?
          if !item.backstage_passes?
            if !item.sulfuras?
              item.decrement_quality
            end
          else
            item.reset_quality
          end
        else
          item.increment_quality
        end
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
end
