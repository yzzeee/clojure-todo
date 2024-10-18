(ns todo.db.pool
  (:require [clojure.tools.logging :as log]
            [integrant.core :as ig]
            [next.jdbc.connection :refer [->pool jdbc-url uri->db-spec]])
  (:import (com.zaxxer.hikari HikariDataSource)))


(def db (atom nil))


(defn connect! [url user pass]
  (->pool HikariDataSource
          {:jdbcUrl (jdbc-url (uri->db-spec url))
           :username user
           :password pass}))



(defmethod ig/init-key :db/pool [_ {:keys [url username password]}]
  (reset! db (connect! url username password ))
  (log/info "DataSource mounted")
  db)


(defmethod ig/halt-key! :db/pool [_ _]
  (do
    (log/info "DataSource shutdown")
    (swap! db (fn [arg] (when arg (.close arg)) nil))))