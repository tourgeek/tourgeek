#!/bin/sh
# Run tour
BINDIR=`dirname $0`
. /home/don/workspace/bin/setenv.sh
cd $APP_HOME
if [ "$1" = "debug" ] ; then
  DEBUG='-agentlib:jdwp=transport=dt_socket,address=8000,server=y,suspend=n'
else
  DEBUG=''
fi
/usr/local/java/jdk/jre/bin/javaws -J$DEBUG -Xms256m -Xmx512m http://fred.tourgeek.com/docs/jnlp/all.jnlp