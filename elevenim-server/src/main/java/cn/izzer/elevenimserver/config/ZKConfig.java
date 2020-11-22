package cn.izzer.elevenimserver.config;

import lombok.extern.slf4j.Slf4j;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.CountDownLatch;

/**
 * @author yintianhao
 * @createTime 2020/11/22 21:11
 * @description
 */
@Configuration
@Slf4j
public class ZKConfig {

    @Value("${zookeeper.address}")
    private String connectString;

    @Value("${zookeeper.timeout}")
    private int timeout;

    @Bean(name = "zkClient")
    public ZooKeeper zkClient(){
        ZooKeeper zooKeeper = null;
        try {
            final CountDownLatch countDownLatch = new CountDownLatch(1);
            zooKeeper = new ZooKeeper(connectString, timeout, new Watcher() {
                @Override
                public void process(WatchedEvent watchedEvent) {
                    countDownLatch.countDown();
                }
            });
            countDownLatch.await();
            log.info(String.format("ZooKeeper 初始化连接成功: %s ",zooKeeper.getState()));
        }catch (Exception e){
            log.error("ZooKeeper 初始化连接失败,错误信息: %s",e);
        }
        return zooKeeper;
    }

}
