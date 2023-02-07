package com.example.zookeeper;

import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.api.BackgroundCallback;
import org.apache.curator.framework.api.CuratorEvent;
import org.apache.curator.framework.recipes.cache.*;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.data.Stat;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

@SpringBootTest
class ZookeeperApplicationTests {

    private CuratorFramework client;

    @Test
    void contextLoads() {

    }

    @Test
    void zkStart() throws Exception {
        // 重试策略
        RetryPolicy retry = new ExponentialBackoffRetry(3000, 10);
        // zkserver地址，会话超时时间，连接超时时间，重试策略

        new Thread(new Runnable() {
            String s;
            @Override
            public void run() {
                try {
                    s = client.create().withMode(CreateMode.EPHEMERAL_SEQUENTIAL).forPath("/");
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
                System.out.println(Thread.currentThread().getName() + "..............." + s);
            }
        }, "thread1").start();
        new Thread(new Runnable() {
            String s1;
            @Override
            public void run() {
                try {
                    s1 = client.create().withMode(CreateMode.EPHEMERAL_SEQUENTIAL).forPath("/");
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
                System.out.println(Thread.currentThread().getName() + "..............." + s1);
            }
        }, "thread2").start();
        new Thread(new Runnable() {
            String s2;
            @Override
            public void run() {
                try {
                    s2 = client.create().withMode(CreateMode.EPHEMERAL_SEQUENTIAL).forPath("/");
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
                System.out.println(Thread.currentThread().getName() + "..............." + s2);
            }
        }, "thread3").start();


        client.close();
    }

    /**
     * 创建节点
     * @throws Exception
     */
    @Test
    void zkCreate() throws Exception {
        // 重试策略
        RetryPolicy retry = new ExponentialBackoffRetry(3000, 10);
        // zkServer地址，会话超时时间，连接超时时间，重试策略
        client = CuratorFrameworkFactory.newClient("localhost:2181", 60 * 1000, 15 * 1000, retry);
        client.start();
        // case1. 如果该节点没有指定存储数据，则该节点存储当前主机地址host
//        String s = client.create().forPath("/javaAPITest");
        // case2. 指定该节点存储数据
        client.create().forPath("/javaAPITest/app1", "hello".getBytes(StandardCharsets.UTF_8));
        // case3. 指定该节点类型（临时），默认永久
        client.create().withMode(CreateMode.EPHEMERAL).forPath("/javaAPITest/app2", "hello".getBytes(StandardCharsets.UTF_8));
        // case4. 创建多级节点，zk是不允许创建父节点不存在的多级节点的，如app3不存在，则不可以直接创建app3/p1，加上creatingParentsIfNeeded就可以了
        client.create().creatingParentsIfNeeded().forPath("/javaAPITest/app3/p1");
        client.close();
    }

    /**
     * 获取节点信息
     * 1. 获取节点存储数据
     * 2. 获取子节点
     * 3. 查询节点状态信息
     * @throws Exception
     */
    @Test
    void zkGet() throws Exception {
        // 重试策略
        RetryPolicy retry = new ExponentialBackoffRetry(3000, 10);
        // zkServer地址，会话超时时间，连接超时时间，重试策略
        client = CuratorFrameworkFactory.newClient("localhost:2181", 60 * 1000, 15 * 1000, retry);
        client.start();
        // case1. 获取节点存储数据
        byte[] bytes = client.getData().forPath("/javaAPITest/app1");
        System.out.print("该节点存储数据：");
        System.out.println(new java.lang.String(bytes));
        // case2. 获取当前节点的所有子节点
        List<java.lang.String> strings = client.getChildren().forPath("/MyRPC");
        System.out.println("子节点：");
        strings.stream().forEach(System.out::println);
        // case3. 查询节点状态信息
        Stat stat = new Stat();
        client.getData().storingStatIn(stat).forPath("/MyRPC");
        System.out.print("当前节点状态信息：");
        System.out.println(stat);
        client.close();
    }

    /**
     * 修改节点数据
     * 1. 直接修改
     * 2. 依据版本号修改，类似于乐观锁原理
     *
     * @throws Exception
     */
    @Test
    void zkSet() throws Exception {
        // 重试策略
        RetryPolicy retry = new ExponentialBackoffRetry(3000, 10);
        // zkServer地址，会话超时时间，连接超时时间，重试策略
        client = CuratorFrameworkFactory.newClient("localhost:2181", 60 * 1000, 15 * 1000, retry);
        client.start();

        // case1. 基础修改
        byte[] bytes = client.getData().forPath("/javaAPITest/app1");
        System.out.println("基础修改前：" + new String(bytes));
        client.setData().forPath("/javaAPITest/app1", "world".getBytes(StandardCharsets.UTF_8));
        System.out.println("基础修改后：" + new String(client.getData().forPath("/javaAPITest/app1")));
        // case2. 依据版本修改
        Stat stat = new Stat();
        byte[] versionPre = client.getData().storingStatIn(stat).forPath("/javaAPITest/app1");
        System.out.println("版本修改前：" + new String(versionPre));
        int version = stat.getVersion();
        System.out.println("修改前版本号" + version);
        client.setData().withVersion(version).forPath("/javaAPITest/app1", ("version" + (version + 1)).getBytes(StandardCharsets.UTF_8));
        byte[] versionAfter = client.getData().storingStatIn(stat).forPath("/javaAPITest/app1");
        System.out.println("版本修改后：" + new String(versionAfter));
        client.getData().storingStatIn(stat).forPath("/javaAPITest/app1");
        System.out.println("修改后版本号：" + stat.getVersion());

        client.close();
    }

    /**
     * 节点删除：delete deleteAll（删除该节点及子节点）
     *  1. 删除节点
     *  2. 删除含有子节点的节点
     *  3. 必须删除成功
     *  4. 回调
     * @throws Exception
     */
    @Test
    void zkDelete() throws Exception {
        // 重试策略
        RetryPolicy retry = new ExponentialBackoffRetry(3000, 10);
        // zkServer地址，会话超时时间，连接超时时间，重试策略
        client = CuratorFrameworkFactory.newClient("localhost:2181", 60 * 1000, 15 * 1000, retry);
        client.start();
        // 1
//        client.create().forPath("/javaAPITest/app3");
//        client.delete().forPath("/javaAPITest/app3");

        // 2
//        client.create().creatingParentsIfNeeded().forPath("/javaAPITest/app3/ap1");
//        System.out.println(new String(client.getData().forPath("/javaAPITest/app3/ap1")));
//        client.delete().deletingChildrenIfNeeded().forPath("/javaAPITest/app3");
//        List<String> strings = client.getChildren().forPath("/javaAPITest");
//        strings.stream().forEach(System.out::println);

        // 3. 必须成功的删除（可能由于各种原因第一次删除请求没成功这种情况）
//        client.delete().guaranteed().forPath("/javaAPITest/app1");

        // 4. 回调
        client.create().forPath("/javaAPITest/app3");
        /*client.delete().guaranteed().inBackground(new BackgroundCallback() {
            @Override
            public void processResult(CuratorFramework curatorFramework, CuratorEvent curatorEvent) throws Exception {
                System.out.println("delete successfully!");
                System.out.println(curatorEvent);
            }
        }).forPath("/javaAPITest/app3");*/

        client.delete().guaranteed().inBackground((CuratorFramework curatorFramework, CuratorEvent curatorEvent)->{
            System.out.println("delete successfully!");
            System.out.println(curatorEvent);
        }).forPath("/javaAPITest/app3");

        client.close();


    }

    /**
     * 事件监听
     * 1. 指定一个节点注册监听器
     * 2. 监听指定节点的子节点
     * 3. 监听指定节点及其子节点
     */
    @Test
    void zkMonitor() throws Exception {
        RetryPolicy retry = new ExponentialBackoffRetry(3000, 10);
        // zkServer地址，会话超时时间，连接超时时间，重试策略
        client = CuratorFrameworkFactory.newClient("localhost:2181", 60 * 1000, 15 * 1000, retry);
        client.start();

        // case1
        /*NodeCache nodeCache = new NodeCache(client, "/javaMonitorTest");
        nodeCache.getListenable().addListener(new NodeCacheListener() {
            @Override
            public void nodeChanged() throws Exception {
                System.out.println("/javaMonitorTest node has been changed!");
                System.out.println(client.getData().forPath("/javaMonitorTest"));
            }
        });
        nodeCache.start();*/

        //case2
        PathChildrenCache pathChildrenCache = new PathChildrenCache(client, "/javaMonitorTest", true);
        pathChildrenCache.getListenable().addListener(new PathChildrenCacheListener() {
            @Override
            public void childEvent(CuratorFramework curatorFramework, PathChildrenCacheEvent pathChildrenCacheEvent) throws Exception {
                System.out.println("child node has been changed!");
                // 针对子节点数据修改这一情况
                if (pathChildrenCacheEvent.getType().equals(PathChildrenCacheEvent.Type.CHILD_UPDATED)) {
                    byte[] data = pathChildrenCacheEvent.getData().getData();
                    System.out.println(new String(data));
                }
            }
        });
        pathChildrenCache.start();


        // case3
        TreeCache treeCache = new TreeCache(client, "/javaMonitorTest");
        treeCache.getListenable().addListener(new TreeCacheListener() {
            @Override
            public void childEvent(CuratorFramework curatorFramework, TreeCacheEvent treeCacheEvent) throws Exception {
                System.out.println("node or its child node has been changed");
            }
        });
        treeCache.start();

        Thread.sleep(30000);
    }


    /**
     * zookeeper实现分布式锁原理：
     * step1. 客户获取锁时，在lock节点下面创建临时顺序节点
     * step2. 获取该节点下的所有子节点，如果自己创建的节点序号最小，则认为该客户端获取到了锁
     * step3. 如果发现自己创建的节点并非所有子节点中最小的，则该客户端找到比自己小的那个节点，并对其注册事件监听器，监听删除事件
     * step4. 如果比自己小的那个节点发生删除事件，则该客户端的Watcher会收到通知，判断自己是否是子节点中最小的，是则获取到锁，否则重复
     *          上一个步骤获取比自己小的那个节点，并注册监听
     */
    @Test
    void zkRPCLock() {

    }

}
