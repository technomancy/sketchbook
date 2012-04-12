(ns sketchbook.chap
  (:use [quil.core]))

(defn setup []
  (smooth)
  (stroke 0))

(def p (atom [0 0]))

(defn within [bound n]
  (cond (< 0 n bound) n
        (< n 0) (Math/abs n)
        (< bound n) (- bound (- n bound))))

(defn walk [n r]
  (+ n (- (random (* 2 r)) r)))

(defn update [[x y]]
  [(within (width) (walk x 10))
   (within (height) (walk y 10))])

(defn colors [x y w h]
  [(* (/ x w) 255) (* (/ y h) 255) 255])

(defn draw []
  (let [[x y] (swap! p update)
        colors (colors x y (width) (height))
        size (+ 10 (* 20 (/ (- (+ (width) (height)) x y) (+ (width) (height)))))
        border (+ 20 (* 30 (+ 1 (Math/sin (/ (frame-count) 100)))))]
    (apply stroke (map (partial + border) colors))
    (apply fill colors)
    (apply ellipse [x y size size])))

(defsketch chap
  :setup setup
  :draw #'draw
  :size [646 400])