package cn.izzer.elevenimroute.config;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @author yintianhao
 * @createTime 2020/11/22 22:33
 * @description
 */
@Component
public class AppConfig {
    @Getter
    @Value("${app.ip}")
    private String appIp;

    @Getter
    @Value("${app.port}")
    private int socketPort;

    @Getter
    @Value("${server.port}")
    private int serverPort;

    @Getter
    @Value("${spring.application.name}")
    private String appName;

    @Getter
    @Value("${zookeeper.address}")
    private String zkAddress;
}
