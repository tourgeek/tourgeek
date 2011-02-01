#!/bin/sh
# Run tour
BINDIR=`dirname $0`
. $BINDIR/setenv.sh
cd $APP_HOME
if [ "$1" = "debug" ] ; then
  DEBUG='-agentlib:jdwp=transport=dt_socket,address=8000,server=y,suspend=n'
else
  DEBUG=''
fi
java $DEBUG -Xms256m -Xmx512m org.jbundle.Main local=Jdbc remote=Jdbc table=Jdbc menu=dev.tourapp.com background=backgrounds/worldmapalpha.gif backgroundcolor=#CCFFCC remotehost=$SERVER codebase=$CODE_BASE dbSuffix=_base programSharedDBName=program_base mainSharedDBName=main_base mainUserDBName=main

