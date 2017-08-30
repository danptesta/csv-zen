(ns csv-zen.csv.handlers
  (:require [yada.yada :as yada]

            [csv-zen.csv.model :as model]))


(defn create-endpoint-request [ctx]
  (let [endpoint-id (model/create-endpoint)
        response (:response ctx)]
    (-> response
        (assoc :status 201)
        (assoc :body {:endpoint {:id endpoint-id}})
        (assoc-in [:headers "Location"]
                  (yada/url-for ctx :endpoint {:route-params {:id endpoint-id}})))))

(defn upload-request [ctx]
  (let [req (:request ctx)
        response (:response ctx)
        endpoint-id (get-in req [:route-params :id])
        endpoint-id (java.util.UUID/fromString endpoint-id)
        multipart (get-in ctx [:parameters :form :file])
        upload-id (model/do-upload endpoint-id (java.io.StringReader. multipart))]
    (-> response
        (assoc :status 201)
        (assoc :body {:upload {:id upload-id
                               :endpoint {:id endpoint-id}}})
        (assoc-in [:headers "Location"]
                  (yada/url-for ctx :endpoint-upload {:route-params {:id endpoint-id :upload-id upload-id}})))))
