(defproject todo "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :license {:name "EPL-2.0 OR GPL-2.0-or-later WITH Classpath-exception-2.0"
            :url  "https://www.eclipse.org/legal/epl-2.0/"}
  :dependencies [[org.clojure/clojure "1.12.0"]
                 [org.clojure/tools.logging "1.3.0"]
                 [integrant "0.13.0"]
                 [ch.qos.logback/logback-classic "1.5.6"]
                 [http-kit "2.8.0"]
                 [compojure "1.7.1"]
                 [clj-http/clj-http "3.13.0"]
                 [ring/ring-core "1.12.2"]
                 [ring/ring-defaults "0.5.0"]
                 [metosin/ring-http-response "0.9.4"]
                 [metosin/muuntaja "0.6.10"]
                 [cheshire/cheshire "5.13.0"]
                 [com.zaxxer/HikariCP "6.0.0"]
                 [com.github.seancorfield/next.jdbc "1.3.955"]
                 [com.mysql/mysql-connector-j "9.1.0"]
                 [org.mariadb.jdbc/mariadb-java-client "3.4.1"]]

  :repl-options {:init-ns todo.core})
