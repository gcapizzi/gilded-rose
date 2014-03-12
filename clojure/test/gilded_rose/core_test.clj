(ns gilded-rose.core-test
  (:require [midje.sweet :refer :all]
            [gilded-rose.core :refer [item update-quality]]))

(facts "about `update-quality`"

       (fact "it lowers both quality and sell-in"
             (update-quality [(item "+5 Dexterity Vest" 10 10)]) => [(item "+5 Dexterity Vest" 9 9)]
             (update-quality [(item "Elixir of the Mongoose" 10 10)]) => [(item "Elixir of the Mongoose" 9 9)])

       #_(fact "it never lowers quality below zero"
               (update-quality [(item "Elixir of the Mongoose" 10 0)]) => [(item "Elixir of the Mongoose" 9 0)])

       (facts "when the sell-in date has passed"
              (fact "it lowers quality twice as fast"
                    (update-quality [(item "+5 Dexterity Vest" 0 10)]) => [(item "+5 Dexterity Vest" -1 8)]
                    (update-quality [(item "Elixir of the Mongoose" 0 10)]) => [(item "Elixir of the Mongoose" -1 8)]))

       (facts "when the item is Aged Brie"
              (fact "it increases quality"
                    (update-quality [(item "Aged Brie" 10 10)]) => [(item "Aged Brie" 9 11)])
              (fact "it never increases quality over 50"
                    (update-quality [(item "Aged Brie" 10 50)]) => [(item "Aged Brie" 9 50)]))

       (facts "when the item is Sulfuras"
              (fact "it doesn't change quality nor sell-in"
                    (update-quality [(item "Sulfuras, Hand of Ragnaros" 10 10)] => [(item "Sulfuras, Hand of Ragnaros" 10 10)])))

       (facts "when the item is Backstage Passes"
              (fact "it increases quality"
                    (update-quality [(item "Backstage passes to a TAFKAL80ETC concert" 20 10)]) => [(item "Backstage passes to a TAFKAL80ETC concert" 19 11)])
              (fact "it increases quality by 2 when there are 10 days or less to sell-in"
                    (update-quality [(item "Backstage passes to a TAFKAL80ETC concert" 10 10)]) => [(item "Backstage passes to a TAFKAL80ETC concert" 9 12)])
              (fact "it increases quality by 3 when there are 5 days or less to sell-in"
                    (update-quality [(item "Backstage passes to a TAFKAL80ETC concert" 5 10)]) => [(item "Backstage passes to a TAFKAL80ETC concert" 4 13)])
              (fact "it resets quality after the concert"
                    (update-quality [(item "Backstage passes to a TAFKAL80ETC concert" 0 10)]) => [(item "Backstage passes to a TAFKAL80ETC concert" -1 0)])))
