(ns sketchbook.log
  (:use [rosado.processing]
        [rosado.processing.applet]
        [clojure.contrib.str-utils :only [re-split]]
        [clojure.contrib.duck-streams :only [reader]])
  (:import [java.util Date]))

;; Point this at a web server log file!
(def lines
     (atom (line-seq (reader "/home/phil/documents/logs/access.log"))))

(def last-time (atom false))

(def regex
     #"([\d\.]+) - - \[(.*?)\] \".*?\" (\d+) (\d+)")

(defn parse [line]
  ;; IP time code size
  (rest (first (re-seq regex line))))

(def months {"Jan" 0 "Feb" 1 "Mar" 2 "Apr" 3 "May" 4 "Jun" 5
             "Jul" 6 "Aug" 7 "Sep" 8 "Oct" 9 "Nov" 10 "Dec" 11})

(defn parse-time
  "(conj folks-who-should-be-dragged-out-and-shot (:authors java.util.Date))"
  [time] ;; I mean seriously!
  (let [parts (take 6 (.split time "[:/ ]"))]
    (apply #(Date. (- (Integer/parseInt (nth parts 2)) 1900)
                   (months (second parts))
                   (Integer/parseInt (nth parts 0)) %1 %2 %3)
           (map #(Integer/parseInt %) (drop 3 parts)))))

(defn draw-line [[ip time code size]]
  (let [segments (map #(Integer/parseInt %) (re-split #"\." ip))
        radius (* (Math/log (Integer/parseInt size)) 10)]
    (fill-int 255)
    (ellipse (+ (nth segments 0)
                (nth segments 1))
             (+ (nth segments 2)
                (nth segments 3))
             radius radius)))

(defn fade-background []
  (fill-int 0 20)
  (rect 0 0 510 510))

(defn draw []
  (if (seq @lines)
    (let [the-line (parse (first @lines))
          time (.getTime (parse-time (second the-line)))]
      (draw-line the-line)
      (when @last-time
        (.delay *applet* (Math/log (- time @last-time))))
      (reset! last-time time))
    (no-loop))
  (fade-background)
  (swap! lines rest))


(defapplet log :title "Log visualizer"
  :setup (fn [] (no-stroke) (background-int 0)) :draw draw
  :width 510 :height 510)
(run log)

;; (stop log)