package com.tuhu.mario.report;

import com.alibaba.otter.canal.common.http.HttpHelper;
import com.alibaba.otter.canal.common.utils.AddressUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author: jianglei
 */
public class  ReportClient {
    private String url;
    private String username;
    private String password;
    private String registerIp;
    private HttpHelper httpHelper;
    private Map<String,String> header = new HashMap<>();
    private final static String REPORT_ERROR_URL = "/api/v1/report/error";

    ReportClient(String url, String username, String password){
        this.url = url;
        this.username = username;
        this.password = password;
        this.registerIp = AddressUtils.getHostIp();
        this.httpHelper = new HttpHelper();

        header.put("user",username);
        header.put("pwd",password);
    }

    public void reportError(String destiantion, String msg){
        Map <String,String> body = new HashMap<>();

        body.put("destiantion", destiantion);
        body.put("ip", this.registerIp);
        body.put("msg", msg);

        httpHelper.post(url + REPORT_ERROR_URL, header, body, 10000);
    }
}
