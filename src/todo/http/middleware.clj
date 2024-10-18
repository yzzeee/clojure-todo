(ns todo.http.middleware
  (:require [clojure.tools.logging :as log]
            [ring.middleware.defaults :refer :all]
            [muuntaja.middleware :refer [wrap-format]]
            [ring.middleware.keyword-params :refer [wrap-keyword-params]]
            [cheshire.core :refer [generate-string]]))

(defn wrap-internal-error [handler]
  (fn [req]
    (try
      (handler req)
      (catch Throwable t
        (log/error t)
        {:status 500
         :headers {"Content-Type" "application/json"}
         :body (generate-string {:status 500
                                 :error (or (.getMessage t)  "Internal Server Error")})}
        ))))


(defn wrap-base [handler]
  (wrap-defaults
    (-> handler
        wrap-format
        wrap-keyword-params
        wrap-internal-error)
    (-> site-defaults
        (assoc-in [:security :anti-forgery] false)
        (dissoc :session))))