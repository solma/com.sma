#!/usr/bin/env bash

Escaping::Parameters() {
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

Escaping::Dummy() {
# https://goo.gl/utcoeB
  echo \\s
  echo '\\s'
  echo $'\\s'
  echo ''\\\s''
  echo "'\\\s'"
}

main() {
  FIRST="1st Param"
  SECOND="2nd Param"
#  Escaping::Parameters "${FIRST}" "${SECOND}"
#  Escaping::Parameters 000 000
  Escaping::Dummy
}

main "$@"
