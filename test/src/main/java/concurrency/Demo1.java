/**
 * Copyright (C), 杭州中恒云能源互联网技术有限公司，保留所有权利
 */
package concurrency;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @auther qiys@hzzh.com
 * @date 2018-04-04
 */
public class Demo1 {

    private static final long count = 10000_0000l;

    private static volatile int a = 0;

    private AtomicInteger aa ;

    public static void main(String[] args) throws InterruptedException {
        //经测试，亿级循环次数，串行都比并行快
        //10亿及以上时，串行比并行慢
        concurrency();
        serial();
    }

    private static void concurrency() throws InterruptedException {
        long start = System.currentTimeMillis();
        int threadNum = 1;
        for (int n = 0; n < threadNum; n++) {
            Thread thread = new Thread(() -> {
                long loop = count / threadNum;
                for (long i = 0; i < loop; i++) {
                    a += 5;
                }
            });
            thread.start();
            thread.join();
        }

        long b = 0;
        for (long i = 0; i < count; i++) {
            b--;
        }
        long time = System.currentTimeMillis() - start;
        System.out.println("concurrency :" + time + "ms,b=" + b);
    }

    private static void serial() {
        long start = System.currentTimeMillis();
        long a = 0;
        for (long i = 0; i < count; i++) {
            a += 5;
        }
        long b = 0;
        for (long i = 0; i < count; i++) {
            b--;
        }
        long time = System.currentTimeMillis() - start;
        System.out.println("serial:" + time + "ms,b=" + b + ",a=" + a);
    }

}
