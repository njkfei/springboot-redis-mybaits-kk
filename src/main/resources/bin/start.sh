#!/bin/sh

curdir=`dirname $0`
cd $curdir
curdir=`pwd`

APP_NAME=kk-1.0.jar
MAIN_CLASS=org.springframework.boot.loader.JarLauncher
APP_HOME=..

export APP_HOME

LIB_PATH=$APP_HOME/lib
export LIB_PATH

#for i in $LIB_PATH/*
#	do CLASSPATH=$i:$CLASSPATH
#done
CLASSPATH=$LIB_PATH/$APP_NAME
#export CLASSPATH

CLASSPATH=$CLASSPATH:$APP_HOME/conf:$APP_HOME/bin:$APP_HOME

export CLASSPATH
cd $APP_HOME/bin

nohup java -classpath $CLASSPATH $MAIN_CLASS &
