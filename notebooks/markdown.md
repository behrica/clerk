# M⬇ Markdown Ingestion

This notebook demoes feeding Clerk with markdown files. We currently make no assumption on the kind of source code passed in fence or indented blocks, we just handle code as if it were clojure  

```clj
^:nextjournal.clerk/no-cache
(ns markdown-example
  (:require [nextjournal.clerk :as clerk]
            [nextjournal.markdown :as md]
            [nextjournal.markdown.transform :as md.transform]))
```

- [x] Parse markdown and split in _code_ and _markdown_ chunks
- [ ] optionally rebuild markdown source strings out of the chunks

Nextjournal Markdown library is able to ingest a markdown string

```clj 
(def markdown-input (slurp "https://daringfireball.net/projects/markdown/syntax.text"))
```

and parse it into a nested clojure structure 

```clj 

(def parsed (md/parse markdown-input))
```

which you can manipulate with your favourite clojure functions

```clj 
(def sliced (update parsed :content #(take 10 %)))
```

and render back to hiccup with customisable elements. 

At present, Clerk will split top level forms which are grouped togetehr under the same cell, this is to guarantee that Clerk's dependency analysys among forms will still effectively avoid needless recomputations when code changes.

```clj 
(def hiccup 
  (md.transform/->hiccup (assoc md.transform/default-hiccup-renderers 
                                :doc (partial md.transform/into-markup 
                                              [:div.viewer-markdown])
                                :ruler (fn [_ _]
                                         [:hr.mt-1.mb-1
                                          {:style {:border "10px solid magenta" :border-radius "10px"}}]))
                         sliced))

(clerk/html hiccup)
```

## Appendix

Don't forget the closing slice 🍕 of markdown! 