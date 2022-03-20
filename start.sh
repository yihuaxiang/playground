#!/bin/bash

pid=$(lsof -i:8080 | grep java | awk '{print $2}')

if [ -z $pid ]
then
  echo -z
else
  echo not -z
  echo
fi
#kill -o ${pid}

