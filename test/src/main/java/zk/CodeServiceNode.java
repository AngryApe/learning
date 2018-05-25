/**
 * Copyright (C), 杭州中恒云能源互联网技术有限公司，保留所有权利
 */
package zk;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.locks.InterProcessMutex;
import org.apache.curator.retry.RetryNTimes;
import org.apache.zookeeper.CreateMode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * CoderGenerator 节点编号生成器<br/>
 * 1. 由于目前CoderGenerator是以jar包方式运行，对于单个服务，若使用固定的服务编码，在集群高并发环境下,不同实例会产生重复code;<br/>
 * 2. 对于不同的服务，在调用CoderGenerator 内的同一个方法时，若服务编码相同，也存在重复code的可能；<br/>
 * 基于以上原因，每个服务实例均必须拥有唯一的服务编码。服务编码是char(0-255)类型，考虑到可读性，目前只取35-126共92个值，即最多支持92个使用CoderGenerator的服务。<br/>
 * <p>
 * <ul>依赖jar包：
 * <li>org.apache.zookeeper:zookeeper:3.4.6</li>
 * <li>org.apache.curator:curator-recipes:4.0.0,参考：http://curator.apache.org/zk-compatibility.html</li>
 * </ul>
 *
 * @auther qiys@hzzh.com
 * @date 2018-01-19
 */
@Service("codeServiceNode")
public class CodeServiceNode {

    private static Logger logger = LoggerFactory.getLogger(CodeServiceNode.class);

    private final static String ZK_ROOT = "/coder_generator_root";

    private final static String ZK_LOCK = "/coder_generator_lock";

    private String url = "10.1.170.151:2181"; //需要改为动态配置

    private CuratorFramework zk;

    public static char serviceId;//外部以静态属性方式获取服务编码

//    static {
//        CodeServiceNode node = new CodeServiceNode();
////        serviceId = node.generate();
//    }

    /**
     * 创建并启动zk连接
     */
    @PostConstruct
    private void init() {
        System.out.println("-----Init----");
        zk = CuratorFrameworkFactory.builder().sessionTimeoutMs(15000)
                .retryPolicy(new RetryNTimes(3, 5000)).connectionTimeoutMs(50000).connectString(url)
                .build();
        zk.start();
        System.out.println("-----Init-Done----");
    }

    /**
     * 生成服务编码
     */
    private char generate() {
        //分布式锁,保证同一个节点号不会被多个服务同时获取
        InterProcessMutex lock = new InterProcessMutex(zk, ZK_LOCK);
        Character nodeId = null;
        try {
            // 获得锁
            if (lock.acquire(10, TimeUnit.SECONDS)) {
                if (zk.checkExists().forPath(ZK_ROOT) == null) { //不存在根节点则创建
                    zk.create().withMode(CreateMode.PERSISTENT).forPath(ZK_ROOT);
                }
                List<String> nodes = zk.getChildren().forPath(ZK_ROOT);
                for (char i = 35; i < 127; i++) {
                    //为了直观，以数字形式保存
                    if (!nodes.contains(Integer.valueOf(i).toString())) {
                        nodeId = i;
                        break;
                    }
                }
                if (nodeId == null) {
                    logger.error(
                            "Too many services started, 92 [35 to 126] services supported, service is shutting down.");
                    System.exit(0);//退出程序
                } else {
                    zk.create().withMode(CreateMode.EPHEMERAL)
                            .forPath(ZK_ROOT + "/" + Integer.valueOf(nodeId).toString(),
                                    String.valueOf(System.currentTimeMillis()).getBytes());
                }
            } else {
                logger.error(
                        "Acquire lock failed, can't generate service id, try to restart service please.");
                System.exit(0);//退出程序
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // 释放锁
            try {
                lock.release();
            } catch (Exception e) {
                logger.error("Release lock[{}] failed, do it manual please.", ZK_LOCK);
            }
        }
        return nodeId;
    }

    @PreDestroy
    public void zkClose() {
        // 关闭连接,节点自动释放
        zk.close();
    }

    public static void main(String[] args) {
        CodeServiceNode node = new CodeServiceNode();
        node.init();
        for (int i = 0; i <= 255; i++) {
            System.out.println(node.generate());
//            System.err.println((char)(Integer.valueOf(i)));
            System.out.println("-----");
        }
//        node.zkClose();
    }
}
