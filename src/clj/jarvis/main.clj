(ns jarvis.main
  (:gen-class)
  (:require [clojure.java.io :as io]
            [duct.core :as duct]))

(duct/load-hierarchy)
(derive :duct.module/cljs :duct/module)

(defn -main [& args]
  (let [keys (or (duct/parse-keys args) [:duct/daemon])]
    (-> (duct/read-config (io/resource "jarvis/config.edn"))
        (duct/prep keys)
        (duct/exec keys))))
