#!/bin/bash

pid=$(lsof -i:8080 | grep java | awk '{print $2}')

if [ -z $pid ]
then
  echo 'website is already done'
else
  echo 'website is not done'
  echo 'try to stop website'
  kill -9 ${pid}
  echo 'website already done'
fi


echo begin package
mvn package
echo finish package

echo begin start
nohup java -jar target/website-0.0.1-SNAPSHOT.jar 2&1>/dev/null
echo success

