(ns datomic-sandbox.core
  (:require [datomic.client.api :as d]))

(def client (d/client {:server-type :datomic-local
                       :storage-dir :mem
                       :system "foo"}))

(d/create-database client {:db-name "bar"})

(def conn
  (d/connect client {:db-name "bar"}))

(def name-schema
  [{:db/ident :person/name
    :db/valueType :db.type/string
    :db/cardinality :db.cardinality/one
    :db/doc "A person's name"}])

(comment

  (d/transact conn {:tx-data name-schema})

  (d/transact conn {:tx-data [{:person/name "badger"
                               :user/age "Grace Hopper"}]})
  )


