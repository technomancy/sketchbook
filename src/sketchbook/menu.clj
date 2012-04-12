(ns sketchbook.menu
  (:gen-class))

(def sketches '#{chap chu gox myu pch pop log})

(defn help []
  (println "Please provide a sketch to display.")
  (apply println "Available sketches:" (seq sketches))
  (System/exit 1))

(defn -main [& [sketch]]
  (if (and sketch (sketches (symbol sketch)))
    (require (symbol (str "sketchbook." sketch)))
    (help)))
