(ns cloped.routes)

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

(def routes
  #{["/health"
     :get `health-check
     :route-name :health-check]

    ["/products"
     :get `list-products
     :route-name :list-products]})
