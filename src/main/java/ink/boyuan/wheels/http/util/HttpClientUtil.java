package ink.boyuan.wheels.http.util;

import ink.boyuan.wheels.base.BaseUtil;
import ink.boyuan.wheels.http.constant.HttpClientConstant;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.*;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.net.URI;
import java.net.URLEncoder;
import java.util.*;

/**
 * @author wyy
 * @version 1.0
 * @Classname HttpClientUtil
 * @date 2020/7/17 8:21
 * @description 调用第三方服务使用Http工具
 **/
public class HttpClientUtil implements BaseUtil {


    private static Logger LOGGER = LoggerFactory.getLogger(HttpClientUtil.class);
    /**
     * 封装HTTP POST方法
     * @author wyy
     * @param url 请求路径
     * @param paramMap 请求体
     * @return
     * @throws Exception
     */
    public static String post(String url, Map<String, String> paramMap) throws Exception {
        HttpClient httpClient=null;
        try {
             httpClient = HttpClients.createDefault();
        }catch (Exception e){
            LOGGER.error("", e);
        }

        HttpPost httpPost = new HttpPost(url);
        //设置请求和传输超时时间
        RequestConfig requestConfig = RequestConfig.custom().setSocketTimeout(60000).setConnectTimeout(60000).build();
        httpPost.setConfig(requestConfig);
        List<NameValuePair> formParams = setHttpParams(paramMap);
        UrlEncodedFormEntity param = new UrlEncodedFormEntity(formParams, "UTF-8");
        httpPost.setEntity(param);
        HttpResponse response = Objects.requireNonNull(httpClient).execute(httpPost);

        String httpEntityContent = getHttpEntityContent(response);

        httpPost.abort();

        return httpEntityContent;

    }

    /**
     * 封装HTTP POST方法
     * @author wyy
     * @param   url 请求路径
     * @param （如JSON串）
     * @return
     * @throws Exception
     */
    public static String post(String url, String data) throws Exception {
        HttpClient httpClient=null;
        try {
             httpClient = HttpClients.createDefault();
        }catch (Exception e){
            LOGGER.error("", e);
        }
        HttpPost httpPost = new HttpPost(url);
        //设置请求和传输超时时间
        RequestConfig requestConfig = RequestConfig.custom().setSocketTimeout(60000).setConnectTimeout(60000).build();
        httpPost.setConfig(requestConfig);
        httpPost.setHeader("Content-Type", "text/json; charset=utf-8");
        httpPost.setEntity(new StringEntity(URLEncoder.encode(data, "UTF-8")));
        HttpResponse response = Objects.requireNonNull(httpClient).execute(httpPost);
        String httpEntityContent = getHttpEntityContent(response);
        httpPost.abort();
        return httpEntityContent;
    }

    /**
     * 封装HTTP GET方法
     *
     * @param  url 请求路径
     * @return  String
     * @throws Exception
     */
    public static String get(String url) throws Exception {
        HttpClient httpClient=null;
        try {
             httpClient = HttpClients.createDefault();
        }catch (Exception e){
            LOGGER.error("", e);
        }
        HttpGet httpGet = new HttpGet();
        //设置请求和传输超时时间
        RequestConfig requestConfig = RequestConfig.custom().setSocketTimeout(60000).setConnectTimeout(60000).build();
        httpGet.setConfig(requestConfig);
        httpGet.setURI(URI.create(url));
        HttpResponse response = Objects.requireNonNull(httpClient).execute(httpGet);
        String httpEntityContent = getHttpEntityContent(response);
        httpGet.abort();
        return httpEntityContent;
    }

    /**
     * 封装HTTP GET方法
     *
     * @param  url 请求路径
     * @param paramMap
     * @return  String
     * @throws Exception
     */
    public static String get(String url, Map<String, String> paramMap) throws Exception {
        HttpClient httpClient = HttpClients.createDefault();
        HttpGet httpGet = new HttpGet();
        //设置请求和传输超时时间
        RequestConfig requestConfig = RequestConfig.custom().setSocketTimeout(60000).setConnectTimeout(60000).build();
        httpGet.setConfig(requestConfig);
        List<NameValuePair> formparams = setHttpParams(paramMap);
        String param = URLEncodedUtils.format(formparams, "UTF-8");
        httpGet.setURI(URI.create(url + "?" + param));
        HttpResponse response = httpClient.execute(httpGet);
        String httpEntityContent = getHttpEntityContent(response);
        httpGet.abort();
        return httpEntityContent;
    }

    /**
     * 封装HTTP PUT方法
     *
     * @param  url 请求路径
     * @param  paramMap
     * @return String
     * @throws Exception
     */
    public static String put(String url, Map<String, String> paramMap) throws Exception {
        HttpClient httpClient=null;
        try {
             httpClient = HttpClients.createDefault();
        }catch (Exception e){
            LOGGER.error("", e);
        }
        HttpPut httpPut = new HttpPut(url);
        //设置请求和传输超时时间
        RequestConfig requestConfig = RequestConfig.custom().setSocketTimeout(60000).setConnectTimeout(60000).build();
        httpPut.setConfig(requestConfig);
        List<NameValuePair> formparams = setHttpParams(paramMap);
        UrlEncodedFormEntity param = new UrlEncodedFormEntity(formparams, "UTF-8");
        httpPut.setEntity(param);
        HttpResponse response = Objects.requireNonNull(httpClient).execute(httpPut);
        String httpEntityContent = getHttpEntityContent(response);
        httpPut.abort();
        return httpEntityContent;
    }

