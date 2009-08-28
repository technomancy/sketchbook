(ns sketchbook.applet
  (:use rosado.processing)
  (:import (javax.swing JFrame)))

(defmacro defapplet
  [name title setup draw width height]
  `(do
     (def ~name
          (proxy [processing.core.PApplet] []
            (setup []
                   (binding [*applet* ~'this]
                     (size ~width ~height)
                     (~setup)))
            (draw []
                  (binding [*applet* ~'this]
                    (~draw)))))
     (defn ~(symbol (str "run-" name)) []
       (.init ~name)
       (doto (JFrame. ~title)
         (.setSize ~width ~height)
         (.add ~name)
         (.pack)
         (.show)))))
