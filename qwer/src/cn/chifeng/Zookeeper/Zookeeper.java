package cn.chifeng.Zookeeper;

import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.ZooDefs;
import org.apache.zookeeper.ZooKeeper;

public class Zookeeper {
    private static final String CONNECT_STRING = "localhost:8080";
    private static final int SESSION_TIMEOUT = 3000;

    public static void main(String[] args) throws Exception {
        ZooKeeper zooKeeper = new ZooKeeper(CONNECT_STRING, SESSION_TIMEOUT, null);

        // 创建节点并设置节点描述信息
        zooKeeper.create("/t1", "Test1".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
        zooKeeper.create("/t1/tt1", "Test11".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
        zooKeeper.create("/t1/tt2", "测试数据2".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
        zooKeeper.create("/t1/tt3", new byte[0], ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);

        // 修改节点描述信息
        zooKeeper.setData("/t1/tt1", "测试数据1".getBytes(), -1);
        zooKeeper.setData("/t1/tt2", new byte[0], -1);

        // 删除节点
        zooKeeper.delete("/t1/tt3", -1);
        zooKeeper.delete("/t1", -1);

        zooKeeper.close();
    }
}
