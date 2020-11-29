package cn.izzer.elevenimserver.controller;

import cn.izzer.elevenimserver.util.ZKUtil;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.Watcher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author yintianhao
 * @createTime 2020/11/22 22:10
 * @description
 */
@RestController
public class IndexController {

    @Autowired
    public ZKUtil zkUtil;

    @GetMapping("/create/{path}/{data}")
    public String createNode(@PathVariable("path")String path,@PathVariable("data")String data){
        path = "/"+path;
        zkUtil.createNode(path,data,CreateMode.EPHEMERAL);
        return "Create success";
    }

    @GetMapping("/delete/{path}")
    public String deleteNode(@PathVariable("path")String path){
        path = "/"+path;
        zkUtil.deleteNode(path);
        return "Delete success";
    }

    @GetMapping("/get/{path}")
    public String getAll(@PathVariable("path")String path){
        String data = zkUtil.getData(path,true);
        return data;
    }
}
