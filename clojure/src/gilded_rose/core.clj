(ns gilded-rose.core)

(defn dec-quality
  [item]
  (merge item {:quality (max 0 (dec (:quality item)))}))

(defn has-name?
  [name item]
  (= name (:name item)))

(def backstage-passes? (partial has-name? "Backstage passes to a TAFKAL80ETC concert"))
(def elixir? (partial has-name? "Elixir of the Mongoose"))
(def dexterity? (partial has-name? "+5 Dexterity Vest"))
(def aged-brie? (partial has-name? "Aged Brie"))
(def sulfuras? (partial has-name? "Sulfuras, Hand of Ragnaros"))

(defn update-quality
  [items]
  (map
    (fn [item] (cond
                 (and (< (:sell-in item) 0) (backstage-passes? item)) (merge item {:quality 0})
                 (or (aged-brie? item) (backstage-passes? item)) (if (and (backstage-passes? item) (>= (:sell-in item) 5) (< (:sell-in item) 10))
                                                                                                                  (merge item {:quality (inc (inc (:quality item)))})
                                                                                                                  (if (and (backstage-passes? item) (>= (:sell-in item) 0) (< (:sell-in item) 5))
                                                                                                                    (merge item {:quality (inc (inc (inc (:quality item))))})
                                                                                                                    (if (< (:quality item) 50)
                                                                                                                      (merge item {:quality (inc (:quality item))})
                                                                                                                      item)))
                 (< (:sell-in item) 0) (if (backstage-passes? item)
                                         (merge item {:quality 0})
                                         (if (or (dexterity? item) (elixir? item))
                                           (merge item {:quality (- (:quality item) 2)})
                                           item))
                 (or (dexterity? item) (elixir? item)) (dec-quality item)
                 :else item))
    (map (fn [item]
           (if (not (sulfuras? item))
             (merge item {:sell-in (dec (:sell-in item))})
             item))
         items)))

(defn item
  [item-name, sell-in, quality]
  {:name item-name, :sell-in sell-in, :quality quality})

(defn update-current-inventory
  []
  (let [inventory [(item "+5 Dexterity Vest" 10 20)
                   (item "Aged Brie" 2 0)
                   (item "Elixir of the Mongoose" 5 7)
                   (item "Sulfuras, Hand of Ragnaros" 0 80)
                   (item "Backstage passes to a TAFKAL80ETC concert" 15 20)]]
    (update-quality inventory)))