    /**
     * 封装HTTP DELETE方法
     *
     * @param url 请求路径
     * @return   String
     * @throws Exception
     */
    public static String delete(String url) throws Exception {
        HttpClient httpClient=null;
        try {
             httpClient = HttpClients.createDefault();
        }catch (Exception e){
            LOGGER.error("", e);
        }

        HttpDelete httpDelete = new HttpDelete();
        //设置请求和传输超时时间
        RequestConfig requestConfig = RequestConfig.custom().setSocketTimeout(60000).setConnectTimeout(60000).build();
        httpDelete.setConfig(requestConfig);
        httpDelete.setURI(URI.create(url));
        HttpResponse response = Objects.requireNonNull(httpClient).execute(httpDelete);
        String httpEntityContent = getHttpEntityContent(response);
        httpDelete.abort();
        return httpEntityContent;
    }

    /**
     * 封装HTTP DELETE方法
     *
     * @param  url 请求路径
     * @param  paramMap
     * @return  String

     */
    public static String delete(String url, Map<String, String> paramMap) throws Exception {
        HttpClient httpClient=null;
        try {
             httpClient = HttpClients.createDefault();
        }catch (Exception e){
            LOGGER.error("", e);
        }
        HttpDelete httpDelete = new HttpDelete();
        //设置请求和传输超时时间
        RequestConfig requestConfig = RequestConfig.custom().setSocketTimeout(60000).setConnectTimeout(60000).build();
        httpDelete.setConfig(requestConfig);
        List<NameValuePair> formparams = setHttpParams(paramMap);
        String param = URLEncodedUtils.format(formparams, "UTF-8");
        httpDelete.setURI(URI.create(url + "?" + param));
        HttpResponse response = Objects.requireNonNull(httpClient).execute(httpDelete);
        String httpEntityContent = getHttpEntityContent(response);
        httpDelete.abort();
        return httpEntityContent;
    }

    /**
     * 不带参数的get请求
     *
     * @param url 请求路径
     * @param token  请求权限或者签名
     * @return String
     */
    public static String doGet(String url, String token, String timeSpan) {
        return doGet(url, token,timeSpan,null);
    }


    /**
     * 带参的get请求
     * @param url 请求路径
     * @param token 权限或者签名
     * @param timeSpan  时间戳
     * @param o
     * @return
     */
    public static String doGet(String url,String token,String timeSpan,Object o) {
        // 创建Httpclient对象
        CloseableHttpClient httpclient=null;
        try {
             httpclient = HttpClients.createDefault();
        }catch (Exception e){
            LOGGER.error("", e);
        }

        String resultString = "";
        CloseableHttpResponse response = null;
        try {
            // 创建uri
            URIBuilder builder = new URIBuilder(url);
            URI uri = builder.build();
            // 创建http GET请求
            HttpGet httpGet = new HttpGet(uri);
            //添加header 头
            httpGet.setHeader("Token",token);
            httpGet.setHeader("Timespan",timeSpan);
            // 执行请求
            response = Objects.requireNonNull(httpclient).execute(httpGet);
            // 判断返回状态是否为200
            if (response.getStatusLine().getStatusCode() == HttpClientConstant.STATUS_CODE_ACCESS) {
                resultString = EntityUtils.toString(response.getEntity(), "UTF-8");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (response != null) {
                    response.close();
                }
                Objects.requireNonNull(httpclient).close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return resultString;
    }


    /**
     * 设置请求参数
     *
     * @param  paramMap
     * @return  List<NameValuePair>
     */
    private static List<NameValuePair> setHttpParams(Map<String, String> paramMap) {
        List<NameValuePair> formParams = new ArrayList<NameValuePair>();
        Set<Map.Entry<String, String>> set = paramMap.entrySet();
        for (Map.Entry<String, String> entry : set) {
            formParams.add(new BasicNameValuePair(entry.getKey(), entry.getValue()));
        }
        return formParams;
    }



    /**
     * 获得响应HTTP实体内容
     *
     * @param response
     * @return  String
     * @throws Exception
     */
    private static String getHttpEntityContent(HttpResponse response) throws Exception {
        HttpEntity entity = response.getEntity();
        if (entity != null) {
            InputStream is = entity.getContent();
            BufferedReader br = new BufferedReader(new InputStreamReader(is, "UTF-8"));
            String line = br.readLine();
            StringBuilder sb = new StringBuilder();
            while (line != null) {
                sb.append(line).append("\n");
                line = br.readLine();
            }
            return sb.toString();
        }
        return "";
    }
}
