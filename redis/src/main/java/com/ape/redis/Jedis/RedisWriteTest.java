package com.ape.redis.Jedis;

import com.ape.utils.CommonUtils;
import redis.clients.jedis.Jedis;

/**
 * AngryApe created at 2017-11-02
 */
public class RedisWriteTest {

    private static int INVOKE_TIMES = 100000;

    public static void main(String[] args) {
        //        testConnect();
        RedisWriteTest test = new RedisWriteTest();
        test.testWrite();
    }

    private void testWrite() {
        RedisPool pool = new RedisPool();
        Long start = System.currentTimeMillis();
        int i = 0;
        try {
            for (; i < 50; i++) {
                new Thread(new RedisConsumer(pool, String.valueOf(i + 1), '1')).start();
                new Thread(new RedisConsumer(pool, String.valueOf(i + 1), '0')).start();
            }
        } catch (Exception e) {
            System.out.println("Got an Exception at [" + i + "]:" + e.getMessage());
            e.printStackTrace();
        }

        CommonUtils.methodCost(start, "Write to Redis");
    }

    class RedisConsumer implements Runnable {

        private RedisPool pool;
        private String threadName;
        private char IO = '1';

        public RedisConsumer(RedisPool pool, String name, char io) {
            this.pool = pool;
            this.IO = io;
            this.threadName = "Thread[RedisConsumer::" + (IO == '1' ? "GET" : "SET") + "]" + name;
        }

        @Override
        public void run() {
            Long start = System.currentTimeMillis();
            for (int i = 0; i < 10000; i++) {
                if (IO == '1')
                    pool.get(String.valueOf(i % 1000));
                else
                    pool.set(String.valueOf(i % 1000), String.valueOf(i));
            }
            CommonUtils.methodCost(start, threadName);
        }
    }

    private static void testConnect() {
        Jedis jedis = new Jedis("192.168.10.250", 6379);
        jedis.auth("ape");
        jedis.set("2", "1");
        Long start = System.currentTimeMillis();
        int i = 0;
        for (; i < INVOKE_TIMES; i++) {
            jedis.set(String.valueOf(i % 1000), String.valueOf(i));
        }
        CommonUtils.methodCost(start, "Write to Redis single connection");
        System.out.println("Redis server is usable.");
        jedis.close();
    }

}
