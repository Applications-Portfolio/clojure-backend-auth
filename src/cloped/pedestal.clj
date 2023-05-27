(ns cloped.pedestal
  (:require [com.stuartsierra.component :as component]
            [io.pedestal.http :as http]
            [io.pedestal.http.route :as route]
            [cloped.routes :as routes]
            [cloped.interceptors :as interceptors]))

(defn test?
  [service-map]
  (= :test (:env service-map)))

(defrecord Pedestal [env
                     service]
  component/Lifecycle
  (start [this]
    ;; This is for interceptor manual download
    (when-not service
      (cond-> {:env          env
               ::http/routes (if (= :prod env)
                               routes/routes
                               #(route/expand-routes (deref (var routes/routes))))
               ::http/type   :jetty
               ::http/port   8890
               ::http/join?  false}
        true               http/default-interceptors
        (= env :dev)       http/dev-interceptors
        true               interceptors/apply-interceptors
        true               http/create-server
        (not= env :test)   http/start
        true               ((partial assoc this :service)))))
  (stop [this]
    (when (and service (not= env :test))
      (http/stop service))
    (assoc this :service nil)))

(defn new-instance [env] (map->Pedestal {:env env}))
