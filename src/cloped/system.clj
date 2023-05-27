(ns cloped.system
  (:require [com.stuartsierra.component :as component]
            [cloped.pedestal :as pedestal]))

(defn new-system
  [env]
  (component/system-map
    :pedestal      (pedestal/new-instance env)))
