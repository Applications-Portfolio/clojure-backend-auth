(ns cloped.routes
  (:require [dev.nu.morse :as morse]))

(defn health-check
  [_]
  {:status 200 :body "Ok"})

(defn list-products
  [request]
  {:status 200 :body '({:id   1
                        :name "Pants"}
                       {:id   2
                        :name "Shoes"}
                       {:id   3
                        :name "Hat"})})

(defn authenticate-user
  [username password])

(def routes
  #{["/health"
     :get `health-check
     :route-name :health-check]

    ["/products"
     :get `list-products
     :route-name :list-products]})

(comment
  (require '[user :as user])
  (user/get-system))

(comment
  (require '[dev.nu.morse :as morse])
  (doto (list-products nil) (morse/inspect)))

(comment
  ; Tap> will send to portal and to morse
  (doto (list-products nil) (tap>)))
