(ns todo.db.handler
  (:require [next.jdbc :as jdbc]
            [todo.db.pool :refer [db]]))


(defn select-todos
  ([]
   (jdbc/execute! @db ["select * from todos"]))
  ([id]
   (jdbc/execute! @db ["select * from todos where id=?" id])))

(defn insert-todo [content]
  "return id"
  (-> (jdbc/execute! @db ["insert into todos (content) values (?)" content]
                     {:return-keys true})
      first
      :insert_id))

(defn update-todo [{:keys [id content done]}]
  (jdbc/execute! @db ["update todos set content=?, done=? where id=?" content done id]))


(defn delete-todo [id]
  (jdbc/execute! @db ["delete from todos where id=?" id]))
