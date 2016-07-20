#!/usr/bin/env bash

STRING_AS_ARRAY="String can be indexed as an array using python like syntax"
echo ${STRING_AS_ARRAY:6:-1}
readonly v1=$(echo "${STRING_AS_ARRAY}")
