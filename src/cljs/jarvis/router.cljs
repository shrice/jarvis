(ns jarvis.router
  (:require [bidi.bidi :as bidi]
            [goog.events :as events]
            [clojure.string :as str]))

(def routes
  ["/" [["" :home]
        ["dictionary" :dictionary]
        [["page/" :page] :home]
        [["tag/" :id] [["" :tag]
                       [["/page/" :page] :tag]]]
        [["article/" :id] :article]
        ["editor" [["" :editor]
                   [["/" :slug] :editor]]]
        ["login" :login]
        ["register" :register]]])

(defn start! [on-set-page routes]
  (letfn [(handle-route []
            (let [uri (str/replace js/location.hash "#" "")]
              (->> (if-not (empty? uri) uri "/")
                   (bidi/match-route routes)
                   on-set-page)))]
    (events/listen js/window "hashchange" handle-route)
    (handle-route)
    handle-route))


(defn stop! [handler]
  (events/unlisten js/window "hashchange" handler))
