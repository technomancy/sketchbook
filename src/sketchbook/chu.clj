(ns sketchbook.chu
  (:use [quil.core]))

(def step 10)

(defn chu-curve [n]
  (line n 0 0 (- (height) n))
    (if (< n (height))
      (recur (+ n step))))

(defn setup []
  (smooth)
  (background-float 0)
  (stroke-int 255)
  (chu-curve 0)
  (with-translation [(width) (height)]
    (with-rotation [PI]
      (chu-curve 0))))

(defsketch chu :title "That Thing I Always Used to Draw in Math Class"
  :setup setup :size [512 512])
