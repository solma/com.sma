#!/usr/bin/env bash

function Set::-U() {
  set -u
  echo "Unset (i.e. uninitialized) parameters and variables except '@' or '*'"\
       "are considered error when performing expansion"
  echo "\${1} is not set"
  echo "${1}"
  echo "${UNSET_VARIABLE}"
  set +u
}

function main() {
#  Set::-U

  echo "null has no special meaning in bash."
  UNSET_VARIABLE=null
  Set::-U "${UNSET_VARIABLE}"
}

main "$@"
