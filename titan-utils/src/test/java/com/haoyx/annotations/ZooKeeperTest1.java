package com.haoyx.annotations;

import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.ZooDefs;
import org.apache.zookeeper.ZooKeeper;

import java.io.IOException;
import java.util.List;

/**
 * Created by haoyuexun on 2018/3/9.
 */
public class ZooKeeperTest1 {
    public static void main(String[] args) {
        String znode = "/dubbo/com.talk51.modules.asset.IAddUserLeaveService/providers";
//        String znode = "/dubbo";
        try {

            ZooKeeper zkClient =  new ZooKeeper("172.16.16.36:2181",3000, null);
//            zkClient.create(znode, "aaaccc".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
//            System.out.println(zkClient.getData("/abc",false,null));
//
//            System.out.println(zkClient.getChildren(znode,null));
//            List<String> childrens =  zkClient.getChildren(znode,false);
//            for(String child:childrens){
//                System.out.println(child);
//            }
            String str = new String(zkClient.getData(znode, false, null));
            System.out.println(str);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (KeeperException e) {
            e.printStackTrace();
        }

    }
}
