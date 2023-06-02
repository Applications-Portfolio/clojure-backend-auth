(ns cloped.system
  (:require [com.stuartsierra.component :as component]
            [cloped.pedestal :as pedestal]
            [cloped.config :as config]))

(defn new-system
  [env]
  (component/system-map
    :config   (config/map->Config {:env env})
    :pedestal (component/using (pedestal/map->Pedestal {:env env}) [:config])))
