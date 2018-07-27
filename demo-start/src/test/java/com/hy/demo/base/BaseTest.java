package com.hy.demo.base;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hy.demo.Application;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.context.WebApplicationContext;

import java.util.Map;

/**
 * BaseTest
 *          base
 *
 * @author  Yu.HaiYang
 * @date    2018/7/13
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = {Application.class})
public class BaseTest {

    protected MockMvc mvc;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Before
    public void setUp() {
        mvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    protected RequestBuilder get(String url) {
        return get(url, null, null);
    }

    protected RequestBuilder get(String url, Map<String, String> params) {
        return get(url, params, null);
    }

    protected RequestBuilder get(String url, Map<String, String> params, Map<String, String> headers) {
        HttpHeaders httpHeaders = new HttpHeaders();
        if (headers != null) {
            headers.forEach((key, Value) -> httpHeaders.add(key, Value));
        }
        MultiValueMap httpParams = new LinkedMultiValueMap();
        if (params != null) {
            params.forEach((key, Value) -> httpParams.add(key, Value));
        }

        return MockMvcRequestBuilders.get(url).headers(httpHeaders).params(httpParams);
    }

    protected RequestBuilder post(String url, Object requestData) throws JsonProcessingException {
        return post(url, requestData, null);
    }

    protected RequestBuilder post(String url, Object requestData, Map<String, String> headers) throws JsonProcessingException {
        HttpHeaders httpHeaders = new HttpHeaders();
        if (headers != null) {
            headers.forEach((key, Value) -> httpHeaders.add(key, Value));
        }

        return MockMvcRequestBuilders.post(url).headers(httpHeaders).contentType(MediaType.APPLICATION_JSON).content(toJsonData(requestData));
    }

    protected RequestBuilder put(String url, Object requestData) throws JsonProcessingException {
        return put(url, requestData, null);
    }

    protected RequestBuilder put(String url, Object requestData, Map<String, String> headers) throws JsonProcessingException {
        HttpHeaders httpHeaders = new HttpHeaders();
        if (headers != null) {
            headers.forEach((key, Value) -> httpHeaders.add(key, Value));
        }

        return MockMvcRequestBuilders.put(url).headers(httpHeaders).contentType(MediaType.APPLICATION_JSON).content(toJsonData(requestData));
    }

    protected RequestBuilder delete(String url) throws JsonProcessingException {
        return delete(url, null);
    }

    protected RequestBuilder delete(String url, Map<String, String> headers) throws JsonProcessingException {
        HttpHeaders httpHeaders = new HttpHeaders();
        if (headers != null) {
            headers.forEach((key, Value) -> httpHeaders.add(key, Value));
        }

        return MockMvcRequestBuilders.delete(url).headers(httpHeaders);
    }

    protected String toJsonData(Object data) throws JsonProcessingException {
        return new ObjectMapper().writeValueAsString(data);
    }
}
