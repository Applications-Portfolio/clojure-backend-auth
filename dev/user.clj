(ns user
  (:require [dev.nu.morse :as morse]
            [cloped.config :as config]
            [com.stuartsierra.component.repl
             :refer [reset set-init start stop system]]
            [clj-http.client :as client]
            [clojure.edn :as edn]
            [clojure.tools.namespace.repl :refer [refresh set-refresh-dirs]]
            [portal.api :as p]
            [cloped.system]))

(set-refresh-dirs "src")

(defn build-system [_] (cloped.system/new-system :dev))
(set-init build-system)

(defn get-system [] system)

(when (nil? (keys system))
  (println "Starting system")
  (start))

(defn helper-restart-system-after-refresh []
  (start))

(defn restart-system []
  (stop)
  (set-refresh-dirs "src")
  (refresh :after 'user/helper-restart-system-after-refresh))

(defn open-portal []
  (def p (p/open))
  (add-tap #'p/submit))

(defn test-health-check [] (client/get "http://localhost:8890/health"))

(defn test-list-products [] (client/get "http://localhost:8890/products"))

(defn get-from-api [route]
  (let [response (client/get (str "http://localhost:"
                               (config/get-config (:config system)
                                 :port)
                               route))]
    (if (= 200 (:status response))
      (-> response :body edn/read-string)
      response)))

(comment
  (open-portal)
  (morse/launch-in-proc))

; Debugging (refresh) not reloading function with :reload
(comment
  (get-from-api "/products")
  #_"Change interceptor (cloped.interceptors.functions) to print another face"
  #_"Reloading file to 'hot reload' interceptor"
  (load-file "src/cloped/interceptors/functions.clj")
  (get-from-api "/products")
  #_"Check if print is the new face, it should"

  #_"now lets refresh things, hot reloading will freeze"
  #_"Change the face to the old or another different from the current"
  (refresh) ; Refresh on dev/user.clj is set only to "src", this could be an issue?
  (get-from-api "/products")
  #_"Changes do not work anymore on interceptor, even if you try to load the face wont change"
  (load-file "src/cloped/interceptors/functions.clj")
  (get-from-api "/products"))

