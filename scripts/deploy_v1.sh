#!/bin/bash

REPOSITORY=/home/ubuntu/app/step1
PROJECT_NAME=blitz

cd $REPOSITORY/$PROJECT_NAME/

echo "> Git pull"

git pull

echo "> ./gradlew 권한 추가"

chmod +x ./gradlew

echo "> 프로젝트 Build 시작"

./gradlew build

echo "> step1 디렉토리로 이동"

cd $REPOSITORY

echo "> Build 파일 복사"

cp $REPOSITORY/$PROJECT_NAME/build/libs/*.war $REPOSITORY/

echo "> 현재 구동중인 PID 확인"

CURRENT_PID=$(pgrep -f ${PROJECT_NAME}.*.war)

echo "> 현재구동중인 애플리케이션 PID : $CURRENT_PID"

if [ -z "$CURRENT_PID" ]; then
  echo "현재 구동중인 애플리케이션이 없으므로 종료하지 않습니다."
else
  echo "> kill -15 $CURRENT_PID"
  kill -15 $CURRENT_PID
  sleep 5
fi

echo "> 새 애플리케이션 배포"

JAR_NAME=$(ls -tr $REPOSITORY/ | grep war | tail -n 1)

echo "> JAR name : $JAR_NAME"

nohup java -jar \
  -Dspring.config.location=classpath:/application.properties,classpath:/application-real.properties,/home/ubuntu/app/application-oauth.properties,/home/ubuntu/app/application-real-db.properties \
  -Dspring.profiles.active=real \
  $JAR_NAME > $REPOSITORY/nohup.out 2>&1 &
