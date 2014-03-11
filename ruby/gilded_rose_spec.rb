require_relative 'gilded_rose'

describe GildedRose do
  describe '#update_quality' do
    let(:item) { Item.from('foo', 10, 20) }
    subject { GildedRose.new [item] }

    before { subject.update_quality }

    it 'does not change the name' do
      expect(item.name).to eq('foo')
    end

    it 'lowers both sell_in and quality' do
      expect(item.sell_in).to eq(9)
      expect(item.quality).to eq(19)
    end

    context 'when the quality is 0' do
      let(:item) { Item.from('foo', 10, 0) }

      it 'doesn\'t lower it further' do
        expect(item.quality).to eq(0)
      end
    end

    context 'when the sell by date has passed' do
      let(:item) { Item.from('foo', 0, 10) }

      it 'lowers quality twice as fast' do
        expect(item.quality).to eq(8)
      end
    end

    context 'when the product is Aged Brie' do
      let(:item) { Item.from('Aged Brie', 10, 10) }

      it 'increments quality but lower sell in' do
        expect(item.quality).to eq(11)
        expect(item.sell_in).to eq(9)
      end

      context 'when the quality is 50' do
        let(:item) { Item.from('Aged Brie', 0, 50) }

        it 'doesn\'t increment it further' do
          expect(item.quality).to eq(50)
        end
      end
    end

    context 'when the product is Sulfuras, Hand of Ragnaros' do
      let(:item) { Item.from('Sulfuras, Hand of Ragnaros', 10, 80) }

      it 'doesn\'t decrease quality nor sell_in' do
        expect(item.quality).to eq(80)
        expect(item.sell_in).to eq(10)
      end
    end

    context 'when the product is Backstage passes' do
      context 'when the sell_in is greater than 10' do
        let(:item) { Item.from('Backstage passes to a TAFKAL80ETC concert', 20, 10) }

        it 'increases quality but lowers sell in' do
          expect(item.quality).to eq(11)
          expect(item.sell_in).to eq(19)
        end
      end

      context 'when the sell_in is less than or equal to 10' do
        let(:item) { Item.from('Backstage passes to a TAFKAL80ETC concert', 10, 10) }

        it 'increases quality by 2' do
          expect(item.quality).to eq(12)
        end
      end

      context 'when the sell_in is less than or equal to 5' do
        let(:item) { Item.from('Backstage passes to a TAFKAL80ETC concert', 5, 10) }

        it 'increases quality by 3' do
          expect(item.quality).to eq(13)
        end
      end

      context 'when the sell_in is less than or equal to 0' do
        let(:item) { Item.from('Backstage passes to a TAFKAL80ETC concert', 0, 10) }

        it 'drops quality to 0' do
          expect(item.quality).to eq(0)
        end
      end
    end

    context 'when the product is Conjured' do
      let(:item) { Item.from('Conjured something', 10, 10) }

      xit 'lowers quality twice as fast' do
        expect(item.quality).to eq(8)
      end

      context 'when the sell by date has passed' do
        let(:item) { Item.from('Conjured something', 0, 10) }

        xit 'lowers quality four times as fast' do
          expect(item.quality).to eq(6)
        end
      end
    end
  end
end

describe Item do
  subject { Item.new('foo', 123, 456) }

  describe '#initialize' do
    it 'sets the item name, sell_in and quality' do
      expect(subject.name).to eq('foo')
      expect(subject.sell_in).to eq(123)
      expect(subject.quality).to eq(456)
    end
  end

  describe '#to_s' do
    it 'returns a string representation of the item' do
      expect(subject.to_s).to eq('foo, 123, 456')
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
end
