(ns sketchbook.pop
  (:refer-clojure :exclude [pop])
  (:use [rosado.processing]
        [rosado.processing.applet]))

(def generation (atom 0.0))

(defn setup []
  (no-stroke)
  (framerate 5))

(defn draw []
  (swap! generation inc)
  (dotimes [x 5]
    (dotimes [y 5]
      (fill-float (* 255 (noise x y @generation)))
      (rect (+ (* x 100) 10)
            (+ (* y 100) 10)
            90 90))))

(defapplet pop "Synthpop-inspired Jams"
  setup draw 510 510)

;; To make things go:
;; (run-pop)
;; (stop-pop)