########
BASHCONFIG_DIR=${WORKSPACE_HOME}/com.sma/src/main/shell/bashconfig/
source ${BASHCONFIG_DIR}/git.sh
source ${BASHCONFIG_DIR}/git_completion.sh
########
############################################
# Command alias
###########################################
alias bc='bc -l'
alias brc='vim $HOME/.bash_profile' # shortcut to bash profile
alias cls='clear'
alias df='df -H'
alias du='du -ch'
alias h='history'
alias zrc='vim $HOME/.zshrc'

#http://apple.stackexchange.com/questions/128402/three-finger-trackpad-gesture-on-mission-control-and-expose-not-working-in-my-ma
alias kd='killall Dock' # quick fix for three-finger gesture failure on Mac
alias mnt='sudo ~/Dropbox/ntfs.sh'
alias python='python2.7'
alias top='top -o mem'
alias umnt='sudo umount -f /Volumes/SeagateBackupPlusDrive/' # eject mounted volume

# show hidden files in Finder
alias shf='defaults write com.apple.finder AppleShowAllFiles YES; killall Finder /System/Library/CoreServices/Finder.app'
alias hhf='defaults write com.apple.finder AppleShowAllFiles NO; killall Finder /System/Library/CoreServices/Finder.app'

########### ls alias and functions ###########################
alias l.='ls -d .*'
alias ll='ls -lah'

