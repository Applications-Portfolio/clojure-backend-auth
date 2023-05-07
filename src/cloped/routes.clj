(ns cloped.routes)

(defn health-check
  [request]
  {:status 200 :body "Ok"})

(def routes
  #{["/health"
     :get health-check
     :route-name :health-check]})
