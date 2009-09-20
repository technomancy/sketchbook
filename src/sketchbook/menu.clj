(ns sketchbook.menu
  (:use [rosado.processing.applet]
        [rosado.processing])
  (:gen-class))

(def sketches '[chu myu pop log])

(doseq [s sketches]
  (require (symbol (str "sketchbook." (name s)))))

(defn in-bounds? [x y]
  (and (> x 45)
       (< x 145)
       (> y 115)
       (< y 385)))

(defn which-sketch [x y]
  (if (in-bounds? x y)
    (nth sketches (/ (- y 110) 75))))

(defn run-sketch [sketch]
  (run (var-get (ns-resolve (symbol (str "sketchbook." (name sketch)))
                            sketch))))

(defn setup []
  (background-int 255)
  (fill-int 0)
  (text-font (load-font "DejaVuSansMono-Bold-36.vlw"))
  (string->text "technomancy" 10 40)
  (string->text "sketchbook" 15 80)

  (dotimes [n (count sketches)]
    (string->text (name (sketches n)) 65 (+ 150 (* 75 n)))))

(defn mouseClicked [event]
  (let [x (.getX event)
        y(.getY event)]
    (if (in-bounds? x y)
      (run-sketch (which-sketch x y)))))

(defapplet menu
  :width 300 :height 800
  :setup setup :mouseClicked mouseClicked)

(defn -main []
  (run menu))

;; (stop menu)