function ld(){
  ls -d --color=always $1/*/
}
alias lld='ld .'

# find $1 files that contain $2 and are modified most recently
function lm(){
  if [[ "$2" ]]; then
    ls -thl | cat | grep $2 | head -$1 2>/dev/null
  else
    ls -thl | head -$1 2>/dev/null
  fi
}
alias lsc='ls -aF --color=always'

############################################
# Cd alias
###########################################
alias ..='cd ..'
alias ...='cd ../../'
alias .3='cd ../../../'

alias cdl='open ${HOME}/Dropbox/pl-books/C++-for-Java-Programmers.pdf'
alias cds='cd ${WORKSPACE_HOME}/com.sma/src/main'
alias d='cd ${HOME}/Desktop/'
alias pl='open ${HOME}/Dropbox/pl-books'
alias pd='open $HOME/Downloads'

############################################
# Miscellaneous functions
###########################################

############## sftp alias and functions ##################
declare -A SFTP_FILE_MAP_LOCAL
declare -A SFTP_FILE_MAP_REMOTE
SFTP_FILE_MAP_LOCAL[p]="/Users/solma/workspace/playground/pinax_ss.sh"
SFTP_FILE_MAP_REMOTE[p]="workspace/pinax_ss.sh"
SFTP_FILE_MAP_LOCAL[b]="/Users/solma/workspace/playground/.bashrc"
SFTP_FILE_MAP_REMOTE[b]="./.bashrc"
SFTP_FILE_MAP_LOCAL[h]="/Users/solma/workspace/playground/helper.sh"
SFTP_FILE_MAP_REMOTE[h]="workspace/helper.sh"

function sfu(){
if ! [[ "$1" ]]; then
  for k in "${!SFTP_FILE_MAP_LOCAL[@]}"; do
    sfu $k
  done
  return 0
fi

sftp ${SOLMA_WORKSTATION_DNS} <<-COMMANDS
put ${SFTP_FILE_MAP_LOCAL[$1]} ${SFTP_FILE_MAP_REMOTE[$1]}
COMMANDS
}

function sfd(){
if ! [[ "$1" ]]; then
  for k in "${!SFTP_FILE_MAP_LOCAL[@]}"; do
    sfd $k
  done
  return 0
fi

#sftp solma@${SOLMA_WORKSTATION_DNS}:workspace/pinax_ss.sh
sftp ${SOLMA_WORKSTATION_DNS} <<-COMMANDS
get ${SFTP_FILE_MAP_REMOTE[$1]} ${SFTP_FILE_MAP_LOCAL[$1]}
COMMANDS
}

###### fast accesses to folder ############

# fast track to folder under ${MY_WORKSPACE}
function cdm(){
  dest=${MY_WORKSPACE}
  if [[ "$#" -eq 1 ]]
  then
    dest=$dest/$1
  fi
  cd $dest
}

# fast track to folder under ${WORKSPACE_HOME}
function cdw(){
  dest=${WORKSPACE_HOME}
  if [[ "$#" -eq 1 ]]
  then
    dest=$dest/$1
  fi
  cd $dest
}

function cdsc() {
  cds && cd cpp/lang
  if [[ "$1" ]]; then
    cd $1
  fi
}

################## smart cd to last and next sibling directory##############
function scd(){
  DIRLIST=$1
  : > ${DIRLIST}
  ls -l .. | grep ^d | awk '{print $NF}' >> $DIRLIST
  headdir=$(head -1 ${DIRLIST})
  taildir=$(tail -1 ${DIRLIST})
  cur=$(pwd | awk -F"/" '{print $NF}')
  curidx=`cat ${DIRLIST} | awk  -v cur="$cur" '$0==cur{print NR}'`
  lastdir=${taildir}
  # index for bash array must be integers
  idx=1
  ridx=`cat ${DIRLIST} | wc -l`
  for dir in `cat ${DIRLIST}`; do
    last[$idx]=${lastdir}
    next[$ridx]=${dir}
    lastdir=${dir}
    ridx=${idx}
    idx=$[ ${idx}+1 ]
  done
  if [ $1 == next ];then
    desdir=${next[$curidx]}
  else
    if [ $1 == last ]; then
      desdir=${last[$curidx]}
    fi
  fi
  #local scdnum
  if [ $2 ];then scdnum=$[ $2-1 ]; else scdnum=0; fi
  cd ../${desdir}
  #echo $scdnum
  while [ ${scdnum} -gt 0 ]; do
    scd $1 ${scdnum}
  done
}

################ date time functions ###############################

# show current datetime
function ct(){
  # http://stackoverflow.com/questions/592620/check-if-a-program-exists-from-a-bash-script
  # if command -v gdate >/dev/null 2>&1;
  if hash gdate 2>/dev/null; then
    # datetime string to long
    # "2015-03-19 21:17:08"
    if [[ $1 =~ .*:.* ]]; then
      echo "format: \"yyyy-mm-dd hh:MM:ss\""
      gdate -d "$1" "+%s"
    # long to datetime string
    else
      if [[ $# == 2 ]]; then
        option=$1"d"
        shift
      else
        option="-d"
      fi
        gdate $option @$1 +"%m-%d-%Y %T"
    fi
  else
    date "$@"
  fi
}

############# Dictionary Functions ############################

# lookup a word
function def(){
    # espeak for the pronunciation audible output and phonetic alphabet string
    echo "Phoneme mnemonics: $(espeak -ven-uk-rp -x -s 120 "$1" 2> /dev/null)"
    # dict - the client for the dictionary server
    dict "$1"
}

# add a word to a local dictionay file
worddictionary="$HOME/EnglishNewWords.txt"
function dic(){
  cdate=$(date --iso-8601=date &)
  if ! cat ${worddictionary} | egrep -x ${cdate}.* ;then
    echo -e "$cdate\r" >> "$worddictionary"
  fi
  if dict $1 ;then
  # save the word only when the lookup returns succesfully
    echo -e "$1\r" >> "$worddictionary"
  fi
}
alias word="less $worddictionary"

################ Intelligent string search #########################

# search files for string
# $1 string to be searched: "match_words -v reverse_match_words"
# $2 file format;
# $3 return file name only
function fj() {
  KEY_WORD=$1
  EXT=$2
  RETURN_FILE_PATH_ONLY=$3

  DELIMITER=" -v " # separator for forward and backward search keywords
  match=$(echo ${KEY_WORD} | sed "s/$DELIMITER/*/g" | cut -d* -f1)
  echo "match: ${match} ext: ${EXT}"
  if [[ "${RETURN_FILE_PATH_ONLY}" ]]; then
    if [[ ${KEY_WORD} =~ .*${DELIMITER}.* ]]; then
        reverse=$(echo ${KEY_WORD} | sed "s/$DELIMITER/*/g" | cut -d* -f2)
        echo "reverse match: ${reverse}"
        for f in $(find . -name "*.""$EXT" | xargs grep -i "${match}" | grep -o ".*\.""$EXT" | uniq | sort); do
            if ! [[ $(cat $f | grep -i "${reverse}") ]]; then
                echo $f
            fi
        done
    else
        find . -name "*.""$EXT" | xargs grep -i "${match}" | grep -o ".*\.""$EXT" | uniq | sort
    fi
  else
    if [[ ${KEY_WORD} =~ .*${DELIMITER}.* ]]; then
        reverse=$(echo ${KEY_WORD} | sed "s/$DELIMITER/*/g" | cut -d* -f2)
        echo "reverse: ${reverse}"
        find . -name "*.""$EXT" | xargs grep -i "${match}" | grep -v "${reverse}"
    else
        find . -name "*.""$EXT" | xargs grep -i "${match}"
    fi
  fi
}

