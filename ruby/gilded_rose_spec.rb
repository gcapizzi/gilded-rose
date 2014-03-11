require_relative 'gilded_rose'

describe GildedRose do
  describe '#update_quality' do
    let(:item) { double(Item, update_quality: nil) }
    subject { GildedRose.new [item] }

    before { subject.update_quality }

    it 'calls #update_quality on each item' do
      expect(item).to have_received(:update_quality)
    end
  end
end

describe Item do
  subject { Item.new('foo', 10, 20) }

  describe '#initialize' do
    it 'sets the item name, sell_in and quality' do
      expect(subject.name).to eq('foo')
      expect(subject.sell_in).to eq(10)
      expect(subject.quality).to eq(20)
    end
  end

  describe '#to_s' do
    it 'returns a string representation of the item' do
      expect(subject.to_s).to eq('foo, 10, 20')
    end
  end

  describe '.from' do
    let(:name) { 'foo' }

    subject { Item.from(name, 10, 20) }

    it 'returns an Item' do
      expect(subject).to be_an_instance_of(Item)
    end

    it 'sets name, sell_in and quality' do
      expect(subject.name).to eq(name)
      expect(subject.sell_in).to eq(10)
      expect(subject.quality).to eq(20)
    end

    context 'when the item is Aged Brie' do
      let(:name) { 'Aged Brie' }
      it { should be_an_instance_of(AgedBrieItem) }
    end

    context 'when the item is Backstage Passes' do
      let(:name) { 'Backstage passes to a Radiohead concert' }
      it { should be_an_instance_of(BackstagePassesItem) }
    end

    context 'when the item is Sulfuras' do
      let(:name) { 'Sulfuras, Hand of Ragnaros' }
      it { should be_an_instance_of(SulfurasItem) }
    end

    context 'when the item is Conjured' do
      let(:name) { 'Conjured something' }
      it { should be_an_instance_of(ConjuredItem) }
    end
  end

  describe '#update_quality' do
    before { subject.update_quality }

    it 'does not change the name' do
      expect(subject.name).to eq('foo')
    end

    it 'lowers both sell_in and quality' do
      expect(subject.sell_in).to eq(9)
      expect(subject.quality).to eq(19)
    end

    context 'when the quality is 0' do
      subject { Item.from('foo', 10, 0) }

      it 'doesn\'t lower it further' do
        expect(subject.quality).to eq(0)
      end
    end

    context 'when the sell by date has passed' do
      subject { Item.from('foo', 0, 10) }

      it 'lowers quality twice as fast' do
        expect(subject.quality).to eq(8)
      end
    end
  end
end

describe AgedBrieItem do
  subject { AgedBrieItem.new('Aged Brie', 10, 10) }

  describe '#update_quality' do
    before { subject.update_quality }

    it 'increments quality but lower sell in' do
      expect(subject.quality).to eq(11)
      expect(subject.sell_in).to eq(9)
    end

    context 'when the quality is 50' do
      subject { AgedBrieItem.new('Aged Brie', 0, 50) }

      it 'doesn\'t increment it further' do
        expect(subject.quality).to eq(50)
      end
    end
  end
end

describe SulfurasItem do
  subject { SulfurasItem.new('Sulfuras, Hand of Ragnaros', 10, 80) }

  describe '#update_quality' do
    before { subject.update_quality }

    it 'doesn\'t decrease quality nor sell_in' do
      expect(subject.quality).to eq(80)
      expect(subject.sell_in).to eq(10)
    end
  end
end

describe BackstagePassesItem do
  subject { BackstagePassesItem.new('Backstage passes to a TAFKAL80ETC concert', 20, 10) }

  describe '#update_quality' do
    before { subject.update_quality }

    it 'increases quality but lowers sell in' do
      expect(subject.quality).to eq(11)
      expect(subject.sell_in).to eq(19)
    end

    context 'when the sell_in is less than or equal to 10' do
      subject { BackstagePassesItem.new('Backstage passes to a TAFKAL80ETC concert', 10, 10) }

      it 'increases quality by 2' do
        expect(subject.quality).to eq(12)
      end
    end

    context 'when the sell_in is less than or equal to 5' do
      subject { BackstagePassesItem.new('Backstage passes to a TAFKAL80ETC concert', 5, 10) }

      it 'increases quality by 3' do
        expect(subject.quality).to eq(13)
      end
    end

    context 'when the sell_in is less than or equal to 0' do
      subject { BackstagePassesItem.new('Backstage passes to a TAFKAL80ETC concert', 0, 10) }

      it 'drops quality to 0' do
        expect(subject.quality).to eq(0)
      end
    end
  end
end

describe ConjuredItem do
  subject { ConjuredItem.new('Conjured something', 10, 10) }

  describe '#update_quality' do
    before { subject.update_quality }

    it 'lowers quality twice as fast, sell in as usual' do
      expect(subject.quality).to eq(8)
      expect(subject.sell_in).to eq(9)
    end

    context 'when the sell by date has passed' do
      subject { ConjuredItem.new('Conjured something', 0, 10) }

      it 'lowers quality four times as fast' do
        expect(subject.quality).to eq(6)
      end
    end
  end
end
