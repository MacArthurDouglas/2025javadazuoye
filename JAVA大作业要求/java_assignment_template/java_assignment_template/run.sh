#!/bin/bash

SEED=999

if [ ! -d "bin" ]; then
    mkdir bin
fi

javac -encoding UTF-8 -source 8 -target 8 -d bin src/*.java
if [ $? -ne 0 ]; then
    echo "Compilation failed, please check your code!"
    exit 1
fi

pushd bin > /dev/null
jar cfe ../Game.jar Main *.class
popd > /dev/null

java -jar Game.jar $SEED