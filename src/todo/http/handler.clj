(ns todo.http.handler
  (:require
    [clojure.tools.logging :as log]
    [compojure.core :refer :all]
    [compojure.route :as route]
    [integrant.core :as ig]))



(defroutes
  base-route
  (GET "/" [] "Hello World")
  (GET "/error" [] (throw (Exception. "What is wrong with you!"))))


(defn handler []
  (routes
    base-route
    (route/not-found "404")))



(defmethod ig/init-key :http/handler [_ _]
  (log/info "handler created")
  (handler))