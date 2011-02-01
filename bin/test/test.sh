#!/bin/sh
# Run tour
BINDIR=`dirname $0`
. $BINDIR/setenv.sh
CLASSPATH=$CLASSPATH:/usr/local/java/util/misc/junit3.5/junit.jar
export CLASSPATH
cd /data/java/tour
java com.tourapp.Main screen=.test.test.screen.TestGridScreen local=Net remote=Net table=Net background=backgrounds/worldmapalpha.gif backgroundcolor=#FFFFCC

