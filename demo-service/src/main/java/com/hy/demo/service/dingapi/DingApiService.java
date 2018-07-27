package com.hy.demo.service.dingapi;

/**
 * DingApiService
 *
 * @author silent
 * @date 2018/4/12
 */
public interface DingApiService {

    String send(String address, String msg);

    String sendByConnector(String address, String msg);

//    String dingJdk(String address, String msg);
}
