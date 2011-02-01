#!/bin/sh
# Run tour
BINDIR=`dirname $0`
. /data/java/bin/setenv.sh
CLASSPATH=$CLASSPATH:/usr/local/java/util/dev/junit/junit.jar
CLASSPATH=$CLASSPATH:/usr/local/java/util/dev/log4j/dist/lib/log4j.jar
export CLASSPATH
cd /data/java/tour
java com.tourapp.Main menu=MainDon local=Jdbc remote=Jdbc table=Jdbc background=backgrounds/worldmapalpha.gif backgroundcolor=#FFFFCC

