#!/usr/bin/env bash

# 192 Word Frequency
Leetcode::WordFrequency() {
  awk '{for(i=1;i<=NF;i++) a[$i]++} END {for(k in a) print k,a[k]}' words.txt | sort -k2 -nr
}

# 193 Valid phone numbers
Leetcode::PhoneNumbers() {
  cat file.txt | grep -E "^([0-9]{3}-|\\([0-9]{3}\\) )[0-9]{3}-[0-9]{4}$"
}

# 194 Transpose a file
Leetcode::TransposeAFile() {
  awk '{for(i=1;i<=NF;i++) a[NR,i]=$i} END{for(j=1;j<=NF;j++){str=a[1,j];for(i=2;i<=NR;i++){str=str" "a[i,j]} print str}}' file.txt
}

# 195 tenth line
Leetcode::TenthLine() {
  sed -n "10p" file.txt
}
