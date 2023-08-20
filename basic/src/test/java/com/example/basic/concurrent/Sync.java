package com.example.basic.concurrent;

import java.util.*;

/**
 * @author ryan
 * @date 2023/7/19 19:42
 */
public class Sync {
    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (Sync.class) {
                    System.out.println("a" + System.currentTimeMillis());
                    while (true) {
                        if (Thread.interrupted()) {
                            System.out.println("b" + System.currentTimeMillis());
                            break;
                        }
                    }
                }
            }
        });
        Thread thread1 = new Thread(() -> {
            synchronized (Sync.class) {
                System.out.println("d" + System.currentTimeMillis());
            }
        });
        thread.start();
        Thread.sleep(10);
        thread1.start();
        Thread.sleep(1000);
        thread.interrupt();

    }

    public static int[] method(Work[] work, int[] level) {
        Arrays.sort(work, (o1, o2) -> o1.hard != o2.hard ? o1.hard - o2.hard : o2.money - o1.money);
        TreeMap<Integer, Integer> treeMap = new TreeMap<>();
        treeMap.put(work[0].hard, work[0].money);
        Work pre = work[0];
        for (int i = 1; i < work.length; i++) {
            if (work[i].hard != pre.hard && work[i].money > pre.money) {
                treeMap.put(work[i].hard, work[i].money);
                pre = work[i];
            }
        }
        int[] res = new int[level.length];
        for (int i = 0; i < level.length; i++) {
            Integer hard = treeMap.floorKey(level[i]);
            Map.Entry<Integer, Integer> integerIntegerEntry = treeMap.floorEntry(level[i]);
            if (integerIntegerEntry != null) {
                System.out.print(integerIntegerEntry.getKey());
                System.out.print("--->");
                System.out.print(integerIntegerEntry.getValue());
                System.out.println();
            }
            res[i] = hard == null ? 0 : treeMap.get(hard);
        }
        return res;
    }
}

class Work {
    int hard;
    int money;

    public Work(int hard, int money) {
        this.hard = hard;
        this.money = money;
    }
}
