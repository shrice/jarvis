(ns jarvis.handler.index
  (:require [ataraxy.core :as ataraxy]
            [ataraxy.response :as response]
            [clojure.java.io :as io]
            [integrant.core :as ig]))

(defmethod ig/init-key :jarvis.handler/index [_ _]
  (fn [_]
    [::response/ok (io/resource "jarvis/public/index.html")]))
