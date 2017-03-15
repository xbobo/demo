package com.demo.common.util;

import com.alibaba.fastjson.JSONObject;
import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.NameValuePair;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/3/15.
 */
public class HttpSender {
    private static Logger logger = LoggerFactory.getLogger(HttpSender.class);
    /**
     *
     * @param url
     *            应用地址，类似于http://ip:port/msg/
     * @param un
     *            账号
     * @param pw
     *            密码
     * @param phone
     *            手机号码，多个号码使用","分割
     * @param msg
     *            短信内容
     * @param rd
     *            是否需要状态报告，需要1，不需要0
     * @return 返回值定义参见HTTP协议文档
     * @throws Exception
     */
    public static String batchSend(String url, String un, String pw, String phone, String msg, String rd, String ex)
            throws Exception {
        JSONObject jsonObject = new JSONObject();
        HttpClientBuilder httpClientBuilder = HttpClientBuilder.create();
        CloseableHttpClient closeableHttpClient = httpClientBuilder.build();
        // 依次是目标请求地址，端口号,协议类型
        HttpHost target = new HttpHost(url, 80, "http");
        // 依次是代理地址，代理端口号，协议类型
        HttpHost proxy = new HttpHost("10.25.59.84", 3128, "http");
        RequestConfig config = RequestConfig.custom().setProxy(proxy).build();
        // 请求地址
        HttpPost httpPost = new HttpPost(url);
        httpPost.setConfig(config);
        // 创建参数队列
        List<NameValuePair> formparams = new ArrayList<NameValuePair>();
        formparams.add(new BasicNameValuePair("un", un));
        formparams.add(new BasicNameValuePair("pw", pw));
        formparams.add(new BasicNameValuePair("phone", phone));
        formparams.add(new BasicNameValuePair("rd", rd));
        formparams.add(new BasicNameValuePair("msg", msg));
        formparams.add(new BasicNameValuePair("ex", ex));
        UrlEncodedFormEntity entity;
        try {
            entity = new UrlEncodedFormEntity((List<? extends org.apache.http.NameValuePair>) formparams, "UTF-8");
            httpPost.setEntity(entity);
            CloseableHttpResponse response = closeableHttpClient.execute(target, httpPost);
            HttpEntity httpEntity = response.getEntity();
            if (httpEntity != null) {
                // 打印响应内容
                logger.info("response:" + jsonObject.toJSONString());
                return EntityUtils.toString(httpEntity, "UTF-8");
            }
            // 释放资源
            closeableHttpClient.close();
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("response:" + jsonObject.toJSONString(), e);
        }
        return null;
    }
}
