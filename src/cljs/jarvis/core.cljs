(ns jarvis.core
  (:require [rum.core :as r]
            [citrus.core :as citrus]
            [goog.dom :as dom]
            [cljs-react-material-ui.core :refer [get-mui-theme color]]
            [cljs-react-material-ui.icons :as ic]
            [cljs-react-material-ui.rum :as ui]
            [jarvis.router :as router]
            [jarvis.components.root :refer [Root]]
            [jarvis.controllers.router :as router-controller]))

;; create Reconciler instance
(defonce reconciler
  (citrus/reconciler
   {:state           (atom {})
    :controllers     {;:articles     articles/control
                      ;:tag-articles tag-articles/control
                      ;:tags         tags/control
                      ;:article      article/control
                      ;:comments     comments/control
                      ;:user         user/control
                      ;:profile      profile/control
                      :router       router-controller/control}}))

;; initialize controllers
(defonce init-ctrl (citrus/broadcast-sync! reconciler :init))

(router/start! #(citrus/dispatch! reconciler :router :push %) router/routes)

(defn render! []
  (r/mount (Root reconciler) (dom/getElement "app")))

(defn ^:export init []
  (render!))
