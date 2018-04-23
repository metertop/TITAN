#!/bin/bash

source /etc/profile

/usr/local/jdk1.8.0_121/bin/java -server -Xms2048m -Xmx2048m -Xmn768m -XX:PermSize=128m -XX:MaxPermSize=128m -XX:+UseParallelOldGC -XX:ParallelGCThreads=3 -XX:+HeapDumpOnOutOfMemoryError -XX:+PrintGCDetails -XX:+PrintGCDateStamps -Xloggc:/usr/local/yunji/titan-datacollect/logs/gc.log -jar titan-datacollect*.jar > /usr/local/yunji/titan-datacollect/logs/log  2>&1 &
