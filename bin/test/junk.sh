#!/bin/sh
java_dir=/usr/local/java
JAVA_HOME=$java_dir/jdk/jdk1.3
export JAVA_HOME
CLASSPATH=$java_dir/jdk/j2sdkee1.2.1/lib/j2ee.jar
CLASSPATH=$CLASSPATH:/data/java/tour/WEB-INF/classes
export CLASSPATH
