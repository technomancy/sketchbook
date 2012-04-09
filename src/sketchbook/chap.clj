(ns sketchbook.chap
  (:use [quil.core]))

(defn setup []
  (smooth)
  (stroke 0))

(def p (atom [0 0]))

(def c (atom 255))

(defn within [bound n]
  (cond (< 0 n bound) n
        (< n 0) (Math/abs n)
        (< bound n) (- bound (- n bound))))

(defn walk [n r]
  (+ n (- (random (* 2 r)) r)))

(defn update [[x y]]
  [(within (width) (walk x 5))
   (within (height) (walk y 5))])

(defn draw []
  (swap! p update)
  (swap! c #(within 255 (walk % 5)))
  (fill @c)
  (stroke (- 255 @c))
  (apply ellipse (concat @p [15 15])))

(defsketch chap
  :setup setup
  :draw #'draw
  :size [323 200])