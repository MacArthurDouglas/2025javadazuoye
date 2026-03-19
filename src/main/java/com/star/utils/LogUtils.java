package com.star.utils;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class LogUtils {
    private static LogUtils Instance;

    public static LogUtils getInstance() {
        if (Instance == null) {
            try {
                Instance = new LogUtils();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return Instance;
    }
    public PrintWriter log;
    public LogUtils() throws IOException {
        log = new PrintWriter(new FileWriter("seed.log", false)); // 覆盖写
    }

    /**
     * 写入日志并打印
     * @param msg
     */
    public static void logPrintln(String msg) {
        System.out.println(msg);
        if (Instance.log != null) {
            Instance.log.println(msg);
            Instance.log.flush();
        }
    }

    public static void logPrint(String msg) {
        System.out.print(msg);
        if (Instance.log != null) {
            Instance.log.print(msg);
            Instance.log.flush();
        }
    }
    public static void logPrint(char ch) {
        logPrint(""+ch);
    }

}
