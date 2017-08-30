(defproject csv-zen "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.8.0"]
                 [ring "1.6.1"]
                 [com.layerware/hugsql "0.4.7"]
                 [org.postgresql/postgresql "9.4.1207"]
                 [org.clojure/data.csv "0.1.4"]
                 [org.clojure/core.match "0.3.0-alpha5"]
                 [hiccup "1.0.5"]
                 [bidi "2.1.2"]
                 [aleph "0.4.3"]
                 [yada "1.2.6"]]
  :main csv-zen.core

  :profiles
  {:dev {:source-paths ["dev-src"]
         :main csv-zen.dev
         :dependencies [[prone "1.1.4"]]}})
