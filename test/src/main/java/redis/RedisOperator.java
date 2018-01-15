package redis;

import java.util.List;
import java.util.Map;
import java.util.Set;

public class RedisOperator extends BaseOperator {

    public RedisOperator(boolean dev) {
        super(dev);
    }

    public String get(String key) {
        return jedis.get(key);
    }

    public void set(String key, String value) {
        jedis.set(key, value);
    }

    public void mset(String... keysvalues) {
        jedis.mset(keysvalues);
    }

    public String hget(String key, String field) {
        return jedis.hget(key, field);
    }

    public Map<String, String> hgetAll(String key) {
        return jedis.hgetAll(key);
    }

    public List<String> hmget(String key, String[] fields) {
        return jedis.hmget(key, fields);
    }

    public void hset(String key, String field, String value) {
        jedis.hset(key, field, value);
    }

    public Long hdel(String key, String... field) {
        return jedis.hdel(key, field);
    }

    public void hincrByFloat(String key, String field, Double value) {
        jedis.hincrByFloat(key, field, value);
    }

    public void sadd(String key, String... member) {
        jedis.sadd(key, member);
    }

    public boolean sismember(String key, String member) {
        return jedis.sismember(key, member);
    }

    public Set<String> smembers(String key) {
        return jedis.smembers(key);
    }

    public void srem(String key, String... member) {
        jedis.srem(key, member);
    }

    public boolean hexists(String key, String field) {
        return jedis.hexists(key, field);
    }

    public void del(String key) {
        jedis.del(key);
    }

    public void del(String... keys) {
        jedis.del(keys);
    }
}
