#!/bin/sh
# [CLEANUP=cleanup_cmd] run cmd [args...]
#
# `cmd` and `args...` A command to run and its arguments.
#
# `cleanup_cmd` A command that is called after cmd has exited,
# and gets passed the same arguments as cmd. Additionally, the
# following environment variables are available to that command:
#
# - `RUN_CMD` contains the `cmd` that was passed to `run`;
# - `RUN_EXIT_CODE` contains the exit code of the command.
#
# If `cleanup_cmd` is set, `run` will return the exit code of that
# command. Otherwise, it will return the exit code of `cmd`.
#
function run() {
  local cmd="$1"; shift
  local e_was_set=1
  if ! is_shell_attribute_set e; then
    set -e
    e_was_set=0
  fi

  "$cmd" "$@" &

  local exit_code=0
  wait $! || {
    exit_code=$?
  }

  if [[ "$e_was_set" == 0 ]] && is_shell_attribute_set e; then
    set +e
  fi

  if [[ "$CLEANUP" ]]; then
    echo "--> cleanup: $@"
    echo "    RUN_CMD = '$cmd $@'"
    echo "    RUN_EXIT_CODE = ${exit_code}"
    "$CLEANUP" "$@"
    echo '<-- cleanup'
    return ${exit_code}
  fi

  return $exit_code
}

function is_shell_attribute_set() { # attribute, like "x"
  case "$-" in
    *"$1"*) return 0 ;;
    *)    return 1 ;;
  esac
}
