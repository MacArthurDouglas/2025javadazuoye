#!/bin/bash

SEED=484

if [ ! -d "bin" ]; then
    mkdir bin
fi

# 复制 Game.jar 到 bin 目录
cp target/Game.jar bin/Game.jar
if [ $? -ne 0 ]; then
    echo "复制 Game.jar 失败，请检查权限或路径！"
    exit 1
fi

# 运行
cd bin

java -jar Game.jar $SEED