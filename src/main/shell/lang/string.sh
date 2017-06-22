#!/usr/bin/env bash

function concatenation() {
  empty=
  empty+=append
  echo ${empty}
}

function substring() {
  STRING_AS_ARRAY="String can be indexed as an array using python like syntax"
  echo ${STRING_AS_ARRAY:6:10}
  echo ${STRING_AS_ARRAY:6:-1}
}

concatenation
substring
