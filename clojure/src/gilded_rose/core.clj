(ns gilded-rose.core)

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
  (update-item-quality [this])
  (update-item-sell-in [this]))

(defrecord RegularItem [name sell-in quality]
  Item
  (update-item-quality [this]
    (if (< (:sell-in this) 0)
      (dec-quality this 2)
      (dec-quality this)))
  (update-item-sell-in [this]
    (dec-sell-in this)))

(defrecord BackstagePassesItem [name sell-in quality]
  Item
  (update-item-quality [this]
    (cond (and (>= (:sell-in this) 5) (< (:sell-in this) 10)) (inc-quality this 2)
          (and (>= (:sell-in this) 0) (< (:sell-in this) 5)) (inc-quality this 3)
          (< (:sell-in this) 0) (reset-quality this)
          :else (inc-quality this)))
  (update-item-sell-in [this]
    (dec-sell-in this)))

(defrecord AgedBrieItem [name sell-in quality]
  Item
  (update-item-quality [this] (inc-quality this))
  (update-item-sell-in [this] (dec-sell-in this)))

(defrecord SulfurasItem [name sell-in quality]
  Item
  (update-item-quality [this]
    (if (< (:sell-in this) 0)
      (dec-quality this 2)
      (dec-quality this)))
  (update-item-sell-in [this]))

(defn update-quality
  [items]
  (map (comp update-item-quality update-item-sell-in) items))

(defn item
  [item-name sell-in quality]
  (cond (= item-name "Backstage passes to a TAFKAL80ETC concert") (BackstagePassesItem. item-name sell-in quality)
        (= item-name "Aged Brie") (AgedBrieItem. item-name sell-in quality)
        (= item-name "Sulfuras, Hand of Ragnaros") (SulfurasItem. item-name sell-in quality)
        :else (RegularItem. item-name sell-in quality)))

(defn update-current-inventory
  []
  (let [inventory [(item "+5 Dexterity Vest" 10 20)
                   (item "Aged Brie" 2 0)
                   (item "Elixir of the Mongoose" 5 7)
                   (item "Sulfuras, Hand of Ragnaros" 0 80)
                   (item "Backstage passes to a TAFKAL80ETC concert" 15 20)]]
    (update-quality inventory)))
