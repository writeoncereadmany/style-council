cat moby_dick_no_punctuation.txt | tr ' ' '\n' | awk '{ print tolower($0)}' | sort -r | uniq -c | sort -rn
