package com.tuhu.mario.alarm;

import com.alibaba.otter.canal.common.alarm.CanalAlarmHandler;
import com.tuhu.mario.report.ReportClient;
import com.tuhu.mario.report.ReportClientFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @Author: jianglei
 */
public class MarioAlarmHandler implements CanalAlarmHandler {
    Logger logger = LoggerFactory.getLogger(MarioAlarmHandler.class);

    private AtomicInteger startCount = new AtomicInteger();
    private volatile boolean isStart = false;
    private String url;
    private String username;
    private String password;
    private String registerIp;
    private ReportClient reportClient;

    public MarioAlarmHandler(String url, String username, String password){
        this.url = url;
        this.username = username;
        this.password = password;


        this.reportClient = ReportClientFactory.getReportClient(url, username, password);
    }


    @Override
    public void sendAlarm(String destination, String msg) {
        try {
            reportClient.reportError(destination, msg);
        }catch (Exception e){
            logger.error("report to admin error", e);
        }
    }

    @Override
    public void start() {
        startCount.incrementAndGet();
        isStart = true;

    }

    @Override
    public void stop() {
        startCount.decrementAndGet();

        //stop
        if(startCount.get() == 0){
            isStart = false;
        }

    }

    @Override
    public boolean isStart() {
        return isStart;
    }
}

