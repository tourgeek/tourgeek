#!/bin/sh
# Run tour
. /data/java/bin/setenv.sh
cd /data/java/tour
java -Djava.rmi.server.codebase=http://$SERVER:8040/classes/ -Djava.security.policy=/data/java/tour/bin/policy/policy.all com.tourapp.Server provider=$SERVER remoteapp=msgapp

