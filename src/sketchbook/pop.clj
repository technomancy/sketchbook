(ns sketchbook.pop
  (:refer-clojure :exclude [pop])
  (:use [rosado.processing]
        [rosado.processing.applet]))

;; TODO: define these modulo frame-count instead of by mutation
(def x (atom 0))
(def y (atom 0))

(defn setup []
  (no-stroke)
  (framerate 12))

(defn draw []
  (fill-float (* 255 (noise @x @y (.frameCount *applet*))))
  (rect (+ (* @x 100) 10)
        (+ (* @y 100) 10)
        90 90)

  (swap! x inc)
  (when (>= @x 5)
    (reset! x 0)
    (swap! y #(mod (inc %) 5))))

(defapplet pop "Synthpop-inspired Jams"
  setup draw 510 510)

;; To make things go:
;; (run-pop)
;; (stop-pop)