#!/usr/bin/env bash

function Set::-U() {
  set -u
  echo "Unset (i.e. uninitialized) parameters and variables except '@' or '*'"\
       "are considered error when performing expansion"
  echo "${1}"
  echo "${UNSET_VARIABLE}"
  set +u
}

main() {
  Set::-U
}

main "$@"
