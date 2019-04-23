package com.sy.utils;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

public class HttpClientUtil {
	
	public static String httpGet(String urlParam, Map<String, Object> params, String charset) {
		 StringBuffer sbParams = new StringBuffer();  
		 sbParams.append("?");  
	        if (params != null && params.size() > 0) {  
	            for (Entry<String, Object> entry : params.entrySet()) {  
	                sbParams.append(entry.getKey());  
	                sbParams.append("=");  
	                sbParams.append(entry.getValue());  
	                sbParams.append("&");  
	            }  
	        }  
		// 创建 HttpClient 客户端
        CloseableHttpClient httpClient = HttpClients.createDefault();
        // 创建 HttpGet 请求
        HttpGet httpGet = new HttpGet(urlParam+ sbParams.substring(0, sbParams.length() - 1));
        // 设置长连接
        httpGet.setHeader("Connection", "keep-alive");
        // 设置代理（模拟浏览器版本）
        httpGet.setHeader("User-Agent", "Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/63.0.3239.132 Safari/537.36");
        // 设置 Cookie
        //httpGet.setHeader("Cookie", "UM_distinctid=16442706a09352-0376059833914f-3c604504-1fa400-16442706a0b345; CNZZDATA1262458286=1603637673-1530123020-%7C1530123020; JSESSIONID=805587506F1594AE02DC45845A7216A4");
        CloseableHttpResponse httpResponse = null;
        String response = null;
        try {
            // 请求并获得响应结果
            httpResponse = httpClient.execute(httpGet);
            HttpEntity httpEntity = httpResponse.getEntity();
            // 输出请求结果
            response=EntityUtils.toString(httpEntity);
        } catch (IOException e) {
            e.printStackTrace();
        }

        // 无论如何必须关闭连接
        finally {
            if (httpResponse != null) {
                try {
                    httpResponse.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (httpClient != null) {
                try {
                    httpClient.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return response;
    }

	public static String httpPost(String urlParam, Map<String, Object> params, String charset) {
        // 创建 HttpClient 客户端
        CloseableHttpClient httpClient = HttpClients.createDefault();
        // 创建 HttpPost 请求
        HttpPost httpPost = new HttpPost(urlParam);
        // 设置长连接
        httpPost.setHeader("Connection", "keep-alive");
        // 设置代理（模拟浏览器版本）
        httpPost.setHeader("User-Agent", "Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/63.0.3239.132 Safari/537.36");
        // 设置 Cookie
        //httpPost.setHeader("Cookie", "UM_distinctid=16442706a09352-0376059833914f-3c604504-1fa400-16442706a0b345; CNZZDATA1262458286=1603637673-1530123020-%7C1530123020; JSESSIONID=805587506F1594AE02DC45845A7216A4");
        // 创建 HttpPost 参数
        List<BasicNameValuePair> paramsList = new ArrayList<BasicNameValuePair>();
        for (Entry<String, Object> entry : params.entrySet()) {
        	paramsList.add(new BasicNameValuePair(entry.getKey(),String.valueOf(entry.getValue()) ));
		}
        CloseableHttpResponse httpResponse = null;
        String response = null;
        if (charset==null) {
        	charset="UTF-8";
		}
        try {
            // 设置 HttpPost 参数
            httpPost.setEntity(new UrlEncodedFormEntity(paramsList, charset));
            httpResponse = httpClient.execute(httpPost);
            HttpEntity httpEntity = httpResponse.getEntity();
            // 输出请求结果
            response=EntityUtils.toString(httpEntity);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        // 无论如何必须关闭连接
        finally {
            try {
                if (httpResponse != null) {
                    httpResponse.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                if (httpClient != null) {
                    httpClient.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
		return response;
	}
}
