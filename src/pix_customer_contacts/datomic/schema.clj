(ns pix-customer-contacts.datomic.schema
  (:require [datomic.api :as d]))


(def contact [{:db/ident :contact/id
               :db/valueType :db.type/uuid
               :db/cardinality :db.cardinality/one
               :db/unique :db.unique/identity}
              {:db/ident :contact/customer-id
               :db/valueType :db.type/uuid
               :db/cardinality :db.cardinality/one}
              {:db/ident :contact/name
               :db/valueType :db.type/string
               :db/cardinality :db.cardinality/one}
              {:db/ident :contact/tax-id
               :db/valueType :db.type/string
               :db/cardinality :db.cardinality/one}
              {:db/ident :contact/email
               :db/valueType :db.type/string
               :db/cardinality :db.cardinality/one}
              {:db/ident :contact/created-at
               :db/valueType :db.type/instant
               :db/cardinality :db.cardinality/one}])
(def contact-bank-account [{:db/ident :bank-account/id
                            :db/valueType :db.type/uuid
                            :db/cardinality :db.cardinality/one
                            :db/unique :db.unique/identity}
                           {:db/ident :bank-account/contact
                            :db/valueType :db.type/ref
                            :db/cardinality :db.cardinality/one}
                           {:db/ident :bank-account/bank-id
                            :db/valueType :db.type/string
                            :db/cardinality :db.cardinality/one}
                           {:db/ident :bank-account/account-id
                            :db/valueType :db.type/long
                            :db/cardinality :db.cardinality/one}
                           {:db/ident :bank-account/status
                            :db/valueType :db.type/keyword
                            :db/cardinality :db.cardinality/one}
                           {:db/ident :bank-account/created-at
                            :db/valueType :db.type/instant
                            :db/cardinality :db.cardinality/one}])


(def schema (into [] (concat contact contact-bank-account)))

(defn create-database
  [db-uri]
  (d/create-database db-uri))

(defn create-schema
  [connection schema]
  @(d/transact connection schema))
