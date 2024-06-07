(ns pix-customer-contacts.datomic.contacts
  (:require [datomic.api :as d]))


(def conn (d/connect "datomic:dev://localhost:4334/pix-customer-contacts"))

(defn get-contacts-by-customer-id
  ([customer-id]
   (get-contacts-by-customer-id customer-id (d/db conn)))
  ([customer-id db]
   (d/q '[:find (pull ?a [*])
          :in $ ?customer-id
          :where [?a :contact/customer-id ?customer-id]]
        db customer-id)))

(defn get-customer-contact
  [customer-id contact-id]
  (d/q '[:find ?e
         :in $ ?customer-id ?contact-id
         :where [?e :contact/customer-id ?customer-id]
                [?e :contact/contact-id ?contact-id]]
       (d/db conn) customer-id contact-id))

(defn get-contact-bank-accounts
  [contact-id]
  (d/q '[:find ?e
         :in $ ?contact-id
         :where [?a :contact/id ?contact]
                [?e :bank-account/contact ?a]]
       (d/db conn) contact-id))

(defn insert-data [data]
  @(d/transact conn [data]))




