;; # Controlling Visibility 🙈
;; You can control visibility in Clerk by setting the `:nextjournal.clerk/visibility` which takes a keyword or a set of keywords. Valid values are:
;; * `:show` (the default)
;; * `:hide` to hide the cells w
;; * `:hide-result` to hide the result of evaluation
;; * `:fold` which shows the cells collapsed and lets users uncollapse them

;; A declaration on the `ns` form lets all code cells in the notebook inherit the value. On the `ns` form you can also use `:fold-ns` or `:hide-ns` if you'd like an option to only apply to the namespace form.
^{:nextjournal.clerk/visibility #{:fold}}
(ns visibility
  (:require [clojure.string :as str]
            [nextjournal.clerk :as clerk]))

;; So a cell will only show the result now while you can uncollapse the code cell.
(+ 39 3)

;; If you want, you can override it. So the following cell is shown:
^{::clerk/visibility :show} (range 25)

;; While this one is completely hidden, without the ability to uncollapse it.
^{::clerk/visibility :hide} (shuffle (range 25))

;; In the case you'd like to hide the result of a cell, use `:hide-result`.
^{::clerk/visibility #{:show :hide-result}}
(range 500)

;; In a follow-up, we'll remove the `::clerk/visibility` metadata from the code cells to not distract from the essence.

;; Fin.
#_ (clerk/show! "notebooks/visibility.clj")
