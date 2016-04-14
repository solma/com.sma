#!/usr/bin/env bash

# set the cell at 2nd column and 2nd row to 4 and the cell at 3rd column
# and 3rd row to 9
cat matrix | awk 'NR==3{$3=9}NR==2{$2=4}{print}' && echo

cat matrix | awk 'NR=NF{$NR=$NR*$NR}{print}' && echo

# cut the first column of the file
cat matrix | cut -d" " -f 2- && echo

# cut the first 3 columns of the file and output each number in a line<br>
cat matrix | awk '{for(i=4;i<=NF;i++)print $i}' && echo

# cut the last row of the file
sed '$d' matrix && echo

# cut the last 2 rows of the file
sed 'N;$!P;$!D;$d' matrix && echo

# cut the 2nd row of the file
sed '2d' matrix && echo

# use 2nd column as the key to the 4th column.
# e.g. if a file named "key" contains
# gawk 'ARGIND==1 {map[$2]=$4;next} {print $1, "-", map[$1]}' matrix key

# count the frequency of the 5th column (adjacent recurrences only
# count one) for rows do not contain number "2"
# note how variable last is referenced!!!
cat matrix | awk '/2/{last=0;next} last!=$5 {c[$5]++} {last=$5} END {for(i in c){print c[i],i}}' && echo

#For each number i appears on the 5th column, count # of rows that have it on the 5th column
# (excluding rows that do not contain number "2")
cat matrix | awk '/2/{last=0;next} {c[$5]++} END {for(i in c){print c[i],i}}' && echo

#calculate the increase rate of the 5th column<br>
cat matrix | awk '{print "last="last,"current="$5,"rate="($5-last)/$5;last=$5}' && echo
