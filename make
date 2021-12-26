#!/bin/bash
set -e
java -version

# finding script folder
dir=`dirname $0`
absdir=`cd $dir; pwd`
cd $absdir

skip='-Dmaven.test.skip -DskipITs'


show_help(){
    echo -e "Usage: ./make build|git|check|deploy|test"
    exit
}

if [ "$1" = "build-prod" ]; then
  mvn clean install  $skip 
fi

if [ "$1" = "build-stage" ]; then
  mvn clean install  $skip 
fi

if [ "$1" = "build-dev" ]; then
  mvn clean install  $skip 
fi

if [ "$1" = "test-it" ]; then
  mvn clean test  $skip 
fi

if [ "$1" = "build-site" ]; then
  mvn site  $skip 
fi

if [ "$1" = "deploy-github" ]; then
  mvn clean
  mvn -f bom deploy
  mvn -f lib  deploy
fi


