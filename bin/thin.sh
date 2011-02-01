#!/bin/sh
# Run tour
BINDIR=`dirname $0`
. $BINDIR/setenv.sh
cd $APP_HOME
if [ "$1" = "debug" ] ; then
DEBUG='-agentlib:jdwp=transport=dt_socket,address=8002,server=y,suspend=n'
else
DEBUG=''
fi
# java $DEBUG org.jbundle.thin.Thin dbSuffix=_test mainUserDBName=main programSharedDBName=program_test menu=Thin background=backgrounds/worldmapalpha.gif backgroundcolor=#EEEEFF remotehost=$SERVER codebase=$CODE_BASE
java $DEBUG org.jbundle.thin.Thin menu=Thin background=backgrounds/worldmapalpha.gif backgroundcolor=#EEEEFF remotehost=$SERVER codebase=$CODE_BASE

