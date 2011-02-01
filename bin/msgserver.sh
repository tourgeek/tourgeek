#!/bin/sh
# Run tour
BINDIR=`dirname $0`
. $BINDIR/setenv.sh
cd $APP_HOME
if [ "$1" = "debug" ] ; then
DEBUG='-agentlib:jdwp=transport=dt_socket,address=8003,server=y,suspend=n'
else
DEBUG=''
fi
java $DEBUG -Djava.rmi.server.codebase=http://$SERVER/classes/ -Djava.security.policy=$CODE_HOME/tourapp/bin/policy/policy.all org.jbundle.server.Server providerapp=msgapp

