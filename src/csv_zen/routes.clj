(ns csv-zen.routes
  (:require [bidi.vhosts :refer [vhosts-model]]
            [ring.handler.dump :as dump]
            [yada.yada :as yada]
            [yada.resources.classpath-resource :refer [new-classpath-resource]]

            [csv-zen.dashboard.views :as dashboard-views]
            [csv-zen.csv.handlers :as handlers]))

(def scheme "http")
(def host "localhost:8080")

(def routes
  (vhosts-model
    [[(str scheme "://" host)]
     ["" [["/dump" dump/handle-dump]
          ["/dashboard" (yada/resource
                         {:id :dashboard
                          :produces {:media-type "text/html"
                                     :language "en"}
                          :response (fn [ctx]
                                      (dashboard-views/page))})]
          ["/endpoints" (yada/resource
                         {:id :endpoints
                          :produces {:media-type "application/json"}
                          :methods {:post
                                    {:response
                                     (fn [ctx]
                                       (handlers/create-endpoint-request ctx))}}})]
          [["/endpoint/" :id]
           {"" (yada/resource
                {:id :endpoint
                 :produces {:media-type "application/json"}
                 :methods {:post
                           {:consumes "multipart/form-data"
                            :parameters {:form {:file String}}
                            :response handlers/upload-request}}})
            ["/upload/" :upload-id] (yada/resource
                                     {:id :endpoint-upload
                                      :response nil})}]
          ["" (new-classpath-resource "homepage"
                                      {:index-files ["index.html"]})]]]]))
