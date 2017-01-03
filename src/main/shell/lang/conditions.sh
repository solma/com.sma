#!/usr/bin/env bash

(( 1 )) && echo "eval is true"

var=wa
if [[ "$var" != wae ]]; then
  echo $var
fi
