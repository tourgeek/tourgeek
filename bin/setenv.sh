#!/bin/sh
JAVA_DIR=/usr/local/java
# export JAVA_HOME=$JAVA_DIR/jdk

export USER_DIR=/home/don

export VERSION=0.7.6-SNAPSHOT
export CODE_HOME=$USER_DIR/workspace/jbundle-workspace
export TOURAPP_HOME=$CODE_HOME/tourapp-root
export APP_HOME=$CODE_HOME/tourapp-root/tourapp-config/tourapp-config-web/tourapp-config-web-webapp/target/tourapp-config-web-webapp-$VERSION

# Usually you want to set this to $HOSTNAME! NOTE: IT IS VERY IMPORTANT THAT $HOSTNAME is the host and is the host's actual IP
export SERVER=$HOSTNAME
# Remember the trailing / on code_base
export CODE_BASE=$SERVER:8080/
export J2EE_HOME=$JAVA_DIR/web/glassfish
export POLICY_ALL=$TOURAPP_HOME/bin/policy/policy.all

CLASSPATH=
CLASSPATH=$CLASSPATH:$TOURAPP_HOME/tourapp-config/tourapp-config-web/tourapp-config-web-webapp/target/tourapp-config-web-webapp-$VERSION/WEB-INF/lib/*
CLASSPATH=$CLASSPATH:$TOURAPP_HOME/tourapp-config/tourapp-config-web/tourapp-config-web-webapp/target/tourapp-config-web-webapp-$VERSION/lib/*
CLASSPATH=$CLASSPATH:$J2EE_HOME/glassfish/lib/javaee.jar
CLASSPATH=$CLASSPATH:$TOURAPP_HOME/tourapp-config/tourapp-config-web/tourapp-config-web-webapp/target/tourapp-config-web-webapp-$VERSION/
CLASSPATH=$CLASSPATH:/usr/local/java/util/jaxe/lib/*
export CLASSPATH

