(ns user
  (:require [dev.nu.morse :as morse]
            [com.stuartsierra.component.repl
             :refer [reset set-init start stop system]]
            [clj-http.client :as client]
            [clojure.edn :as edn]
            [clojure.tools.namespace.repl :refer [refresh set-refresh-dirs]]
            [cloped.system]))

(defn exec-morse [] (morse/launch-in-proc))

(defn get-system [_] (cloped.system/new-system :dev))
(set-init get-system)
(when (nil? (keys system))
  (println "Starting system")
  (start))

(defn helper-restart-system-after-refresh []
  (start))

(defn restart-system []
  (stop)
  (set-refresh-dirs "src" "dev")
  (refresh :after 'user/helper-restart-system-after-refresh))

(defn test-health-check [] (client/get "http://localhost:8890/health"))

(defn test-list-products [] (client/get "http://localhost:8890/products"))



