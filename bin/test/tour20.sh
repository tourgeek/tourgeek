#!/bin/sh
# Run tour
BINDIR=`dirname $0`
. /data/java/bin/setenv.sh
cd /data/java/tour
# java com.tourapp.Main remoteapp=appserver2 menu=MainDon background=backgrounds/worldmapalpha.gif backgroundcolor=#FFCCFF"
java com.tourapp.Main remotehost=www.tourdemo.com:8001 codebase=www.tourdemo.com:8041 menu=MainDon background=backgrounds/worldmapalpha.gif backgroundcolor=#FFCCFF

