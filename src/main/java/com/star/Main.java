package com.star;

import java.util.Random;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        if (args.length == 0) {
            System.out.println("请从脚本运行程序并传入 seed（你的学号后三位）");
            System.out.println("示例： java -jar Game.jar 999");
            return;
        }

        long seed = Long.parseLong(args[0]);
        System.out.println("程序启动，使用 seed = " + seed);

        //输入棋盘大小
        Scanner scanner = new Scanner(System.in);
        int length, width;
        while (true) {
            System.out.print("输入棋盘大小（长 宽），例如：5 4：");
            if (scanner.hasNextInt()) {
                length = scanner.nextInt();
            } else {
                System.out.println("请输入整数！");
                scanner.nextLine(); // 清除非整数输入
                continue;
            }

            if (scanner.hasNextInt()) {
                width = scanner.nextInt();
            } else {
                System.out.println("请输入整数！");
                scanner.nextLine(); // 清除非整数输入
                continue;
            }

            if (length < 3 || width < 3) {
                System.out.println("棋盘的长和宽都不能小于3，请重新输入。");
                continue;
            }
            break;
        }

        Random rand = new Random(seed);
        Game game = new Game(rand, seed,length,width); // 传入 seed 以便记录到 seed.log
        game.start();
    }
}
