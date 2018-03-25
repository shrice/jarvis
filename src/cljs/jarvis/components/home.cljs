(ns jarvis.components.home)

(defn Home
  [r route params]
  (.log js/console params)
  [:div "Home"])
