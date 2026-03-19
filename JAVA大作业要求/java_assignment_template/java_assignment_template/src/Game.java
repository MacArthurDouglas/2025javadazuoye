import java.util.Random;
import java.util.Scanner;
import java.io.PrintWriter;
import java.io.FileWriter;
import java.time.LocalDateTime;

public class Game {

    private Random rand;
    private Monster[] monsters;
    private int playerHp = 100;
    private PrintWriter log;

    public Game(Random rand, long seed) {
        this.rand = rand;
        // 打开 seed.log 并写入开头信息
        try {
            log = new PrintWriter(new FileWriter("seed.log", false)); // 覆盖写
            log.println("seed = " + seed);
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

        Scanner scanner = new Scanner(System.in);
        int turn = 1;
        while (true) {
            println("Turn " + turn);
            println("你的血量: " + playerHp);
            System.out.print("输入方向 (w/a/s/d): ");

            String input = scanner.nextLine().trim();
            handleInput(input);
            logLine("Player move: " + input);

            for (Monster m : monsters) {
                if (!m.isDead() && rand.nextInt(5) == 0) {
                    fight(m);
                }
            }

            if (playerHp <= 0) {
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

    private void initMonsters() {
        monsters = new Monster[]{
                new Monster("MonsterA", rand),
                new Monster("MonsterB", rand),
                new Monster("MonsterC", rand)
        };
    }

    private void printMonsters() {
        println("根据 seed 生成的怪物属性：");
        for (Monster m : monsters) {
            println(m.toString());
        }
    }

    private void handleInput(String input) {
        switch (input) {
            case "w": println("你向上移动。"); break;
            case "s": println("你向下移动。"); break;
            case "a": println("你向左移动。"); break;
            case "d": println("你向右移动。"); break;
            default:
                println("无效指令");
                return;
        }
    }

    private void fight(Monster m) {
        println("\n遭遇战斗！" + m.getName());

        int dmgToPlayer = Math.max(0, m.getAttack() - rand.nextInt(6));
        int dmgToMonster = Math.max(0, 15 - m.getDefense());

        playerHp -= dmgToPlayer;
        m.takeDamage(dmgToMonster);

        println("你受到伤害：" + dmgToPlayer);
        println("你造成伤害：" + dmgToMonster);

        logLine("Fight: " + m.getName() + " dmgToPlayer=" + dmgToPlayer + " dmgToMonster=" + dmgToMonster);
        if (m.isDead()) {
            println(m.getName() + " 被击败！");
            logLine(m.getName() + " 被击败");
        }
    }

    private void println(String s) {
        System.out.println(s);
        logLine(s);
    }

    private void logLine(String s) {
        if (log != null) {
            log.println(s);
            log.flush();
        }
    }
}
