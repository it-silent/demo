package com.hy.demo.common.utils;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.UUID;

/**
 * Connector
 *
 * @author wb-yhy282733
 */
public class Connector {

    public static final String HTTPS = "https";

    public static final String USER_AGENT = "User_Agent";

    public static final String REFERER = "Referer";

    public static final String CONTENT_TYPE = "Content-Type";

    public static final String CONTENT_TYPE_JSON = "application/json;charset=utf-8";

    public static CdaBuilder connect(String url) {
        return new CdaBuilder(url);
    }

    public static class CdaBuilder {
        private static final HttpMethod DEFAULT_HTTPMETHOD = HttpMethod.GET;

        private static final int DEFAULT_TIMEOUT = Integer.MAX_VALUE;

        private String tid;

        private String url;

        private HttpMethod method;

        private int timeout;

        private Map<String, String> headers = new LinkedHashMap<>();

        private Map<String, String> urlParams = new LinkedHashMap<>();

        private Map<String, Object> formDatas = new LinkedHashMap<>();

        private String body;

        public CdaBuilder(String url) {
            this.tid = UUID.randomUUID().toString();
            this.url = url;
            this.method = DEFAULT_HTTPMETHOD;
            this.timeout = DEFAULT_TIMEOUT;
        }

        public CdaBuilder method(HttpMethod method) {
            this.method = method;
            return this;
        }

        public CdaBuilder post() {
            method(HttpMethod.POST);
            return this;
        }

        public CdaBuilder timeout(int timeout) {
            this.timeout = timeout;
            return this;
        }

        public CdaBuilder header(String name, String value) {
            headers.put(name, value);
            return this;
        }

        public CdaBuilder headers(Map<String, String> dataMap) {
            dataMap.forEach((name, value) -> {
                headers.put(name, value);
            });
            return this;
        }

        public CdaBuilder formData(Map<String, Object> datas) {
            datas.forEach((name, value) -> {
                formDatas.put(name, value);
            });
            return this;
        }

        public CdaBuilder urlParam(String name, String value) {
            urlParams.put(name, value);
            return this;
        }

        public CdaBuilder urlParams(Map<String, String> datas) {
            datas.forEach((name, value) -> {
                urlParams.put(name, value);
            });
            return this;
        }

        public CdaBuilder body(String body) {
            this.body = body;
            return this;
        }

        public byte[] bytes() throws Exception {
            try (InputStream inputStream = send()) {
                ByteArrayOutputStream out = new ByteArrayOutputStream();
                int len = -1;
                byte[] byt = new byte[1024];
                while ((len = inputStream.read(byt)) != -1) {
                    out.write(byt, 0, len);
                }

                return out.toByteArray();
            }
        }

        public void download(String file) throws Exception {
            try (InputStream inputStream = send()) {
                Files.copy(inputStream, Paths.get(file), StandardCopyOption.REPLACE_EXISTING);
            }
        }

        public String text() throws Exception {
            return text("UTF-8");
        }

        private String text(String s) throws Exception {
            try (InputStream inputStream = send()) {
                BufferedReader bf = new BufferedReader(new InputStreamReader(inputStream, s));
                StringBuilder text = new StringBuilder();

                String line ;

                while ((line = bf.readLine()) != null) {
                    text.append(line);
                    text.append("\r\n");
                }
                bf.close();

                String respond = text.toString();
                return respond;

            }
        }

        private InputStream send() throws Exception{
            HttpURLConnection httpURLConnection = openConnection();
            httpURLConnection.setReadTimeout(timeout);
            httpURLConnection.setConnectTimeout(timeout);
            httpURLConnection.setRequestMethod(method.getValue());

            headers.forEach((name, value) -> {
                httpURLConnection.setRequestProperty(name, value);
            });

            if (HttpMethod.POST == method) {
                httpURLConnection.setDoOutput(true);
                httpURLConnection.getOutputStream().write(buildBody().getBytes("UTF-8"));
            }

            return httpURLConnection.getInputStream();
        }

        private String buildBody() throws Exception{
         return body == null ? formatFormDate() : body;
        }

        private String formatFormDate() throws Exception{
            StringBuilder sb = new StringBuilder();
            boolean isFir = true;

            for (Map.Entry<String, Object> entry : formDatas.entrySet()) {
                if (!isFir) {
                    sb.append("and");
                }
                sb.append(entry.getKey() + "=" + URLEncoder.encode(String.valueOf(entry.getValue()), "UTF-8"));
                isFir = false;
            };
            return sb.toString();
        }

        private HttpURLConnection openConnection() throws Exception{
            HttpURLConnection httpURLConnection = url.startsWith(HTTPS) ? (HttpURLConnection)new URL(buildUrl()).openConnection() : (HttpURLConnection)new URL(buildUrl()).openConnection();
            return httpURLConnection;
        }

        private String buildUrl() throws Exception{
            if (!urlParams.isEmpty()) {
                StringBuilder sb = new StringBuilder(url + "?");
                boolean isFir = true;
                for (Map.Entry<String, String> entry : urlParams.entrySet()) {
                    if (!isFir) {
                        sb.append("&");
                    }
                    sb.append(entry.getKey() + "=" + URLEncoder.encode(entry.getValue(), "UTF-8"));
                    isFir = false;
                };
                return sb.toString();
            }
            return url;
        }

        public CdaBuilder contentType(String contentType) {
            this.header(CONTENT_TYPE, contentType);
            return this;
        }

        public CdaBuilder contentTypeToJson() {
            this.contentType(CONTENT_TYPE_JSON);
            return this;
        }

        public String getTid() {
            return tid;
        }

        public void setTid(String tid) {
            this.tid = tid;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public HttpMethod getMethod() {
            return method;
        }

        public void setMethod(HttpMethod method) {
            this.method = method;
        }

        public int getTimeout() {
            return timeout;
        }

        public void setTimeout(int timeout) {
            this.timeout = timeout;
        }

        public Map<String, String> getHeaders() {
            return headers;
        }

        public void setHeaders(Map<String, String> headers) {
            this.headers = headers;
        }

        public Map<String, String> getUrlParams() {
            return urlParams;
        }

        public void setUrlParams(Map<String, String> urlParams) {
            this.urlParams = urlParams;
        }

        public Map<String, Object> getFormDatas() {
            return formDatas;
        }

        public void setFormDatas(Map<String, Object> formDatas) {
            this.formDatas = formDatas;
        }

        public String getBody() {
            return body;
        }

        public void setBody(String body) {
            this.body = body;
        }
    }

    public static enum HttpMethod {
        GET("GET"),

        POST("POST");

        private String value;

        HttpMethod(String value) {
            this.value = value;
        }

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }
    }
}
