package jdk.concurrency;

import jdk.concurrency.sparkle.CoderGenerator;
import jdk.concurrency.sparkle.SparkleWrapper;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class MyLock {

    public static void main(String[] args) {
        SparkleWrapper sparkleWrapper = new SparkleWrapper();
        ExecutorService executor = Executors.newCachedThreadPool();
        try {
            for (int i = 0; i < 10; i++) {
//                executor.execute(() -> {
                    for (int j = 0; j < 10; j++) {
                                            System.out.println(CoderGenerator.getMetricCode('1'));
//                                                System.out.println(sparkleWrapper.getSequence());
//                        System.out
//                                .println(SparkleWrapper._getSequence2());
                    }
                    Thread.sleep(1000);
//                });
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            executor.shutdown();
        }

    }
}

class SyncTest {

    private static volatile Integer seq = 0;//普通方法有重复；synchronized有重复；lock
    //    private static Integer seq = 0;//普通方法有重复；synchronized有重复；lock 无重复；

    private static Lock lock = new ReentrantLock();

    public static Integer getSequence() {
        synchronized (seq) {
            return seq++;
        }
    }

    public static Integer getSeq() {
        lock.lock();
        try {
            return seq++;
        } finally {
            lock.unlock();
        }
    }
}