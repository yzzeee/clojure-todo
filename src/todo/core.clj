(ns todo.core
  (:gen-class)
  (:require [clojure.java.io :as io]
            [integrant.core :as ig]
            [todo.http.server]))

(defonce component (atom nil))

(def config
  (-> (io/resource "config.edn")
      (slurp)
      (ig/read-string)))


(defn shutdown[]
  (when-not (nil? @component)
    (ig/halt! @component)
    (reset! component nil)))

(defn bootup[]
  (reset! component (ig/init config))
  (.addShutdownHook (Runtime/getRuntime)
                    (Thread. shutdown)))

(defn reboot []
  (shutdown)
  (bootup))

(defn -main []
  (bootup))