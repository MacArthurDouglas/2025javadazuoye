import java.util.Random;

public class Main {
    public static void main(String[] args) {
        if (args.length == 0) {
            System.out.println("请从脚本运行程序并传入 seed（你的学号后三位）");
            System.out.println("示例： java -jar Game.jar 999");
            return;
        }

        long seed = Long.parseLong(args[0]);
        System.out.println("程序启动，使用 seed = " + seed);

        Random rand = new Random(seed);
        Game game = new Game(rand, seed); // 传入 seed 以便记录到 seed.log
        game.start();
    }
}
