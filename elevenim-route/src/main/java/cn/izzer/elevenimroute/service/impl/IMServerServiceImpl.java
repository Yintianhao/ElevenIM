package cn.izzer.elevenimroute.service.impl;

import cn.izzer.elevenimroute.exception.IMServerException;
import cn.izzer.elevenimroute.service.IMServerService;
import cn.izzer.elevenimroute.util.ZKUtil;
import common.Const;
import org.apache.zookeeper.KeeperException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author yintianhao
 * @createTime 2020/11/29 21:43
 * @description
 */
@Service
public class IMServerServiceImpl implements IMServerService {


    @Autowired
    private ZKUtil zkUtil;


    @Override
    public String chooseValidServer() throws KeeperException,InterruptedException,IMServerException {
        String parentPah = String.format("/%s", Const.ZNODE_SERVER_NAME);
        List<String> sereverList = zkUtil.getAllChildrenData(parentPah);
        if(sereverList==null||sereverList.size()<=0){
            throw new IMServerException("There is no valid server");
        }
        int serverCount = sereverList.size();
        int random = (int)(serverCount*Math.random());
        return sereverList.get(random);
    }
}
