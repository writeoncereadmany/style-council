cat moby_dick_no_punctuation.txt \
| tr [:blank:] '\n' \
| grep --invert-match '^$' \
| tr [:upper:] [:lower:] \
| sort \
| uniq --count \
| sort --ignore-leading-blanks --general-numeric-sort --reverse \
| head
