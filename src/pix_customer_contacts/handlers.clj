(ns pix-customer-contacts.handlers
  (:require pix-customer-contacts.datomic.contacts :as db-contacts)
  (:import (java.util UUID)))


(defn random-uuid []
  (UUID/randomUUID))

(defn get-customer-contacts
  [customer-id]
  (db-contacts/get-contacts-by-customer-id customer-id))

(defn create-customer-contact
  [customer-id body]
  (db-contacts/insert-data (assoc body :customer-id customer-id  :contact-id (random-uuid))))

(defn get-customer-contact
  [customer-id contact-id]
  (db-contacts/get-customer-contact customer-id contact-id))

(defn get-customer-contact-bank-accounts
  [_ contact-id]
   (db-contacts/get-contact-bank-accounts contact-id))

(defn create-customer-contact-bank-account
  [_ contact-id bank-account]
  (db-contacts/insert-data (assoc bank-account :contact-id contact-id)))


