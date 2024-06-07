(ns pix-customer-contacts.handlers
  (:require [pix-customer-contacts.datomic.contacts :as db-contacts])
  (:import (java.util UUID)))


(defn random-uuid []
  (UUID/randomUUID))

(defn get-customer-contacts
  [customer-id]
  (db-contacts/get-contacts-by-customer-id customer-id))


(defn create-customer-contact
  [customer-id body]
  (let [contact-id (random-uuid)]
    (db-contacts/insert-data (assoc body :contact/customer-id customer-id :contact/id contact-id))))


(defn update-customer-contact
  [customer-id contact-id body]
  (let [customer-uuid (UUID/fromString customer-id)
        contact-uuid (UUID/fromString contact-id)]
    (db-contacts/insert-data (assoc body :contact/customer-id customer-uuid :contact/id contact-uuid))))


(defn get-customer-contact
  [customer-id contact-id]
  (let [customer-uuid (UUID/fromString customer-id)
        contact-uuid (UUID/fromString contact-id)]
      (db-contacts/get-customer-contact customer-uuid contact-uuid)))

(defn get-customer-contact-bank-accounts
  [_ contact-id]
  (db-contacts/get-contact-bank-accounts (UUID/fromString contact-id)))

(defn create-customer-contact-bank-account
  [_ contact-id bank-account]
  (db-contacts/insert-data (assoc bank-account :contact-id (UUID/fromString contact-id))))


