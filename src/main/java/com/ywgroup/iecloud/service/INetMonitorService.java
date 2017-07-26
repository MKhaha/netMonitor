package com.ywgroup.iecloud.service;

import com.ywgroup.iecloud.common.ServerResponse;

/**
 * Created by guotao on 2017/7/25.
 * com.ywgroup.iecloud.service
 * netMonitor
 */
public interface INetMonitorService {

    ServerResponse<String> demandCanBeSupport(int speedInByte);
}
