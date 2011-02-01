#!/bin/sh
# Run tour
BINDIR=`dirname $0`
. bin/setenv.sh
cd $APP_HOME
if [ "$1" = "debug" ] ; then
  DEBUG='-agentlib:jdwp=transport=dt_socket,address=8000,server=y,suspend=y'
else
  DEBUG=''
fi
export SERVER=donandann.com
export CODE_BASE=donandann.com
java $DEBUG -Xms256m -Xmx512m com.tourapp.Main local=Jdbc remote=Jdbc table=Jdbc menu=dev.tourapp.com background=backgrounds/worldmapalpha.gif backgroundcolor=#FFFFCC remotehost=$SERVER codebase=$CODE_BASE dbserver=$SERVER
