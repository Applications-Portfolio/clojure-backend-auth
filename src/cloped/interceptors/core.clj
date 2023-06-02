(ns cloped.interceptors.core
  (:require [io.pedestal.interceptor :as interceptor]
            [io.pedestal.http :as http]))

;; Workaround for reloading interceptors without restarting system
;; You should use your :enter :leave an :error functions like: (fn [context] (your-fn context))
(require '[cloped.interceptors.functions :as interceptor-functions] :reload)

(def authentication-interceptor
  (interceptor/interceptor
    {:name  ::authenticate
     :enter (fn [context] (interceptor-functions/authenticate context))}))

(defn conj-interceptor [service-map interceptor]
  (update service-map ::http/interceptors conj interceptor))

(defn apply-interceptors [service-map]
  (-> service-map
    (conj-interceptor authentication-interceptor)))


