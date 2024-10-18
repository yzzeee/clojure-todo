(ns todo.http.server
  (:require [clojure.tools.logging :as log]
            [integrant.core :as ig]
            [org.httpkit.server :as http]))

(defn handler [req]
  {:status 200
   :body "Hello, world"})

(defmethod ig/init-key :http/server [_ {:keys [port handler]}]
  (let [server (http/run-server handler {:port port})]
    (log/info "starting HTTP server on port" port)
    server))


(defmethod ig/halt-key! :http/server [_ server]
  (server :timout 1000)
  (log/info "HTTP server stopped"))