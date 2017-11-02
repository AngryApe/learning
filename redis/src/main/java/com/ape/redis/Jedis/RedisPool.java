package com.ape.redis.Jedis;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**
 * AngryApe created at 2017-11-02
 */
public class RedisPool {

    private static JedisPool pool;

    public RedisPool() {
        init();
    }

    private void init() {
        JedisPoolConfig config = new JedisPoolConfig();
        config.setMinIdle(10);
        config.setMaxIdle(100);
        config.setMaxTotal(1000);
        config.setMaxWaitMillis(1500);
        config.setTestOnBorrow(true);
        config.setTestOnReturn(true);
        pool = new JedisPool(config, "192.168.10.250", 6379, 10000, "ape", 0);
    }

    private static Jedis getJedis() {
        synchronized (pool) {
            return pool.getResource();
        }
    }

    public String get(String key) {
        Jedis jedis = null;
        try {
            jedis = getJedis();
            return jedis.get(key);
        } catch (Exception e) {
            System.out.println(key + " ERROR");
            e.printStackTrace();
        } finally {
            if (jedis != null)
                jedis.close();
        }
        return null;
    }

    public void set(String key, String value) {
        Jedis jedis = null;
        try {
            jedis = getJedis();
            jedis.set(key, value);
        } catch (Exception e) {
            System.out.println(key + " ERROR");
            e.printStackTrace();
        } finally {
            if (jedis != null)
                jedis.close();
        }
    }
}
