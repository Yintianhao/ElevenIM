package cn.izzer.elevenimroute.controller;

import cn.izzer.elevenimroute.util.ZKUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author yintianhao
 * @createTime 2020/11/23 23:52
 * @description
 */
@RestController
public class IndexController  {


    @Autowired
    private ZKUtil zkUtil;

    @GetMapping("/getAll")
    public String getAllZKInfo(){
        //测试，获取全部的Zookeeper注册信息
        
    }
}
