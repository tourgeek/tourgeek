#!/bin/sh
# Run tour
BINDIR=`dirname $0`
. $BINDIR/../setenv.sh
cd $APP_HOME
if [ "$1" = "debug" ] ; then
  DEBUG='-agentlib:jdwp=transport=dt_socket,address=8000,server=y,suspend=n'
else
  DEBUG=''
fi
cd build/webapp
java $DEBUG -Xms256m -Xmx512m org.jbundle.Main url=http://fred.tourgeek.com/tourapp baseURL=fred.tourgeek.com/ domain=fred.tourgeek.com background=backgrounds/worldmapalpha.gif backgroundcolor=#FFEEEE remotehost=$SERVER codebase=$CODE_BASE

