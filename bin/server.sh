#!/bin/sh
# Run tour
BINDIR=`dirname $0`
. $BINDIR/setenv.sh
cd $APP_HOME
# Note: This is configured as a (jms) message server. Make JMS server dedicated in production.
if [ "$1" = "debug" ] ; then
DEBUG='-agentlib:jdwp=transport=dt_socket,address=8001,server=y,suspend=n'
else
DEBUG=''
fi
java $DEBUG -Xms256m -Xmx512m -Djava.rmi.server.codebase=http://$SERVER/classes/ -Djava.security.policy=$POLICY_ALL org.jbundle.server.Server jmsserver=true remoteapp=msgapp provider=$SERVER
