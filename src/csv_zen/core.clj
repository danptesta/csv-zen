(ns csv-zen.core
  (:require [yada.yada :as yada]

            [csv-zen.routes :as routes]
            [csv-zen.csv.model :as model]))

(defonce server (atom nil))

(defn -main []
  (model/init-db)
  (let [listener  (yada/listener routes/routes
                                 {:port 8080})]
    (reset! server listener)))

(defn reset []
  (when (fn? (:close @server))
    ((:close @server)))
  (-main))

(reset)
