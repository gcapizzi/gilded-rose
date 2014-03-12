(ns gilded-rose.core)

(defn has-name?
  [name item]
  (= name (:name item)))

(def backstage-passes? (partial has-name? "Backstage passes to a TAFKAL80ETC concert"))
(def aged-brie? (partial has-name? "Aged Brie"))
(def sulfuras? (partial has-name? "Sulfuras, Hand of Ragnaros"))

(defn dec-quality
  ([item]
   (dec-quality item 1))
  ([item times]
   (merge item {:quality (max 0 (- (:quality item) times))})))

(defn inc-quality
  ([item]
   (inc-quality item 1))
  ([item times]
   (merge item {:quality (min 50 (+ (:quality item) times))})))

(defn reset-quality
  [item]
  (merge item {:quality 0}))

(defn dec-sell-in
  [item]
  (merge item {:sell-in (dec (:sell-in item))}))

(defn update-quality-for-item
  [item]
  (cond
    (backstage-passes? item) (cond (and (>= (:sell-in item) 5) (< (:sell-in item) 10)) (inc-quality item 2)
                                   (and (>= (:sell-in item) 0) (< (:sell-in item) 5)) (inc-quality item 3)
                                   (< (:sell-in item) 0) (reset-quality item)
                                   :else (inc-quality item))
    (aged-brie? item) (inc-quality item)
    :else (if (< (:sell-in item) 0)
            (dec-quality item 2)
            (dec-quality item))))

(defn update-sell-in-for-item
  [item]
  (if (not (sulfuras? item))
    (dec-sell-in item)
    item))

(defn update-quality
  [items]
  (map (comp update-quality-for-item update-sell-in-for-item) items))

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
