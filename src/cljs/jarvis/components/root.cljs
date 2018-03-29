(ns jarvis.components.root
  (:require [rum.core :as rum]
            [citrus.core :as citrus]
            [cljs-react-material-ui.core :refer [get-mui-theme]]
            [cljs-react-material-ui.rum :as ui]
            [jarvis.components.home :as home]
            [jarvis.components.dictionary :as dictionary]
            [jarvis.components.header :refer [Header]]))

(rum/defc Root < rum/reactive
  [r]
  (let [{route :handler params :route-params}
        (rum/react (citrus/subscription r [:router]))]
    (ui/mui-theme-provider
     {:mui-theme (get-mui-theme)}
     [:div
      (Header r route)
      (case route
        :home (home/Home r route params)
        :dictionary (dictionary/Dictionary r route params)
        [:div "404"])])))
