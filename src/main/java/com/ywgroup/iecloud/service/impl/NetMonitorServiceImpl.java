package com.ywgroup.iecloud.service.impl;

import com.ywgroup.iecloud.common.ServerResponse;
import com.ywgroup.iecloud.service.INetMonitorService;
import com.ywgroup.iecloud.util.NetMonitor;
import com.ywgroup.iecloud.util.PropertiesUtil;
import org.springframework.stereotype.Service;

/**
 * Created by guotao on 2017/7/25.
 * com.ywgroup.iecloud.service.impl
 * netMonitor
 */
@Service("iNetMonitorService")
public class NetMonitorServiceImpl implements INetMonitorService {

    @Override
    public ServerResponse<String> demandCanBeSupport(int speedInByte) {
        int maxNetSpeed = 0;

        String maxNetSpeedString = PropertiesUtil.getProperty("maxNetSpeed");
        if (org.apache.commons.lang3.StringUtils.isBlank(maxNetSpeedString)) {
            return ServerResponse.createByErrorMessage("获取最大负载网速错误");
        }

        try {
            maxNetSpeed = Integer.parseInt(maxNetSpeedString);
        } catch (NumberFormatException e) {
            return ServerResponse.createByErrorMessage("解析最大负载网速字符串错误");
        }

        if (maxNetSpeed - NetMonitor.getRxSpeed() > speedInByte) {
            return ServerResponse.createBySuccessMessage("当前负载能够支持您请求的网速");
        } else {
            return ServerResponse.createByErrorMessage("当前负载不能够支持您请求的网速");
        }
    }
}
