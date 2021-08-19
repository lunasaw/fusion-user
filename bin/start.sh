#!/bin/sh

PID=$(ps -ef | grep "fusion-user-server" | grep -v grep | awk '{print $2}')
if [[ "" != "$PID" ]]; then
  echo "killing $PID"
  kill -9 $PID

  sleep 3
fi

java -Dspring.config.location=application.properties -jar fusion-user-server-1.0-SNAPSHOT.jar >> logs/console.log &
