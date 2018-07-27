package com.hy.demo;

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.hy.demo.common.utils.JwtUtils;
import com.hy.demo.dal.dataobject.SttDO;
import com.hy.demo.dal.dataobject.UserDO;
import com.hy.demo.lean.ThreadTest;
import com.hy.demo.service.dingapi.DingApiService;
import com.hy.demo.service.dingapi.impl.DingApiServiceImpl;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.collections4.MapUtils;
import org.apache.commons.collections4.QueueUtils;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.cglib.beans.BeanCopier;
import org.springframework.cglib.beans.BeanMap;
import org.springframework.http.HttpStatus;
import org.springframework.util.CollectionUtils;
import org.springframework.util.MultiValueMap;
import org.yaml.snakeyaml.Yaml;
import redis.clients.jedis.Jedis;

import java.io.*;
import java.lang.reflect.*;
import java.net.InetAddress;
import java.net.URL;
import java.net.UnknownHostException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.IsoFields;
import java.time.temporal.WeekFields;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * MainTest
 *
 * @author wb-yhy282733
 */
public class MainTest {

    private static final Pattern p = Pattern.compile("\\<dd class\\=\"fz24\">(.*?)\\<\\/dd>");

    private static String REDIS_HOST ;

    private static int REDIS_PORT ;

    private static final String GET_IP_URL = "http://ip.chinaz.com/getip.aspx";

