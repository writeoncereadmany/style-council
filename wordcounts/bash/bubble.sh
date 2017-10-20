#!/bin/bash
value=$(<moby_dick_no_punctuation.txt)
rm -rf sorted.txt
touch sorted.txt
echo $value | tr ' ' '\n' | tr '[:upper:]' '[:lower:]' | sort -n | uniq -c | sort -rn > sorted.txt
