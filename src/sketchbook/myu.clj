(ns sketchbook.myu
  (:use [quil.core]))

;; TODO: calculate position as a function of frame-count instead of by mutation
(def vortex (atom [0 0]))
(def vortex-size 20)
(def vortex-velocity (atom [10 10]))

(defn sine-frame []
  (sin (/ (frame-count) 5.0)))

(defn bounce! []
  (when (or (> (first @vortex) (width)) (neg? (first @vortex)))
    (swap! vortex-velocity (fn [[x y]] [(- x) y])))
  (when (or (> (last @vortex) (height)) (neg? (last @vortex)))
    (swap! vortex-velocity (fn [[x y]] [x (- y)]))))

(defn move! []
  (bounce!)
  (swap! vortex #(map + % @vortex-velocity)))

(defn draw-vortex
  ([alpha]
     (draw-vortex alpha @vortex))
  ([alpha [x y]]
     (fill 255 alpha)
     (ellipse x y vortex-size vortex-size)))

(defn setup []
  (background-float 0)
  (smooth)
  (no-stroke)
  (frame-rate 40))

(defn draw []
  (move!)
  (draw-vortex (* 150 (/ (+ (sine-frame) 1) 2))))

(defsketch myu :title "Something vaguely Birdlike"
  :setup setup :draw draw :size [800 600])
