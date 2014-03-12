(ns gilded-rose.core)

(defn backstage-passes? [name] (= name "Backstage passes to a TAFKAL80ETC concert"))
(defn aged-brie? [name] (= name "Aged Brie"))
(defn sulfuras? [name] (= name "Sulfuras, Hand of Ragnaros"))

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

(defprotocol Item
  (update [this]))

(defrecord RegularItem [name sell-in quality]
  Item
  (update [this]
    (let [item (dec-sell-in this)]
      (if (< (:sell-in item) 0)
        (dec-quality item 2)
        (dec-quality item)))))

(defrecord BackstagePassesItem [name sell-in quality]
  Item
  (update [this]
    (let [item (dec-sell-in this)]
      (cond (and (>= (:sell-in item) 5) (< (:sell-in item) 10)) (inc-quality item 2)
            (and (>= (:sell-in item) 0) (< (:sell-in item) 5)) (inc-quality item 3)
            (< (:sell-in item) 0) (reset-quality item)
            :else (inc-quality item)))))

(defrecord AgedBrieItem [name sell-in quality]
  Item
  (update [this] (inc-quality (dec-sell-in this))))

(defrecord SulfurasItem [name sell-in quality]
  Item
  (update [this]
    (if (< (:sell-in item) 0)
      (dec-quality item 2)
      (dec-quality item))))

(defn update-quality
  [items]
  (map update items))

(defn item
  [item-name sell-in quality]
  (cond (backstage-passes? item-name) (BackstagePassesItem. item-name sell-in quality)
        (aged-brie? item-name) (AgedBrieItem. item-name sell-in quality)
        (sulfuras? item-name) (SulfurasItem. item-name sell-in quality)
        :else (RegularItem. item-name sell-in quality)))

(defn update-current-inventory
  []
  (let [inventory [(item "+5 Dexterity Vest" 10 20)
                   (item "Aged Brie" 2 0)
                   (item "Elixir of the Mongoose" 5 7)
                   (item "Sulfuras, Hand of Ragnaros" 0 80)
                   (item "Backstage passes to a TAFKAL80ETC concert" 15 20)]]
    (update-quality inventory)))
