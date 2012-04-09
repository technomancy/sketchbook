(ns sketchbook.pch
  (:use [quil.core]))

(defn setup []
  ;; (color-mode HSB)
  (fill-int 255)
  (no-stroke)
  (rect 0 0 (width) (height))
  (frame-rate 60))

(defn draw []
  (fill (mod (* 2 (frame-count)) 255) 200 230)
  (with-translation [(/ (width) 2) (/ (height) 2)]
    (with-rotation [(frame-count)]
      (let [a (mod (/ (frame-count) 10) 100)
            b (* 2 a)]
        (triangle a a b a a b)))))

(defsketch pch :title "Peachy Keen"
  :setup setup :draw draw :size [400 400])
