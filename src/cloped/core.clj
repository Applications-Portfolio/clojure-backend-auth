(ns cloped.core
  (:require [cloped.system :as system]
            [com.stuartsierra.component :as component]))

(defn -main [data]
  (component/start (system/new-system (:env data :dev))))
