package redis;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.JedisCluster;

import java.util.HashSet;
import java.util.Set;

public class BaseOperator {

    private final Logger LOGGER = LoggerFactory.getLogger(BaseOperator.class);

    //测试
    private String hostAndPort = "10.1.170.218:7000,10.1.170.219:7000,10.1.170.220:7000,10.1.170.218:7001,10.1.170.219:7001,10.1.170.220:7001";
    //开发
    private String hostAndPortDev = "10.1.170.207:17000,10.1.170.207:17001,10.1.170.207:17002,10.1.170.207:17003,10.1.170.207:17004,10.1.170.207:17005";

    JedisCluster jedis = null;

    public BaseOperator(boolean dev) {
        if (dev)
            hostAndPort = hostAndPortDev;
        init();
    }

    private void init() {
        if (jedis == null) {
            try {
                Set<HostAndPort> jedisClusterNodes = new HashSet<HostAndPort>();
                String[] singleUrl = hostAndPort.trim().split(",");
                for (int i = 0; i < singleUrl.length; i++) {
                    String[] single = singleUrl[i].split(":");
                    jedisClusterNodes.add(new HostAndPort(single[0], Integer.parseInt(single[1])));
                }
                jedis = new JedisCluster(jedisClusterNodes);
            } catch (Exception e) {
                LOGGER.error("RedisClusterService init error", e);
            }
        }
    }
}
