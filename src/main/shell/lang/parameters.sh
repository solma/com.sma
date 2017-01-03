#!/usr/bin/env bash

function Parameters::Special() {
  IFS=:
  FIRST=$1
  SECOND=$2
  echo "First: ${FIRST}" "Second: ${SECOND}"
  echo "Description: expand * under double quotes -> IFS.join(positional parameters)"
  echo "$*"
  echo "Description: expand * without double quotes -> expand each positional parameter to a separate word"
  echo $*
  echo "Description: expand @ under double quotes -> expand each positional parameter to a separate word"
  echo "$@"
  echo "Description: expand @ without double quotes -> expand each positional parameter to a separate word"
  echo $@
}

function main() {
  FIRST="1st Param"
  SECOND="2nd Param"
  Parameters::Special "${FIRST}" "${SECOND}"
#  Parameters::Special 000 000
}

main "$@"
