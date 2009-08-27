(ns sketchbook.pop
  (:use [rosado.processing])
  (:import (processing.core PApplet))
  (:import (javax.swing JFrame JLabel JTextField JButton)))

(def z (atom 0.0))

(defn setup []
  (size 510 510)
  (no-stroke)
  (framerate 5))

(defn draw []
  (background-float 255)
  (swap! z inc)
  (dotimes [x 5]
    (dotimes [y 5]
      (fill-float (* 255 (noise x y @z)))
      (rect (+ (* x 100) 10)
            (+ (* y 100) 10)
            90 90))))

(def p5-applet
     (proxy [PApplet] []
       (setup []
              (binding [*applet* this]
                (setup)))
       (draw []
             (binding [*applet* this]
               (draw)))))

(.init p5-applet)

(defonce swing-frame
  (doto (JFrame. "Processing with Clojure")
    (.setSize 510 510)
    (.add p5-applet)
    (.pack)
    (.show)))
