#!/usr/bin/env bash

function Parameters::Special() {
  IFS=:
  echo "Description: expand * under double quotes -> IFS.join(positional parameters)"
  echo "Result: " "$*"
  echo "Description: expand * without double quotes -> expand each positional parameter to a separate word"
  echo "Result: " $*
  echo "Description: expand @ under double quotes -> IFS.join(positional parameters)"
  echo "Result: " "$@"
  echo "Description: expand @ under double quotes -> IFS.join(positional parameters)"
  echo "Result: " "$@"
}

main() {
  FIRST=1st
  SECOND=2nd
  Parameters::Special "${FIRST} parameter" "2nd parameter"
}

main "$@"
