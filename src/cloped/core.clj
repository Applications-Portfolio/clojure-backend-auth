(ns cloped.core
  (:require [cloped.system :as system]
            [com.stuartsierra.component :as component]))

(defn start-api [data]
  (component/start (system/new-system (:env data))))
