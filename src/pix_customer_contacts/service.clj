(ns pix-customer-contacts.service
  (:require [io.pedestal.http :as http]
            [io.pedestal.http.body-params :as body-params]
            [ring.util.response :as ring-resp]
            [pix-customer-contacts.handlers :as handlers]))

(defn get-customer-contacts
  [request]
  (let [customer-id (get-in request [:path-params :customer-id])]
    (http/json-response (handlers/get-customer-contacts customer-id))))

(defn create-customer-contact
  [request]
  (let [customer-id (get-in request [:path-params :customer-id])
        body (:json-params request)]
    (ring-resp/created "" (handlers/create-customer-contact customer-id body))))

(defn get-customer-contact
 [request]
 (let [customer-id (get-in request [:path-params :customer-id])
       contact-id (get-in request [:path-params :customer-id])]
   (http/json-response (handlers/get-customer-contact customer-id contact-id))))

(defn update-customer-contact
  [request]
  (let [customer-id (get-in request [:path-params :customer-id])
        body (:json-params request)]
    (ring-resp/created "" (handlers/create-customer-contact customer-id body))))

(defn get-customer-contact-bank-accounts
  [request]
  (let [customer-id (get-in request [:path-params :customer-id])
        contact-id (get-in request [:path-params :contact-id])]
    (http/json-response (handlers/get-customer-contact-bank-accounts customer-id contact-id))))

(defn create-customer-contact-bank-account
  [request]
  (let [customer-id (get-in request [:path-params :customer-id])
        contact-id (get-in request [:path-params :contact-id])
        body (:json-params request)]
    (ring-resp/created "" (handlers/create-customer-contact-bank-account customer-id contact-id body))))

(defn update-customer-contact-bank-account
  [request]
  (let [customer-id (get-in request [:path-params :customer-id])
        contact-id (get-in request [:path-params :contact-id])
        body (:json-params request)]
    (ring-resp/created (handlers/create-customer-contact-bank-account customer-id contact-id body))))

(def common-interceptors [(body-params/body-params) http/html-body])

(def routes #{["/api/customers/:customer-id/contacts" :get (conj common-interceptors `get-customer-contacts)]
              ["/api/customers/:customer-id/contacts" :post (conj common-interceptors `create-customer-contact)]
              ["/api/customers/:customer-id/contacts/:contact-id/" :put `update-customer-contact]
              ["/api/customers/:customer-id/contacts/:contact-id/" :post `update-customer-contact]
              ["/api/customers/:customer-id/contacts/:contact-id" :get `get-customer-contact]
              ["/api/customers/:customer-id/contacts/:contact-id/bank-accounts" :get `get-customer-contact-bank-accounts]
              ["/api/customers/:customer-id/contacts/:contact-id/bank-accounts" :post `create-customer-contact-bank-account]
              ["/api/customers/:customer-id/contacts/:contact-id/bank-accounts" :put `update-customer-contact-bank-account]})

(def service {:env :prod
              ;; You can bring your own non-default interceptors. Make
              ;; sure you include routing and set it up right for
              ;; dev-mode. If you do, many other keys for configuring
              ;; default interceptors will be ignored.
              ;; ::http/interceptors []
              ::http/routes routes

              ;; Uncomment next line to enable CORS support, add
              ;; string(s) specifying scheme, host and port for
              ;; allowed source(s):
              ;;
              ;; "http://localhost:8080"
              ;;
              ;;::http/allowed-origins ["scheme://host:port"]

              ;; Tune the Secure Headers
              ;; and specifically the Content Security Policy appropriate to your service/application
              ;; For more information, see: https://content-security-policy.com/
              ;;   See also: https://github.com/pedestal/pedestal/issues/499
              ;;::http/secure-headers {:content-security-policy-settings {:object-src "'none'"
              ;;                                                          :script-src "'unsafe-inline' 'unsafe-eval' 'strict-dynamic' https: http:"
              ;;                                                          :frame-ancestors "'none'"}}

              ;; Root for resource interceptor that is available by default.
              ::http/resource-path "/public"

              ;; Either :jetty, :immutant or :tomcat (see comments in project.clj)
              ;;  This can also be your own chain provider/server-fn -- http://pedestal.io/reference/architecture-overview#_chain_provider
              ::http/type :jetty
              ;;::http/host "localhost"
              ::http/port (Integer. (or (System/getenv "port") 2057))
              ;; Options to pass to the container (Jetty)
              ::http/container-options {:h2c? true
                                        :h2? false
                                        ;:keystore "test/hp/keystore.jks"
                                        ;:key-password "password"
                                        ;:ssl-port 8443
                                        :ssl? false
                                        ;; Alternatively, You can specify your own Jetty HTTPConfiguration
                                        ;; via the `:io.pedestal.http.jetty/http-configuration` container option.
                                        ;:io.pedestal.http.jetty/http-configuration (org.eclipse.jetty.server.HttpConfiguration.)
                                        }})
