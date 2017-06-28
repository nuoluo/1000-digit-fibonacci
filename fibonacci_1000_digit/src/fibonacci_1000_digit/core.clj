(ns fibonacci-1000-digit.core
  (:require [clojure.math.numeric-tower :as math])
  (:gen-class))

(defn fib-seq*
  [a b]
  (cons a (lazy-seq (fib-seq* b (+ a b)))))

(defn seq-way
  []
  (let [fib-seq (fib-seq* (bigint 0) (bigint 1))]
    (inc (count (take-while #(< % (math/expt 10 999)) fib-seq)))))

(defn loop-way
  []
  (loop [a (bigint 0)
         b (bigint 1)
         i 1]
    (if (< a (math/expt 10 999))
      (recur b (+ a b) (inc i))
      i)))

(defn -main
  "Find the 1st fibonacci number with 1000 digits"
  []
  (let [indexA (time (seq-way))
        indexB (time (loop-way))]
    (println "The 1st 1000 digits term is the No." indexA "/" indexB)))
