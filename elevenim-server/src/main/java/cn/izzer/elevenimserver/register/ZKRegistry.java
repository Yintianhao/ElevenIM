package cn.izzer.elevenimserver.register;

import cn.izzer.elevenimserver.config.AppConfig;
import cn.izzer.elevenimserver.util.SpringBeanFactory;
import cn.izzer.elevenimserver.util.ZKUtil;
import common.Const;
import org.apache.zookeeper.CreateMode;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author yintianhao
 * @createTime 2020/11/22 23:02
 * @description
 */
public class ZKRegistry implements Runnable {

    private ZKUtil zkUtil;

    private String ip;

    private int port;

    private AppConfig appConfig;

    public ZKRegistry(){
        appConfig = SpringBeanFactory.getBean(AppConfig.class);
        zkUtil = SpringBeanFactory.getBean(ZKUtil.class);
    }

    @Override
    public void run() {
        String parentNodePath = String.format("/%s",Const.ZNODE_SERVER_NAME);
        String path = String.format("/%s/%s", Const.ZNODE_SERVER_NAME,appConfig.getAppName());
        String data = String.format("%s:%s",appConfig.getAppIp(),appConfig.getSocketPort());
        if(zkUtil.exist(parentNodePath,false)==null){
            zkUtil.createNode(parentNodePath,"",CreateMode.PERSISTENT);
        }
        zkUtil.createNode(path,data,CreateMode.EPHEMERAL);
    }
}
