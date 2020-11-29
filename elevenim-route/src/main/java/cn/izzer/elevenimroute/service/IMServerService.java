package cn.izzer.elevenimroute.service;

import cn.izzer.elevenimroute.exception.IMServerException;
import org.apache.zookeeper.KeeperException;

/**
 * @author yintianhao
 * @createTime 2020/11/29 21:40
 * @description 和服务器相关的业务逻辑
 */
public interface IMServerService {
    /**
     * 从ZK已经注册的服务器列表里选取一个可用的服务器连接给客户端
     * */
    String chooseValidServer() throws KeeperException,InterruptedException,IMServerException;

}
