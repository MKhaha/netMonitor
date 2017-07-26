package com.ywgroup.iecloud.controller;

import com.ywgroup.iecloud.common.ServerResponse;
import com.ywgroup.iecloud.service.INetMonitorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by guotao on 2017/7/25.
 * com.ywgroup.iecloud.controller
 * netMonitor
 */
@Controller
@RequestMapping("/")
public class NetMonitorController {


    @Autowired
    private INetMonitorService iNetMonitorService;

    @RequestMapping(value = "demandCanBeSupport.do")
    @ResponseBody
    public ServerResponse<String> demandCanBeSupport(String speedInByteString) {

        int speedInByte;

        try {
            speedInByte = Integer.parseInt(speedInByteString);
            if (speedInByte <= 0) {
                return ServerResponse.createByErrorMessage("您必须要传入一个大于0的整数");
            }
        } catch (NumberFormatException e) {
            return ServerResponse.createByErrorMessage("您必须要传入一个大于0的整数");
        }

        return iNetMonitorService.demandCanBeSupport(speedInByte);
    }

}
