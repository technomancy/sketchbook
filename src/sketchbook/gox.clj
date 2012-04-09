(ns sketchbook.gox
  (:use [quil.core]))

(defn rotator [x y x-spin y-spin]
  (ellipse (+ x (* (sin (/ (frame-count) 10.0)) (* 100
                                                   x-spin)))
           (+ y (* (cos (/ (frame-count) 10.0)) (* 100
                                                   y-spin)))
           50 50))

(defn draw []
  (fill-int 0 10)
  (rect 0 0 (width) (height))

  (fill-int 255)
  (rotator 100 100 1 1)
  (rotator 350 100 1 -1)
  (rotator 100 350 -1 1)
  (rotator 350 350 -1 -1))

(defsketch gox :title "Maybe something involving trig."
  :setup (fn [] (smooth) (no-stroke))
  :draw draw :size [640 480])
