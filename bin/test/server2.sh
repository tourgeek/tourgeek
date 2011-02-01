#!/bin/sh
# Run tour
. /data/java/bin/setenv.sh
cd /data/java/tour
java -Djava.rmi.server.codebase=http://$SERVER/classes/ -Djava.security.policy=/data/java/tour/bin/policy/policy.all com.tourapp.Server provider=$SERVER:8002 remoteapp=msgapp

