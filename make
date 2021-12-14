#!/bin/bash
set -e
java -version

# finding script folder
dir=`dirname $0`
absdir=`cd $dir; pwd`
cd $absdir

if [ "$1" = "build-prod" ]; then
  mvn clean install  -T 1C -Dmaven.test.skip -Dorg.slf4j.simpleLogger.defaultLogLevel=info -DargLine="-Xms1024m -Xmx8192m"
fi

if [ "$1" = "build-stage" ]; then
  mvn clean install  -T 1C -Dmaven.test.skip -Dorg.slf4j.simpleLogger.defaultLogLevel=info -DargLine="-Xms1024m -Xmx8192m"
fi

if [ "$1" = "build-dev" ]; then
  mvn clean install  -T 1C -Dmaven.test.skip -Dorg.slf4j.simpleLogger.defaultLogLevel=info -DargLine="-Xms1024m -Xmx8192m"
fi

if [ "$1" = "test-dev" ]; then
  mvn clean test  -T 1C -Dorg.slf4j.simpleLogger.defaultLogLevel=warn -DargLine="-Xms1024m -Xmx8192m"
fi

if [ "$1" = "site" ]; then
  mvn site  -DskipTests -Dorg.slf4j.simpleLogger.defaultLogLevel=info -DargLine="-Xms1024m -Xmx8192m"
fi

if [ "$1" = "deploy" ]; then
  mvn clean
  mvn -f bom deploy
  mvn -f lib  deploy
fi


