#!/bin/bash

# ANSI
RED='\033[0;31m'
GREEN='\033[0;32m'
BLUE='\033[0;34m'
CYAN='\033[0;36m'
MAGENTA='\033[0;35m'
YELLOW='\033[1;33m'
BOLD='\033[1m'
RESET='\033[0m'

JAR_FILE='target/stardew-valley-1.0.0.jar'

build() {
   echo -e "${BLUE}${BOLD}Compiling ...${RESET}"
       local mvn_cmd

       if [ "$1" = "test" ]; then
           mvn_cmd=(mvn -B clean package -Dstyle.color=always)
       else
           mvn_cmd=(mvn -B clean package -Dstyle.color=always -DskipTests)
       fi

       if ! output=$("${mvn_cmd[@]}" 2>&1); then
           echo -e "${RED}${BOLD}Maven build failed with errors:${RESET}"
           echo ""
           printf "%b\n" "${output}"
           exit 1
       fi

       echo -e "${GREEN}${BOLD}Compiled Successfully!${RESET}"
}

run_app() {
    if [ $# -eq 1 ]; then
      if [[ "$1" == "debug" ]]; then
        mvnDebug exec:java 2>&1
      else
        (cat "$1"; cat -) | mvn exec:java 2>&1
      fi
    else
        mvn exec:java 2>&1
    fi
}

run_app2() {
    if [ $# -eq 1 ]; then
        (cat "$1"; cat -) | java -jar "${JAR_FILE}"
    else
        java -jar "${JAR_FILE}"
    fi
}

if [ $# -eq 0 ]; then
    build
    run_app
elif [ $# -eq 1 ]; then
    if [ "$1" = "--help" ]; then
          echo -e "Usage: ${BOLD}$0 [input file]${RESET}"
          echo -e "Run Java project in the root directory. Optionally pass input from a file."
          exit 0
    fi
    
    build "$1"
    run_app "$1"
fi


