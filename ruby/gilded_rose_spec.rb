require_relative 'gilded_rose'

describe GildedRose do
  describe '#update_quality' do
    let(:item) { Item.new('foo', 0, 0) }

    subject { GildedRose.new [item] }

    it 'does not change the name' do
      expect { subject.update_quality }.not_to change { item.name }
    end
  end
end
