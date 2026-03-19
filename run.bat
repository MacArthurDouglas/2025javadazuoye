@echo off
setlocal enabledelayedexpansion

set SEED=484

if not exist bin mkdir bin

copy /Y target\Game.jar bin\

cd bin

java -jar Game.jar %SEED%
pause
