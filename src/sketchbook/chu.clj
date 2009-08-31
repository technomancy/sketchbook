(ns sketchbook.chu
  (:use [rosado.processing]
        [rosado.processing.applet]))

(def step 10)

(defn height []
  (.getHeight *applet*))

(defn width []
  (.getWidth *applet*))

(defn chu-curve [n]
  (line n 0 0 (- (height) n))
    (if (< n (height))
      (recur (+ n step))))

(defn setup []
  (smooth)
  (background-float 0)
  (stroke-int 255)
  (framerate 0)
  (chu-curve 0)
  (with-translation [(width) (height)]
    (with-rotation [PI]
      (chu-curve 0))))

(defapplet chu "That Thing I Always Used to Draw in Math Class"
  setup #() 512 512)

;; (run-chu)
;; (stop-chu)