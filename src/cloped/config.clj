(ns cloped.config
  (:require [com.stuartsierra.component :as component]
            [clojure.string :as str]
            [clojure.edn :as edn]))

(defprotocol ConfigProtocol
  (get-config [this key]))

(defn stringify-keyword [kwd]
  (-> kwd
      str
      (subs 1)))

(defrecord Config [env]
  component/Lifecycle
  (start [component] component)
  (stop [component] component)

  ConfigProtocol
  (get-config
    [this key]
    (let [str-kwd (stringify-keyword key)]
      (or (System/getenv (-> str-kwd
                           str/upper-case
                           (str/replace "(-|.)" "_")))
          (System/getProperty (-> str-kwd
                                  str/lower-case
                                  (str/replace "(-|_)" ".")))
          (get (with-open [default (clojure.java.io/reader "config/config.edn")]
                 (let [fallback (clojure.edn/read (java.io.PushbackReader. default))]
                   (with-open [env-config (clojure.java.io/reader (str "config/"
                                                                       (if (= env :prod)
                                                                         "prod"
                                                                         "dev")
                                                                       ".config.edn"))]
                     (merge fallback
                                      (clojure.edn/read (java.io.PushbackReader. env-config))))))
               key)))))





