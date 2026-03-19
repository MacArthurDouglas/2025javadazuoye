package com.star;

import com.star.constants.Constant;
import com.star.enums.Direction;
import com.star.utils.LogUtils;

import java.util.Objects;
import java.util.Random;
import java.util.Scanner;
import java.io.PrintWriter;
import java.io.FileWriter;
import java.time.LocalDateTime;

public class Game {

    private Random rand;
    private Monster[] monsters;

    private PrintWriter log;

    private Player player;
    public static int length;
    public static int width;


    public static BaseRole [][] chessboard;



    public Game(Random rand, long seed, int length, int width) {
        this.player = new Player(rand);
        this.length = length;
        this.width = width;
        this.chessboard = new BaseRole[length][width];
        this.rand = rand;
        // 打开 seed.log 并写入开头信息
        try {
            log=LogUtils.getInstance().log;

            log.println("seed = " + seed);
            log.println("棋盘大小 = " + length +" x "+ width);
            log.println("start at: " + LocalDateTime.now());
            log.flush();
        } catch (Exception e) {
            System.err.println("无法写入 seed.log: " + e.getMessage());
            log = null;
        }

    }

    public void start() {
        initMonsters();
        printMonsters();
        putPlayerAndMonsters();
        Scanner scanner = new Scanner(System.in);
        int turn = 1;
        while (true) {
            printChessboard();

            println("Turn " + turn);

            println("你的血量: " + player.getPlayerHp());
            for (Monster m : monsters){
                if (!m.isDead()){
                    println(m.alias+"的血量："+m.getHp());

                }
            }
            String input;

            while(true){
                System.out.print("输入方向 (up/left/down/right): ");
                input=scanner.nextLine().trim();
                boolean b = handleInput(input);
                if (b) break;
            }

            logLine("Player move: " + input);

            for (Monster m : monsters) {
                if (!m.isDead()) {
                    m.randomMove();
                }
            }

            if (player.getPlayerHp() <= 0) {
                println("YOU HAVE DIED!");
                logLine("Result: YOU HAVE DIED!");
                break;
            }

            boolean allDead = true;
            for (Monster m : monsters) {
                if (!m.isDead()) allDead = false;
            }

            if (allDead) {
                println("YOU HAVE WON!");
                logLine("Result: YOU HAVE WON!");
                break;
            }
            turn++;
        }
        if (log != null) {
            log.println("end at: " + LocalDateTime.now());
            log.close();
        }
        scanner.close();
    }

    private void printChessboard() {
        for (int j = 0; j < width; j++) {
            for (int i = 0; i < length; i++) {
                if (chessboard[i][j]==null){
                    LogUtils.logPrint(Constant.AIR_CHAR);
                }
                else if (Objects.equals(chessboard[i][j].type, "Player")) {

                    LogUtils.logPrint(Constant.PLAYER_CHAR);
                }
                else if (Objects.equals(chessboard[i][j].type, "Monster")) {
                    Monster m = (Monster) chessboard[i][j];
                    if (m.isDead()){
                        LogUtils.logPrint(Constant.DEAD_MONSTER_CHAR);
                    }
                    else{
                        LogUtils.logPrint(Constant.MONSTER_CHAR);
                    }
                }
            }
            LogUtils.logPrintln("");
        }
    }

    private void putPlayerAndMonsters() {
        player.setLocation(length -1, width -1);
        monsters[0].setLocation(length-1,0);
        monsters[1].setLocation(0,width-1);
        monsters[2].setLocation(0,0);



        chessboard[player.getX()][player.getY()] = player;

        for (Monster m : monsters){
            chessboard[m.getX()][m.getY()] = m;

        }



    }

    private void initMonsters() {
        monsters = new Monster[]{
                new Monster(1,"MonsterA", rand),
                new Monster(2,"MonsterB", rand),
                new Monster(3,"MonsterC", rand)
        };
    }

    private void printMonsters() {
        println("根据 seed 生成的怪物属性：");
        for (Monster m : monsters) {
            println(m.toString());
        }
    }


    /**
     * 是否是合法输入
     * @param input
     * @return
     */
    private boolean handleInput(String input) {
        switch (input) {
            case "up": player.move(Direction.UP); break;
            case "down": player.move(Direction.DOWN); break;
            case "left": player.move(Direction.LEFT); break;
            case "right": player.move(Direction.RIGHT); break;
            default:
                println("Use only keywords up,down,left,right.");
                return false;
        }
        return true;
    }



    private void println(String s) {
        System.out.println(s);
        logLine(s);
    }

    public void logLine(String s) {
        if (log != null) {
            log.println(s);
            log.flush();
        }
    }
}
