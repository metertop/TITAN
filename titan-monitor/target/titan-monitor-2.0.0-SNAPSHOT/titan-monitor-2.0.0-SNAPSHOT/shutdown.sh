#!/bin/bash

source /etc/profile

#��ȡ����monitor��PID
pid=`ps -ef | grep "titan-monitor" | grep -v "grep" | awk '{print $2}'`
echo $pid

#kill����������monitor�ڵ�
kill -9 $pid
echo "titan-monitor�����Ѿ�ȫ��kill"

