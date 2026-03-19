@echo off
setlocal enabledelayedexpansion

set SEED=999

if not exist bin mkdir bin

javac -encoding UTF-8 -source 8 -target 8 -d bin src\*.java
if %errorlevel% neq 0 (
    echo Compilation failed, please check your code!
    pause
    exit /b
)

pushd bin
jar cfe ../Game.jar Main *.class
popd

java -jar Game.jar %SEED%
pause
