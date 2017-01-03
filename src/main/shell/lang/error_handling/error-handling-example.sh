#!/bin/sh
set -e

# Source the file with the definition of `run` (previous code snippet).
# Alternatively, you may paste that code directly here and comment the next line.
source error-handling-utils.sh

function main() {
  echo "--> main: $@"
  #  CLEANUP=cleanup run inner "$@" # alternative of using trap
  run inner "$@"
  echo "<-- main"
}

function inner() {
  echo "--> inner: $@"
  sleep 0.5
  if [ "$1" = 'fail' ]; then
    oh_my_god_look_at_this
  fi
  echo "<-- inner"
}

function cleanup() {
  sleep 1
  echo "inside cleanup with ${CLEANUP_PARAM}"
}

CLEANUP_PARAM=hello

trap cleanup EXIT
main "$@"
