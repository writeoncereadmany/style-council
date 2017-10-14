#lang racket
(require rackunit)

(define (with-counts list) (cons (first list) (length list)))

(define (count-words text)
  (define words (string-split (string-downcase text)))
  (define counts (map with-counts (group-by identity words)))
  (sort counts > #:key cdr))

(module+ test
  (check-equal? (count-words #<<TEST-SAMPLE
one two three four
TWO THREE FOUR
Three Four
FoUr
TEST-SAMPLE
) '(("four" . 4) ("three" . 3) ("two" . 2) ("one" . 1))))

(call-with-input-file "/Users/tom/Downloads/moby_dick_no_punctuation.txt"
  (compose display count-words port->string))


