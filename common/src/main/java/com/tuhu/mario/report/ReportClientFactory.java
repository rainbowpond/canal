package com.tuhu.mario.report;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @Author: jianglei
 */
public class ReportClientFactory {
    private static Map<String, ReportClient> reportClientMap = new ConcurrentHashMap<>();

    public synchronized static ReportClient getReportClient(String url, String user, String pwd){
        String key = url+"-"+user+"-"+pwd;
        if(!reportClientMap.containsKey(key)){
            ReportClientBuilder reportClientBuilder = new ReportClientBuilder().url(url).username(user).password(pwd);
            ReportClient reportClient = reportClientBuilder.build();

            reportClientMap.put(key, reportClient);
        }

        return reportClientMap.get(key);
    }


}
