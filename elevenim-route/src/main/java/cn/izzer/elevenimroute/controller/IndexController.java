package cn.izzer.elevenimroute.controller;

import cn.izzer.elevenimroute.service.IMServerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * @author yintianhao
 * @createTime 2020/11/23 23:52
 * @description
 */
@RestController
@Slf4j
public class IndexController  {


    @Autowired
    private IMServerService imServerService;

    @GetMapping("/getAll")
    public String getAllZKInfo() throws Exception{
        String server = imServerService.chooseValidServer();
        log.info(String.format("Client choose a server:%s",server));
        return server;
    }
}
