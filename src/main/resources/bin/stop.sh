APP_NAME=kk-1.0.jar


PID=`ps aux | grep $APP_NAME | grep -v "grep" | awk '{print $2}'`

kill -9 $PID
