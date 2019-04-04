#!/usr/bin/env bash
# dummy callee params consumer
dceec() {
  while [[ "$1" ]]; do
    echo "$1"
    shift
  done
}

# dummy callee params producer
dceep() {
  fname=${FUNCNAME[ 0 ]}
  echo "\"${fname: 0: 1} ${fname: -1}\"" "${fname: 1: 2}"
}

#dummy caller
dcer() {
  # echo from dceep is assigned to params
  params=$(dceep)
  echo $params
  dceec $params
}

dcer
