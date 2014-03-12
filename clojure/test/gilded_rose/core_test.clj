(ns gilded-rose.core-test
  (:require [midje.sweet :refer :all]
            [gilded-rose.core :refer [item update-quality]]))

(def aged-brie (partial item "Aged Brie"))
(def sulfuras (partial item "Sulfuras, Hand of Ragnaros"))
(def backstage-passes (partial item "Backstage passes to a TAFKAL80ETC concert"))

(facts "about `update-quality`"

       (fact "it lowers both quality and sell-in"
             (update-quality [(item "foo" 10 10)]) => [(item "foo" 9 9)])

       (fact "it never lowers quality below zero"
               (update-quality [(item "foo" 10 0)]) => [(item "foo" 9 0)])

       (facts "when the sell-in date has passed"
              (fact "it lowers quality twice as fast"
                    (update-quality [(item "foo" 0 10)]) => [(item "foo" -1 8)]))

       (facts "when the item is Aged Brie"
              (fact "it increases quality"
                    (update-quality [(aged-brie 10 10)]) => [(aged-brie 9 11)])
              (fact "it never increases quality over 50"
                    (update-quality [(aged-brie 10 50)]) => [(aged-brie 9 50)]))

       (facts "when the item is Sulfuras"
              (fact "it doesn't change quality nor sell-in"
                    (update-quality [(sulfuras 10 10)] => [(sulfuras 10 10)])))

       (facts "when the item is Backstage Passes"
              (fact "it increases quality"
                    (update-quality [(backstage-passes 20 10)]) => [(backstage-passes 19 11)])
              (fact "it increases quality by 2 when there are 10 days or less to sell-in"
                    (update-quality [(backstage-passes 10 10)]) => [(backstage-passes 9 12)])
              (fact "it increases quality by 3 when there are 5 days or less to sell-in"
                    (update-quality [(backstage-passes 5 10)]) => [(backstage-passes 4 13)])
              (fact "it resets quality after the concert"
                    (update-quality [(backstage-passes 0 10)]) => [(backstage-passes -1 0)])))
