package cn.izzer.elevenimroute.util;

import common.Const;
import lombok.extern.slf4j.Slf4j;
import org.apache.zookeeper.*;
import org.apache.zookeeper.data.Stat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * @author yintianhao
 * @createTime 2020/11/22 21:24
 * @description
 */
@Component
@Slf4j
public class ZKUtil {

    @Autowired
    private ZooKeeper zkClient;

    /**
     * 判断节点是否存在
     * @param path 节点路径
     * @param needWatch 指定是否复用Zookeeper中的Watcher
     * */
    public Stat exist(String path,boolean needWatch) {

        try {
            return zkClient.exists(path,needWatch);
        }catch (Exception e){
            log.error(String.format("Zookeeper 判断指定节点{%s}存在异常,异常信息:{%s}",path,e));
            return null;
        }

    }

    /**
     * 判断节点是否存在，并且设置监听事件
     * @param path 节点路径
     * @param watcher
     * */
    public Stat exist(String path, Watcher watcher){
        try {
            return zkClient.exists(path,watcher);
        }catch (Exception e){
            log.error("Zookeeper 判断指定节点{%s}并设置Watcher时存在异常,异常信息:{%s}\",path,e");
            return null;
        }
    }

    /**
     * 创建持久化节点
     * @param path
     * @param data
     */
    public boolean createNode(String path, String data){
        try {
            //创建节点，不设置持久化
            zkClient.create(path,data.getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE,CreateMode.EPHEMERAL);
            return true;
        } catch (Exception e) {
            log.error("Zookeeper 创建持久化节点异常{},{},{}",path,data,e);
            return false;
        }
    }

    /**
     * 修改持久化节点
     * @param path
     * @param data
     */
    public boolean updateNode(String path, String data){
        try {
            //zk的数据版本是从0开始计数的。如果客户端传入的是-1，则表示zk服务器需要基于最新的数据进行更新。如果对zk的数据节点的更新操作没有原子性要求则可以使用-1.
            //version参数指定要更新的数据的版本, 如果version和真实的版本不同, 更新操作将失败. 指定version为-1则忽略版本检查
            zkClient.setData(path,data.getBytes(),-1);
            return true;
        } catch (Exception e) {
            log.error("Zookeeper修改持久化节点异常{},{},{}",path,data,e);
            return false;
        }
    }

    /**
     * 删除持久化节点
     * @param path
     */
    public boolean deleteNode(String path){
        try {
            //version参数指定要更新的数据的版本, 如果version和真实的版本不同, 更新操作将失败. 指定version为-1则忽略版本检查
            zkClient.delete(path,-1);
            return true;
        } catch (Exception e) {
            log.error("Zookeeper删除持久化节点异常{},{}",path,e);
            return false;
        }
    }

    /**
     * 获取当前节点的子节点(不包含孙子节点)
     * @param path 父节点path
     */
    public List<String> getChildren(String path) throws KeeperException, InterruptedException{
        List<String> list = zkClient.getChildren(path, false);
        return list;
    }

    /**
     * 获取某节点下所有子节点的值
     * @param path 节点的路径 /xx/xx的形式
     * */
    public List<String> getAllChildrenData(String path) throws KeeperException,InterruptedException{

        List<String> nodeNames = zkClient.getChildren(path,false);
        List<String> nodeValues = new ArrayList<>();
        String parentPath = String.format("/%s", Const.ZNODE_SERVER_NAME);
        for (String name : nodeNames){
            String p = String.format("%s/%s",parentPath,name);
            String v = getData(p,false);
            nodeValues.add(v);
        }
        return nodeValues;
    }

    /**
     * 获取指定节点的值
     * @param path
     * @return
     */
    public String getData(String path,Watcher watcher){
        try {
            Stat stat=new Stat();
            byte[] bytes=zkClient.getData(path,watcher,stat);
            return new String(bytes);
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }
    /**
     * 获取指定节点的值
     * @param path
     * @return
     */
    public String getData(String path,boolean watcher){
        try {
            Stat stat=new Stat();
            byte[] bytes=zkClient.getData(path,watcher,stat);
            return  new String(bytes);
        }catch (Exception e){
            e.printStackTrace();
            return  null;
        }
    }

}
