(ns cloped.dev
  (:require [dev.nu.morse :as morse]
            [com.stuartsierra.component.repl
             :refer [reset set-init start stop system]]
            [clj-http.client :as client]
            [cloped.system :as system]))

(println "Hello from REPL")

(morse/launch-in-proc)

(set-init (constantly (system/new-system :dev)))
(when (nil? (keys system))
  (println "Starting system")
  (start))

;; Util function for dev reload
(defn reload [] (load-file "src/cloped/dev.clj"))

(defn test-health-check [] (client/get "http://localhost:8890/health"))

