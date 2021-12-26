#!/bin/bash
set -e
java -version

# finding script folder
dir=`dirname $0`
absdir=`cd $dir; pwd`
cd $absdir

skip='-Dmaven.test.skip -DskipITs'


show_help(){
    echo -e "Usage: ./make build|deploy|test"
    exit
}

if [ ! -n "$1" ] ;then
    show_help
else
    case "$1" in
        "build-dev")
            mvn clean install $skip
            ;;
        "build-stage")
            mvn clean install $skip
            ;;
        "build-prod")
              mvn clean install -$skip 
            ;;
        "build-site")
            mvn site  $skip
            ;;
        "test-it")
            mvn clean test 
            ;;
        "deploy-github")
            mvn clean
            mvn -f bom deploy
            mvn -f lib  deploy
            ;;                          
        *)
            echo 'Invalid command!'
            show_help
            ;;
    esac
fi



