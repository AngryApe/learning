package jdk.concurrency;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

class PriorityTest implements Runnable {

    private static int count = 1;
    private int id = count++;

    private volatile Double d = 0d;

    @Override
    public void run() {
        for (int i = 0; i < 100_0000; i++) {
            d += (Math.PI + Math.E) / i;
            if (i % 100000 == 0) {
                System.out.println("Thread[" + id + "]-" + (i / 100000));
                Thread.yield();
            }
            Thread.currentThread().setPriority(id == 10 ? 10 : 1);
        }
    }
}

/**
 * Runnable 创建线程
 */
class TestYield1 implements Runnable {

    private static int count = 1;
    private int id = count++;

    public TestYield1() {
        System.out.println(" thread[" + id + "] start.");
    }

    @Override
    public void run() {
        //yield 将让出当前的cpu时间片
        System.out.println(" thread[" + id + "]-1");
        Thread.yield();
        System.out.println(" thread[" + id + "]-2");
        Thread.yield();
        System.out.println(" thread[" + id + "]-3");
        Thread.yield();

        System.out.println(" thread[" + id + "] finish.");
    }
}

/**
 * Callable 创建具有返回值的线程
 * 用Future 接收返回值
 */
class TestCallable implements Callable<Integer> {

    private static int count = 1;
    private int id = count++;

    @Override
    public Integer call() throws Exception {
        TimeUnit.SECONDS.sleep(1);
        return id;
    }
}

//线程工厂
class MyThreadFatory implements ThreadFactory {

    private static AtomicInteger count = new AtomicInteger(1);

    @Override
    public Thread newThread(Runnable r) {
        Thread thread = newThread(r);
        thread.setDaemon(true);
        thread.setName("daemon-thread[" + count.getAndAdd(1) + "]");
        return thread;
    }
}

/**
 * Thread 可以被设置为daemon,所有非后台线程停止时，程序即停止（无视daemon）
 * ThreadFactory 可以用来实现定制化的线程
 */

public class MyThread {
    public static void main(String[] args) {
        ////手动启动线程
//        for (int i = 0; i < 10; i++) {
//            new Thread(new TestYield1()).start();
//        }
        ////通过executor 启动线程
        ExecutorService executor = Executors.newCachedThreadPool();
//        List<Future<Integer>> results = new ArrayList<>();
//        for (int i = 0; i < 5; i++) {
//            executor.execute(new TestYield1());
////            results.add(executor.submit(new TestCallable()));
//        }
//        for (Future<Integer> result : results) {
//            try {
//                System.out.println(result.get());
//            } catch (InterruptedException | ExecutionException e) {
//                e.printStackTrace();
//            }
//        }
        for (int i = 0; i < 10; i++) {
            executor.execute(new PriorityTest());
        }
        executor.shutdown();
    }

}



