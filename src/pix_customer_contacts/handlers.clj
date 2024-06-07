(ns pix-customer-contacts.handlers)

(defn get-customer-contacts
  [customer-id]
  {:name "me" :customer-id customer-id  :contacts []})

(defn create-customer-contact
  [customer-id body]
  {:body {:customer-id customer-id :contact body}})

(defn get-customer-contact
  [customer-id contact-id]
  {:name "me" :customer-id customer-id :contact-id contact-id})

(defn get-customer-contact-bank-accounts
  [customer-id contact-id]
   {:body {:name "Darlei" :customer-id customer-id :contact-id contact-id}})

(defn create-customer-contact-bank-account
  [customer-id contact-id bank-account]
  {:customer-id customer-id :contact-id contact-id :bank-account bank-account})
