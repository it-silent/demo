package com.hy.demo.service.dingapi.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.hy.demo.common.utils.Connector;
import com.hy.demo.service.dingapi.DingApiService;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Map;

/**
 * DingApiServiceImpl
 *
 * @author silent
 * @date 2018/4/12
 */
@Service
public class DingApiServiceImpl implements DingApiService {

//    private String name;

    private Map<Long, Long> longMap;

    @Override
    public String send(String address, String msg) {
        String result = null;
        CloseableHttpResponse httpResponse = null;

        HttpPost httpPost = new HttpPost(address);
        StringEntity entity = new StringEntity(fillMsg(msg), "UTF-8");
        entity.setContentEncoding("UTF-8");
        entity.setContentType("application/json");
        httpPost.setEntity(entity);
        try {
            CloseableHttpClient httpClient = HttpClients.createDefault();
            httpResponse = httpClient.execute(httpPost);
            if (HttpStatus.SC_OK == httpResponse.getStatusLine().getStatusCode()) {
                result = EntityUtils.toString(httpResponse.getEntity());
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            if (httpResponse != null) {
                try {
                    httpResponse.close();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }

        return result;
    }

    @Override
    public String sendByConnector(String address, String msg) {
        String result = null;
        try {
            result = Connector.connect(address).post().contentTypeToJson().body(fillMsg(msg)).text();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return result;
    }

//    @Override
//    public String dingJdk(String address, String msg) {
//        String result;
//        try {
//            DingtalkChatbotClient client = new DingtalkChatbotClient();
//            MarkdownMessage message = new MarkdownMessage();
//            message.setTitle("MarkdownMessage");
//            message.add("sha wa di ka");
//            SendResult send = client.send(address, message);
//            result = send.getErrorMsg();
//        } catch (Exception e) {
//            result = e.getMessage();
//        }
//        return result;
//    }

    private  String fillMsg(String msg) {

        ObjectNode mdMsg = new ObjectMapper().createObjectNode();

        mdMsg.put("msgtype", "markdown");
        ObjectNode markdown = new ObjectMapper().createObjectNode();
        markdown.put("title", "T");
        markdown.put("text", msg);
        mdMsg.put("markdown", markdown.toString());
        return mdMsg.toString();
    }

    public static void main(String[] args) {
        String msg = "测试msg";
        ObjectMapper jsonMapper = new ObjectMapper();

        ObjectNode node = new ObjectNode(JsonNodeFactory.instance);
        ObjectNode mdMsg = jsonMapper.createObjectNode();

        mdMsg.put("msgtype", "markdown");

        ObjectNode markdown = jsonMapper.createObjectNode();
        markdown.put("title", "T");
        markdown.put("text", msg);
        mdMsg.put("markdown", markdown.toString());
        System.err.println(mdMsg.toString());
    }


}
