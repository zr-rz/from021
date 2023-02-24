package com.example.algorithm.thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class PrintABC {
    public static void main(String[] args) {
        PrintAbcUtil printAbcUtil = new PrintAbcUtil();
        ExecutorService executorService = Executors.newFixedThreadPool(3);
        executorService.execute(() -> printAbcUtil.print(0, "a"));
        executorService.execute(() -> printAbcUtil.print(1, "b"));
        executorService.execute(() -> printAbcUtil.print(2, "c"));

    }
}
