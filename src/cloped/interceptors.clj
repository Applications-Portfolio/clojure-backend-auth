(ns cloped.interceptors
  (:require [io.pedestal.interceptor :as interceptor]
            [io.pedestal.http :as http]))

(def authentication-interceptor
  (interceptor/interceptor
    {:name  ::authenticate
     :enter (fn [context]
              context)}))

(defn conj-interceptor [service-map interceptor]
  (update service-map ::http/interceptors conj interceptor))

(defn apply-interceptors [service-map]
  (-> service-map
      (conj-interceptor authentication-interceptor)))

