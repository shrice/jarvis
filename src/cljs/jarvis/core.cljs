(ns jarvis.core
  (:require [rum.core :as r]))

(r/defc top
  []
  [:main "main container."])

(defn render!
  []
  (.log js/console "test.")
  (r/mount (top) (js/document.getElementById "app")))

(defn ^:export init
  []
  (render!))