# extract information from function names
function extractFormatFromFuncName() {
  # FUNCNAME is a stack, 0 is the most recent one, 1 is the 2nd most recent one, etc.
  fname=${FUNCNAME[ 1 ]}
  len=${#fname}
  if [[ "$len" == 4 ]]; then
    format=${fname: -2: 1}
  else
    format=${fname: -1}
  fi
  case "$format" in
    s) ext="scala";;
    j) ext="java";;
    p) ext="py";;
  esac
  if [[ "$len" == 4 ]]; then
    echo "$ext" true
  else
    echo "$ext"
  fi
}

# search java files
function fgj() {
  fj "$1" $(extractFormatFromFuncName)
}

# search java files return file name only
function fgjf() {
  fj "$1" $(extractFormatFromFuncName)
}

alias fgjfdp="fgjf \"algs.*DynamicProgramming\""

# search python files
function fgp() {
  fj "$1" $(extractFormatFromFuncName)
}

# search python files return file name only
function fgpf() {
  fj "$1" $(extractFormatFromFuncName)
}

# search scala files for string
function fgs() {
  fj "$1" $(extractFormatFromFuncName)
}

# search scala files for string return file name only
function fgsf() {
  fj "$1" $(extractFormatFromFuncName)
}

# search string across all file types
function fg() {
  for x in j p s; do
    fg${x} $1
  done
}

# search string across all file types
function fgf() {
  for x in j p s; do
    fg${x}f $1
  done
}

############################################
# Source outside bash config files
###########################################
if [[ $SHELL == "/usr/local/bin/bash" ]]; then
  plugins='git git_completion' #docker googlecloud guru latex maven spark'
else
  plugins=(git)
fi
CUR_DIR="$( cd "$( dirname "${BASH_SOURCE[0]}" )" && pwd )"
for plugin in ${plugins}; do
  source ${CUR_DIR}/${plugin}.sh
done

############################################
# temporary functions
###########################################

# update urban computing 2015 web jar file
function ucup {
  local ZIP_FILE="UrbComp2016.zip"
  cp $HOME/Dropbox/UrbComp\ 2011/${ZIP_FILE} ~/Desktop/
  unzip -qq -d ~/Desktop/ ~/Desktop/${ZIP_FILE}
  local DEST="$HOME/Desktop/urbcomp2016/"
  mv ~/Desktop/UrbComp2016 "${DEST}"
  # update my affiliation
  sed -i -- 's/Motorola Mobility/Google/g; s/span>University of Illinois at Chicago/span>Google/g' "${DEST}"/pc.html
  scp -r "${DEST}" urbcomp2013@bert.cs.uic.edu:WWW/
  rm -rf "${DEST}"
  rm ~/Desktop/"${ZIP_FILE}"
}


############ automatically u/mount partitions of window7 ###################
sol=/media/sol
system=/media/system
lenovo=/media/lenovo
#allmount
function mountall(){
  if ! [ -d ${sol} ];then sudo mkdir -p ${sol}; fi
  if ! [ -d ${system} ];then sudo mkdir -p ${system}; fi
  if ! [ -d ${lenovo} ];then sudo mkdir -p ${lenovo}; fi
  sudo mount -t ntfs /dev/sda2 ${system}
  sudo mount -t ntfs /dev/sda5 ${sol}
  sudo mount -t ntfs /dev/sda4 ${lenovo}
}

function umountall(){
  sudo umount ${sol} ${system} ${lenovo}
}

################ miscellaneous ##########################
# Run cpp binary defined in src/main/cpp/lang
function rcpp(){
  # run this cmd under the directory where Makefile is defined
  if [[ "$1" ]]; then
    local NAME=$1
  else
    local NAME=$(pwd | awk -F/ '{print $NF}')
  fi
  make $NAME && bin/$NAME
}
