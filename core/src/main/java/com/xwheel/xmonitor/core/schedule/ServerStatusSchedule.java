package com.xwheel.xmonitor.core.schedule;

import com.xwheel.xmonitor.core.collector.serverstatus.service.ServerStatusCollectorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author: lei hang
 * @date: 2016年03月22日
 * @description: 应用信息收集任务
 */

@Service("serverStatusSchedule")
public class ServerStatusSchedule implements Schedule {

    @Autowired
    ServerStatusCollectorService serverStatusCollectorService;

    public void schedule() {
        serverStatusCollectorService.collectStatus();
    }
}
