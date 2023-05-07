(ns cloped.system
  (:require [com.stuartsierra.component :as component]
            [io.pedestal.http :as http]
            [cloped.pedestal :as pedestal]
            [cloped.routes :as routes]))

(defn new-system
  [env]
  (component/system-map
    :service-map
    {:env          env
     ::http/routes routes/routes
     ::http/type   :jetty
     ::http/port   8890
     ::http/join?  false}

    :pedestal
    (component/using
      (pedestal/new-instance)
      [:service-map])))