    static {
        String pathname = System.getProperty("user.dir") + "/demo-start/src/main/resources/application-local.yml";
        try (FileInputStream inputStream = new FileInputStream(new File(pathname))) {
            Yaml yaml = new Yaml();
            Map map = yaml.loadAs(inputStream, Map.class);
            Map<String, Object> redis = (Map<String, Object>) map.get("redis");
            REDIS_HOST = redis.get("host").toString();
            REDIS_PORT = Integer.valueOf(redis.get("port").toString());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    public static void main(String[] args) {
        try {
//            math();
//            properties();
//            stringFormat();
//            subString();
//            inetAddress();
//            stringBuffer();
//            threadTest();
//            listAndQueue();
//            isPrimitive(boolean.class);
//            isPrimitive(Boolean.class);
//            isPrimitive(String.class);
//            isPrimitive(Object.class);
//            isPrimitive(void.class);
//            isPrimitive(Void.class);
//            reflect();
//            ioStream();
//            localDateTime();
//            objectMapperAndJwts();
//            pingJedis();
//            cotentEquals();
//            connectionDB();

//            String webIP = getWebIP("http://ip.chinaz.com/");
//            System.err.println(webIP);

//            pingRedis("10.211.55.3", 6379);

//            getIp();

//            readValueAsList();

            String userDir = System.getProperty("user.dir");
            System.err.println();
        } catch (Exception e) {
            System.err.println(e);
        }
    }

    private static void readValueAsList() throws IOException {
        ObjectNode nodes = new ObjectNode(JsonNodeFactory.instance);
        nodes.put("name", "23");
        nodes.put("age", "jack");
        nodes.put("sex", "man");

        nodes.get(0);
        List<UserDO> userDOS = new ArrayList<>();
        UserDO userDO = new UserDO();
        userDO.setName("first");
        userDOS.add(userDO);

        UserDO userDO1 = new UserDO();
        userDO1.setName("second");
        userDOS.add(userDO1);
        ObjectMapper mapper = new ObjectMapper();
        String content = mapper.writeValueAsString(userDOS);

        List<UserDO> userDOS1 = readValueAsList(content, UserDO.class);
        userDOS1.forEach(user -> System.err.println(user.getName()));
    }

    public static <B> List<B> readValueAsList(String content, Class<B> bean) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        JavaType javaType = mapper.getTypeFactory().constructParametricType(List.class, bean);
        return mapper.readValue(content, javaType);
    }

    private static void getIp() {
        HttpGet httpGet = null;
        CloseableHttpClient httpClients = null;
        CloseableHttpResponse execute = null;
        try {
            String uri = GET_IP_URL;
            httpGet = new HttpGet(uri);
            httpClients = HttpClients.createDefault();
            execute = httpClients.execute(httpGet);
            String content = null;
            String ip = null;
            if (HttpStatus.OK.value() == execute.getStatusLine().getStatusCode()) {
                content = EntityUtils.toString(execute.getEntity());
            }

            content = content == null ? null : content.replace("{", "").replace("}", "");
            ip = content == null ? null : content.split(",")[0].split(":")[1].replace("'", "");
            System.err.println(ip);
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            try {
                if (execute != null) {
                    execute.close();
                }
                if (httpClients != null) {
                    httpClients.close();
                }
                if (httpGet != null) {
                    httpGet.clone();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }

    public static Long delKey(String... keys) {
        Jedis jedis = getJedis();
        return jedis.del(keys);
    }

    public static void setRedisKey(String key, String value) {
        Jedis jedis = getJedis();
        jedis.set(key, value);
    }

    public static String getRedisValue(String key) {
        Jedis jedis = getJedis();
        return jedis.get(key);
    }

    public static Jedis getJedis() {
        Jedis jedis = new Jedis(REDIS_HOST, REDIS_PORT);
        return jedis;
    }

    public static void pingRedis(String host, int port) {
        Jedis jedis = new Jedis(host, port);
        String ping = jedis.ping();
        System.err.println(ping);
    }

    public static String getWebIP(String strUrl) {
        InputStream inputStream = null;
        try {
            //连接网页
            inputStream = new URL(strUrl).openConnection().getInputStream();
            BufferedReader br = new BufferedReader(new InputStreamReader(inputStream, "utf-8"));
            StringBuffer webContent = new StringBuffer();
            String str ;

            //读取网页信息
            while ((str = br.readLine()) != null) {
                webContent.append(str);
            }
            br.close();
            //获取网页中  当前 的 外网IP
            String ip = null;
            Matcher m = p.matcher(webContent.toString());
            if(m.find()){
                String ipstr = m.group(1);
                ip = ipstr;
            }
            return ip;
        } catch (Exception e) {
            e.printStackTrace();
            return "error open url:" + strUrl;
        } finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }


    private static void cotentEquals() {
        String s = "";
        String s1 = new String(s);
        s.equals(new StringBuilder(s).toString());
        boolean b = s.contentEquals(s1);
    }

    private static void pingJedis() {
        Jedis jedis = new Jedis("192.168.13.200", 6380);
        String ping = jedis.ping();
        System.err.println(ping);
    }

    private static void objectMapperAndJwts() throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        ObjectNode objectNode = objectMapper.createObjectNode();
        JwtBuilder jwtBuilder = Jwts.builder().setIssuer("hy").setSubject(objectNode.toString()).setExpiration(new Date()).signWith(SignatureAlgorithm.HS256, "hy-jwt-secert");
        String token = jwtBuilder.compact();
        System.err.println(token);

        UserDO userDO = new UserDO();
        userDO.setName("user_1");
        userDO.setDeptId("dept_1");
        String userJson = objectMapper.writeValueAsString(userDO);
        token = JwtUtils.createJWT(userJson, 100000);
        boolean verify = JwtUtils.verify(token);
        String token1 = JwtUtils.createJWT(userJson, -1);
        boolean verify1 = JwtUtils.verify(token1);

        String tokenContent = JwtUtils.getTokenContent(token1);
        UserDO userDO1 = objectMapper.readValue(tokenContent, UserDO.class);

        SttDO sttDO = new SttDO();
        sttDO.setTestTime(LocalDateTime.now());
        sttDO.setTestTime2(new Date());
        String json = objectMapper.writeValueAsString(sttDO);
    }

    private static void localDateTime() {
        //localDatetime转Date
        LocalDateTime parse = LocalDateTime.parse("2018-05-23T08:19:23.0");
        Date date = Date.from(parse.atZone(ZoneId.systemDefault()).toInstant());

        //Date转localDateTime
        LocalDateTime from = LocalDateTime.from(new Date().toInstant().atZone(ZoneId.systemDefault()));

        //localDateTime格式化
        LocalDateTime localDateTime = LocalDate.parse("2018-05-11").atTime(0, 0, 0);
        localDateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss:ms"));

        //解析时间字符串
        LocalDateTime start = LocalDateTime.parse("2018-05-01T00:00:00.0");
        LocalDateTime end = LocalDateTime.parse("2018-05-02T00:00:00.0");
        Duration between = Duration.between(start.atZone(ZoneId.systemDefault()).toInstant(), end.atZone(ZoneId.systemDefault()).toInstant());
        System.err.println(between.getSeconds());

        //当前周所在年份
        int weekOfYear = LocalDate.now().get(IsoFields.WEEK_BASED_YEAR);

        //当年第几周
        int week = LocalDate.now().get(IsoFields.WEEK_OF_WEEK_BASED_YEAR);

        //当月第几周
        int weekOfMonth = LocalDate.now().get(WeekFields.ISO.weekOfMonth());
        System.err.println(week + "-" + (weekOfYear == LocalDate.now().getYear()));
        System.err.println(LocalDate.now().lengthOfMonth());

        date = new Date();
        LocalDate now = LocalDate.from(date.toInstant().atZone(ZoneId.systemDefault()));
        LocalDateTime x = now.atStartOfDay();
        LocalDateTime max = LocalDateTime.MAX;
        System.err.println(LocalTime.MAX);
        long longTime = 1527491602694L;
        longTime = System.currentTimeMillis();
        Instant instant = Instant.ofEpochSecond(Duration.ofMillis(longTime).getSeconds());
        localDateTime = LocalDateTime.ofInstant(instant, ZoneId.systemDefault());
        System.err.println(localDateTime);


        ZoneId zoneId = ZoneId.of("GMT+6");
        date = new Date();
        LocalDateTime now1 = LocalDateTime.from(date.toInstant().atZone(ZoneId.systemDefault()));
        LocalDateTime now2 = LocalDateTime.from(date.toInstant().atZone(zoneId));
        boolean b = now1.equals(now2);


    }

    private static void ioStream() throws IOException {
        String pathname = "/Users/silent/project/demo/demo-start/src/main/resources/application.properties";
        InputStream inputStream = new FileInputStream(new File(pathname));
        Properties properties = new Properties();
        properties.load(inputStream);
        String str = properties.getProperty("spring.datasource.url");
        int i = Integer.parseInt(str);
        System.err.println(i);

        System.err.println(File.separator);
    }

    private static void reflect() throws NoSuchMethodException, InstantiationException, IllegalAccessException, InvocationTargetException {
        Class<DingApiService> dingApiServiceClass = DingApiService.class;
        dingApiServiceClass.getInterfaces();
        dingApiServiceClass.getSuperclass();
        Class<DingApiServiceImpl> dingApiServiceClass1 = DingApiServiceImpl.class;
        dingApiServiceClass1.getInterfaces();

        Constructor<DingApiServiceImpl> constructor = dingApiServiceClass1.getConstructor();
        //若是private构造，必须设置可访问
        constructor.setAccessible(true);
        constructor.newInstance();

        Method send = dingApiServiceClass1.getMethod("send", String.class, String.class);
//      send.invoke(dingApiServiceClass1.newInstance(), "http://ding", "msg");
        Field[] fields = dingApiServiceClass1.getDeclaredFields();
        for (Field field : fields) {
            Class<?> type = field.getType();
            System.err.println(String.format("type:%s", type.getSimpleName()));

            Type genericType = field.getGenericType();
            System.err.println(String.format("genericType: %s", genericType.getTypeName()));

            ParameterizedType parameterizedType = (ParameterizedType) genericType;
            Type[] actualTypeArguments = parameterizedType.getActualTypeArguments();
            for (Type actualTypeArgument : actualTypeArguments) {
                System.err.println(String.format("actualTypeArgument: %s", actualTypeArgument));
            }
            System.err.println("----------------------------");
        }
    }

    private static boolean isPrimitive(Class clazz) {
        //是否基本数据类型
        return clazz.isPrimitive();
    }

    private static void listAndQueue() {
        List<String> list = new ArrayList<>();
        list.add("1");
        list.add("11");
        list.add("12");
        list.addAll(list.size()-1, Arrays.asList("add1", "add2"));
        list = list.subList(0, list.size());
        System.err.println(list);

        List<Object> objects1 = Collections.emptyList();
//        objects1.add(123);
        Queue<Object> objects = QueueUtils.emptyQueue();
//        objects.add(123);
    }

    private static void threadTest() {
        new Thread(new ThreadTest(), "one").start();
        new Thread(new ThreadTest(), "two").start();
        new Thread(new ThreadTest(), "three").start();
        new Thread(new ThreadTest(), "four").start();
    }

    private static void stringBuffer() {
        StringBuffer buffer = new StringBuffer();
        buffer.append("123");
        buffer.append("to be a better man");
        buffer.insert(buffer.length()-2, "first-");
        buffer.delete(buffer.length() - 7, buffer.length() - 1);
        System.err.println(buffer);
    }

    private static void inetAddress() throws UnknownHostException {
        String hostAddress = InetAddress.getLocalHost().getHostAddress();
        System.err.println(hostAddress);
    }

    private static void subString() {
        String str = "WB282733";
        str.substring(0);
        str.substring(1);
        str.substring(2);
    }

    private static void stringFormat() {
        Date now = new Date();

        //6位数字，不足前面补0
        String format = String.format("%06d", 99);

        //如果位负数，加括号
        format = String.format("%+(,d", 99 - 100);

        //string的日期格式化 yyyy-MM-dd HH:mm:ss
        format = String.format("%tF %tT", now, now);
        System.err.println(format);
    }

    private static void math() {
        //integer最大值
        int maxValue = Integer.MAX_VALUE;

        //2的31幂次方
        double v = Math.pow(2, 31) - 1;
        boolean b = maxValue == v;
    }

    private static void properties() {
        Properties properties = System.getProperties();
        System.err.println(System.getProperty("user.name"));
        System.err.println(System.getProperty("user.host"));
    }

    public static void connectionDB() {
        Connection connection;
        try {
            String driver =  "com.mysql.jdbc.Driver";
            String url = "jdbc:mysql://localhost:3306/information_schema";
            String user = "root";
            String password = "gagwamg0417!";

            Class.forName(driver);
            connection = DriverManager.getConnection(url, user, password);
            Statement statement = connection.createStatement();
            
            String sql = "select table_name from tables where table_schema = 'test_ningbo'";
            ResultSet resultSet = statement.executeQuery(sql);
            System.err.println();
        } catch (Exception e) {
            System.err.println(e);
        }
    }



    public static ArrayNode httpSend(String uri, Map<String, String> params) throws Exception {
        String addParams = addParams(params);
        if (addParams != null) {
            uri = uri.concat("?").concat(addParams);
        }
        HttpGet httpGet = new HttpGet(uri);
        CloseableHttpClient closeableHttpClient = HttpClients.createDefault();
        CloseableHttpResponse response = null;
        ArrayNode data = new ObjectMapper().createArrayNode();
        try {
            response = closeableHttpClient.execute(httpGet);
            String entity = EntityUtils.toString(response.getEntity());
            data = (ArrayNode) new ObjectMapper().readValue(entity, ObjectNode.class).get("data");

        } catch (Exception e) {
            throw new Exception(e);
        } finally {
            response.close();
        }
        return data;
    }

    private static String addParams(Map<String, String> params) {
        if (MapUtils.isEmpty(params)) {
            return null;
        }
        StringBuilder builder = new StringBuilder();
        params.forEach((key, value) -> {
            builder.append("&");
            builder.append(String.valueOf(key)).append("=").append(String.valueOf(value));
        });
        return builder.substring(1);
    }
}
