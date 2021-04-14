package com.tuhu.mario.report;

import org.apache.commons.lang.StringUtils;

/**
 * @Author: jianglei
 */
public class ReportClientBuilder {
    private String url;
    private String username;
    private String password;

    public  ReportClientBuilder url(String url){
        this.url = url;
        return this;
    }

    public  ReportClientBuilder username(String username){
        this.username = username;
        return this;
    }

    public  ReportClientBuilder password(String password){
        this.password = password;
        return this;
    }

    public ReportClient build(){
        if(StringUtils.isBlank(url) || StringUtils.isBlank(username) || StringUtils.isBlank(password)){
            throw new RuntimeException("缺少参数");
        }

        return new ReportClient(url, username, password);

    }
}
