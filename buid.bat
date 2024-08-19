@echo off
title Build run...

echo "Start..."

REM init
set container-tag="interview/interview-app"
set build=%1
REM init

echo %build%

docker stop %container-tag%
if %build% == 1 (
    echo "Build..."
    .\gradlew build -x test && docker build -t %container-tag% .
    docker run -p 8080:8080 %container-tag%
)

docker run -p 8080:8080 %container-tag%