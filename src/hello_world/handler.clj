(ns hello-world.handler
  (:require [compojure.core :refer :all]
            [compojure.route :as route]
            [ring.util.anti-forgery :as util]
            [hiccup.page :as page]
            [hiccup.form :as form]
            [ring.middleware.defaults :refer [wrap-defaults site-defaults]]))

(defn registration-form []
  [:div.row.mt-3
   [:div.col-6
    (form/form-to
      [:post (str "register")]
      (util/anti-forgery-field)
      [:input {:type "submit" :value "Submit"}])]])

(defn page [req]
   (page/html5
     (registration-form)))

(defroutes app-routes
  (GET "/" [] page)
  (POST "/register" [] "OK, it works!")
  (route/not-found "Not Found"))

(def app
  (wrap-defaults app-routes site-defaults))
