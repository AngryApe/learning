package com.ape.zktest;

import org.apache.zookeeper.*;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.CountDownLatch;

/**
 * AngryApe created at 2017/10/9
 */
public class ZKGroup implements Watcher {

    private static final int SESSION_TIMEOUT = 5 * 1000;  //ms

    private ZooKeeper zooKeeper;

    private CountDownLatch latch = new CountDownLatch(1);

    private static final String ZK_SLASH = "/";

    public void process(WatchedEvent watchedEvent) {
        if (watchedEvent.getState().equals(Event.KeeperState.SyncConnected))
            latch.countDown();
        if (watchedEvent.getType().equals(Event.EventType.NodeDeleted)) {
            System.out.println("Delete node:" + watchedEvent.getPath());
        }
    }

    public void create(String groupName) throws KeeperException, InterruptedException {
        String path = ZK_SLASH + groupName;
        String createPath = zooKeeper
                .create(path, null, ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
        System.out.println("zk node created:" + createPath);
    }

    public void close() throws InterruptedException {
        zooKeeper.close();
    }

    private void connect(String hosts) throws IOException, InterruptedException {
        zooKeeper = new ZooKeeper(hosts, SESSION_TIMEOUT, this);
        latch.await();
    }

    public void join(String group, String name) throws KeeperException, InterruptedException {
        String path = path(group, name);
        zooKeeper.create(path, null, ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
        System.out.println("zk node created:" + path);
    }

    public void getChildren(String group) {
        String path = path(group);
        try {
            List<String> children = zooKeeper.getChildren(path, false);
            if (children.isEmpty()) {
                System.out.println("Group[" + group + "] is empty");
            }
            for (String child : children) {
                System.out.println(child);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (KeeperException e) {
            e.printStackTrace();
        }

    }

    public void deleteIfExist(String group) {
        String path = path(group);
        try {
            List<String> children = zooKeeper.getChildren(path, true);
            if (!children.isEmpty()) {
                for (String child : children) {
                    zooKeeper.delete(path + ZK_SLASH + child, -1);
                    System.out.println("Delete node:" + path + ZK_SLASH + child);
                }
            }
            if (zooKeeper.exists(path, true) != null)
                zooKeeper.delete(path, -1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (KeeperException e) {
            e.printStackTrace();
        }
    }

    private String path(String... paths) {
        if (paths == null || paths.length < 1)
            return "";
        StringBuilder nodePath = new StringBuilder();
        for (String path : paths) {
            nodePath.append(ZK_SLASH).append(path);
        }
        return nodePath.toString();
    }

    public static void main(String[] args)
            throws IOException, InterruptedException, KeeperException {
        ZKGroup group = new ZKGroup();
        String host = "192.168.10.250:2181";
        String groupName = "zoo";
        group.connect(host);
        group.deleteIfExist(groupName);
        group.create(groupName);
        group.join(groupName, "elephant");
        group.getChildren(groupName);
//        group.deleteIfExist(groupName);
        group.close();
    }

}
