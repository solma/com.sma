#!/bin/bash
alias gcan='git commit -n --amend'
alias gd='git diff head~1 head'
alias gda='git diff head@{1} head'
alias gdn='git diff --name-status head~1 head'
alias gdna='git diff --name-status head@{1} head'

alias gpull='git pull origin master'
alias gst='git status'

### DATA-CLOUD ####
alias gm='git commit -m'
alias gpl='git pull --rebase'
alias gpu='git push origin HEAD:refs/for/main-dev'

function gpa() {
  pushd . > /dev/null
  for repo in ${WORKSPACE_HOME}/spray ${WORKSPACE_HOME}/spark ${WORKSPACE_HOME}/spark-jobserver\
              ${MY_WORKSPACE}/interview
  do
    cd $repo && gpull && echo
  done
  popd > /dev/null
}

function push() {
    if [ -n "$1" ]; then
      msg=$1
    else
      msg="bulk updates"
    fi
    git add --all . 2>/dev/null; git commit -m "$msg";
    git push origin master
}

function pusha() {
  pushd . > /dev/null
  for repo in ${MY_WORKSPACE}/com.sma
  do
    cd $repo && push && echo
  done
  popd > /dev/null
}
