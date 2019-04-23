package com.sy.utils;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.Socket;
import java.net.URL;
import java.net.URLConnection;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import com.alibaba.fastjson.JSONObject;

/** 
 * @Description:发送http请求帮助类 
 * @author:liuyc 
 * @time:2016年5月17日 下午3:25:32 
 */  
public class HttpUtil {  
    /** 
     * @Description:使用HttpURLConnection发送post请求 
     * @author:liuyc 
     * @time:2016年5月17日 下午3:26:07 
     */  
    public static String sendPost(String urlParam, Map<String, Object> params, String charset) {  
        StringBuffer resultBuffer = null;  
        // 构建请求参数  
        StringBuffer sbParams = new StringBuffer();  
        if (params != null && params.size() > 0) {  
            for (Entry<String, Object> e : params.entrySet()) {  
                sbParams.append(e.getKey());  
                sbParams.append("=");  
                sbParams.append(e.getValue());  
                sbParams.append("&");  
            }  
        }  
        HttpURLConnection con = null;  
        OutputStreamWriter osw = null;  
        BufferedReader br = null;  
        // 发送请求  
        try {  
            URL url = new URL(urlParam);  
            con = (HttpURLConnection) url.openConnection();  
            con.setRequestMethod("POST");  
            con.setDoOutput(true);  
            con.setDoInput(true);  
            con.setUseCaches(false);  
            con.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");  
            if (sbParams != null && sbParams.length() > 0) {  
                osw = new OutputStreamWriter(con.getOutputStream(), charset);  
                osw.write(sbParams.substring(0, sbParams.length() - 1));  
                osw.flush();  
            }  
            // 读取返回内容  
            resultBuffer = new StringBuffer();  
            int contentLength = Integer.parseInt(con.getHeaderField("Content-Length"));  
            if (contentLength > 0) {  
                br = new BufferedReader(new InputStreamReader(con.getInputStream(), charset));  
                String temp;  
                while ((temp = br.readLine()) != null) {  
                    resultBuffer.append(temp);  
                }  
            }  
        } catch (Exception e) {  
            throw new RuntimeException(e);  
        } finally {  
            if (osw != null) {  
                try {  
                    osw.close();  
                } catch (IOException e) {  
                    osw = null;  
                    throw new RuntimeException(e);  
                } finally {  
                    if (con != null) {  
                        con.disconnect();  
                        con = null;  
                    }  
                }  
            }  
            if (br != null) {  
                try {  
                    br.close();  
                } catch (IOException e) {  
                    br = null;  
                    throw new RuntimeException(e);  
                } finally {  
                    if (con != null) {  
                        con.disconnect();  
                        con = null;  
                    }  
                }  
            }  
        }  
        return resultBuffer.toString();  
    }  
  
    /** 
     * @Description:使用URLConnection发送post 
     * @author:liuyc 
     * @time:2016年5月17日 下午3:26:52 
     */  
    public static String sendPost2(String urlParam, Map<String, Object> params, String charset) {  
        StringBuffer resultBuffer = null;  
        // 构建请求参数  
        StringBuffer sbParams = new StringBuffer();  
        if (params != null && params.size() > 0) {  
            for (Entry<String, Object> e : params.entrySet()) {  
                sbParams.append(e.getKey());  
                sbParams.append("=");  
                sbParams.append(e.getValue());  
                sbParams.append("&");  
            }  
        }  
        URLConnection con = null;  
        OutputStreamWriter osw = null;  
        BufferedReader br = null;  
        try {  
            URL realUrl = new URL(urlParam);  
            // 打开和URL之间的连接  
            con = realUrl.openConnection();  
            // 设置通用的请求属性  
            con.setRequestProperty("accept", "*/*");  
            con.setRequestProperty("connection", "Keep-Alive");  
            con.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");  
            con.setRequestProperty("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");  
            // 发送POST请求必须设置如下两行  
            con.setDoOutput(true);  
            con.setDoInput(true);  
            // 获取URLConnection对象对应的输出流  
            osw = new OutputStreamWriter(con.getOutputStream(), charset);  
            if (sbParams != null && sbParams.length() > 0) {  
                // 发送请求参数  
                osw.write(sbParams.substring(0, sbParams.length() - 1));  
                // flush输出流的缓冲  
                osw.flush();  
            }  
            // 定义BufferedReader输入流来读取URL的响应  
            resultBuffer = new StringBuffer();  
            int contentLength = Integer.parseInt(con.getHeaderField("Content-Length"));  
            if (contentLength > 0) {  
                br = new BufferedReader(new InputStreamReader(con.getInputStream(), charset));  
                String temp;  
                while ((temp = br.readLine()) != null) {  
                    resultBuffer.append(temp);  
                }  
            }  
        } catch (Exception e) {  
            throw new RuntimeException(e);  
        } finally {  
            if (osw != null) {  
                try {  
                    osw.close();  
                } catch (IOException e) {  
                    osw = null;  
                    throw new RuntimeException(e);  
                }  
            }  
            if (br != null) {  
                try {  
                    br.close();  
                } catch (IOException e) {  
                    br = null;  
                    throw new RuntimeException(e);  
                }  
            }  
        }  
        return resultBuffer.toString();  
    }  
    /**
     * 向指定 URL 发送POST方法的请求(测试过可用)
     * 
     * @param url
     *            发送请求的 URL
     * @param param
     *            请求参数，请求参数应该是 name1=value1&name2=value2 的形式。
     * @return 所代表远程资源的响应结果
     */
    public static String sendPost3(String url,Map<String, Object> params, String charset) {
        PrintWriter out = null;
        BufferedReader in = null;
        String result = "";
        // 构建请求参数  
        StringBuffer sbParams = new StringBuffer();  
        if (params != null && params.size() > 0) {  
            for (Entry<String, Object> e : params.entrySet()) {  
                sbParams.append(e.getKey());  
                sbParams.append("=");  
                sbParams.append(e.getValue());  
                sbParams.append("&");  
            }  
        }  
        try {
            URL realUrl = new URL(url);
            // 打开和URL之间的连接
            URLConnection conn = realUrl.openConnection();
            // 设置通用的请求属性
            conn.setRequestProperty("accept", "*/*");
            conn.setRequestProperty("connection", "Keep-Alive");
            conn.setRequestProperty("user-agent",
                    "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
            // 发送POST请求必须设置如下两行
            conn.setDoOutput(true);
            conn.setDoInput(true);
            // 获取URLConnection对象对应的输出流
            out = new PrintWriter(conn.getOutputStream());
            // 发送请求参数
            out.print(sbParams.toString());
            // flush输出流的缓冲
            out.flush();
            // 定义BufferedReader输入流来读取URL的响应
            in = new BufferedReader(
                    new InputStreamReader(conn.getInputStream()));
            String line;
            while ((line = in.readLine()) != null) {
                result += line;
            }
        } catch (Exception e) {
            System.out.println("发送 POST 请求出现异常！"+e);
            e.printStackTrace();
        }
        //使用finally块来关闭输出流、输入流
        finally{
            try{
                if(out!=null){
                    out.close();
                }
                if(in!=null){
                    in.close();
                }
            }
            catch(IOException ex){
                ex.printStackTrace();
            }
        }
        return result;
    }    

    /**  
     * @Description:发送get请求保存下载文件  
     * @author:liuyc  
     * @time:2016年5月17日 下午3:27:29  
     */  
    public static void sendGetAndSaveFile(String urlParam, Map<String, Object> params, String fileSavePath) {  
        // 构建请求参数  
        StringBuffer sbParams = new StringBuffer();  
        if (params != null && params.size() > 0) {  
            for (Entry<String, Object> entry : params.entrySet()) {  
                sbParams.append(entry.getKey());  
                sbParams.append("=");  
                sbParams.append(entry.getValue());  
                sbParams.append("&");  
            }  
        }  
        HttpURLConnection con = null;  
        BufferedReader br = null;  
        FileOutputStream os = null;  
        try {  
            URL url = null;  
            if (sbParams != null && sbParams.length() > 0) {  
                url = new URL(urlParam + "?" + sbParams.substring(0, sbParams.length() - 1));  
            } else {  
                url = new URL(urlParam);  
            }  
            con = (HttpURLConnection) url.openConnection();  
            con.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");  
            con.connect();  
            InputStream is = con.getInputStream();  
            os = new FileOutputStream(fileSavePath);  
            byte buf[] = new byte[1024];  
            int count = 0;  
            while ((count = is.read(buf)) != -1) {  
                os.write(buf, 0, count);  
            }  
            os.flush();  
        } catch (Exception e) {  
            throw new RuntimeException(e);  
        } finally {  
            if (os != null) {  
                try {  
                    os.close();  
                } catch (IOException e) {  
                    os = null;  
                    throw new RuntimeException(e);  
                } finally {  
                    if (con != null) {  
                        con.disconnect();  
                        con = null;  
                    }  
                }  
            }  
            if (br != null) {  
                try {  
                    br.close();  
                } catch (IOException e) {  
                    br = null;  
                    throw new RuntimeException(e);  
                } finally {  
                    if (con != null) {  
                        con.disconnect();  
                        con = null;  
                    }  
                }  
            }  
        }  
    }  
  
    /** 
     * @Description:使用HttpURLConnection发送get请求 
     * @author:liuyc 
     * @time:2016年5月17日 下午3:27:29 
     */  
    public static String sendGet(String urlParam, Map<String, Object> params, String charset) {  
        StringBuffer resultBuffer = null;  
        // 构建请求参数  
        StringBuffer sbParams = new StringBuffer();  
        if (params != null && params.size() > 0) {  
            for (Entry<String, Object> entry : params.entrySet()) {  
                sbParams.append(entry.getKey());  
                sbParams.append("=");  
                sbParams.append(entry.getValue());  
                sbParams.append("&");  
            }  
        }  
        HttpURLConnection con = null;  
        BufferedReader br = null;  
        try {  
            URL url = null;  
            if (sbParams != null && sbParams.length() > 0) {  
                url = new URL(urlParam + "?" + sbParams.substring(0, sbParams.length() - 1));  
            } else {  
                url = new URL(urlParam);  
            }  
            con = (HttpURLConnection) url.openConnection();  
            con.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");  
            con.connect();  
            resultBuffer = new StringBuffer();  
            br = new BufferedReader(new InputStreamReader(con.getInputStream(), charset));  
            String temp;  
            while ((temp = br.readLine()) != null) {  
                resultBuffer.append(temp);  
            }  
        } catch (Exception e) {  
            throw new RuntimeException(e);  
        } finally {  
            if (br != null) {  
                try {  
                    br.close();  
                } catch (IOException e) {  
                    br = null;  
                    throw new RuntimeException(e);  
                } finally {  
                    if (con != null) {  
                        con.disconnect();  
                        con = null;  
                    }  
                }  
            }  
        }  
        return resultBuffer.toString();  
    }  
  
    /** 
     * @Description:使用URLConnection发送get请求 
     * @author:liuyc 
     * @time:2016年5月17日 下午3:27:58 
     */  
    public static String sendGet2(String urlParam, Map<String, Object> params, String charset) {  
        StringBuffer resultBuffer = null;  
        // 构建请求参数  
        StringBuffer sbParams = new StringBuffer();  
        if (params != null && params.size() > 0) {  
            for (Entry<String, Object> entry : params.entrySet()) {  
                sbParams.append(entry.getKey());  
                sbParams.append("=");  
                sbParams.append(entry.getValue());  
                sbParams.append("&");  
            }  
        }  
        BufferedReader br = null;  
        try {  
            URL url = null;  
            if (sbParams != null && sbParams.length() > 0) {  
                url = new URL(urlParam + "?" + sbParams.substring(0, sbParams.length() - 1));  
            } else {  
                url = new URL(urlParam);  
            }  
           // System.out.println(url);
            URLConnection con = url.openConnection();  
            // 设置请求属性  
            con.setRequestProperty("accept", "*/*");  
            con.setRequestProperty("connection", "Keep-Alive");  
            con.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");  
            con.setRequestProperty("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");  
            // 建立连接  
            con.connect();  
            resultBuffer = new StringBuffer();  
            br = new BufferedReader(new InputStreamReader(con.getInputStream(), charset));  
            String temp;  
            while ((temp = br.readLine()) != null) {  
                resultBuffer.append(temp);  
            }  
        } catch (Exception e) {  
            throw new RuntimeException(e);  
        } finally {  
            if (br != null) {  
                try {  
                    br.close();  
                } catch (IOException e) {  
                    br = null;  
                    throw new RuntimeException(e);  
                }  
            }  
        }  
        return resultBuffer.toString();  
    }  
  
  
    /** 
     * @Description:使用socket发送post请求 
     * @author:liuyc 
     * @time:2016年5月18日 上午9:26:22 
     */  
    public static String sendSocketPost(String urlParam, Map<String, Object> params, String charset) {  
        String result = "";  
        // 构建请求参数  
        StringBuffer sbParams = new StringBuffer();  
        if (params != null && params.size() > 0) {  
            for (Entry<String, Object> entry : params.entrySet()) {  
                sbParams.append(entry.getKey());  
                sbParams.append("=");  
                sbParams.append(entry.getValue());  
                sbParams.append("&");  
            }  
        }  
        Socket socket = null;  
        OutputStreamWriter osw = null;  
        InputStream is = null;  
        try {  
            URL url = new URL(urlParam);  
            String host = url.getHost();  
            int port = url.getPort();  
            if (-1 == port) {  
                port = 80;  
            }  
            String path = url.getPath();  
            socket = new Socket(host, port);  
            StringBuffer sb = new StringBuffer();  
            sb.append("POST " + path + " HTTP/1.1\r\n");  
            sb.append("Host: " + host + "\r\n");  
            sb.append("Connection: Keep-Alive\r\n");  
            sb.append("Content-Type: application/x-www-form-urlencoded; charset=utf-8 \r\n");  
            sb.append("Content-Length: ").append(sb.toString().getBytes().length).append("\r\n");  
            // 这里一个回车换行，表示消息头写完，不然服务器会继续等待  
            sb.append("\r\n");  
            if (sbParams != null && sbParams.length() > 0) {  
                sb.append(sbParams.substring(0, sbParams.length() - 1));  
            }  
            osw = new OutputStreamWriter(socket.getOutputStream());  
            osw.write(sb.toString());  
            osw.flush();  
            is = socket.getInputStream();  
            String line = null;  
            // 服务器响应体数据长度  
            int contentLength = 0;  
            // 读取http响应头部信息  
            do {  
                line = readLine(is, 0, charset);  
                if (line.startsWith("Content-Length")) {  
                    // 拿到响应体内容长度  
                    contentLength = Integer.parseInt(line.split(":")[1].trim());  
                }  
                // 如果遇到了一个单独的回车换行，则表示请求头结束  
            } while (!line.equals("\r\n"));  
            // 读取出响应体数据（就是你要的数据）  
            result = readLine(is, contentLength, charset);  
        } catch (Exception e) {  
            throw new RuntimeException(e);  
        } finally {  
            if (osw != null) {  
                try {  
                    osw.close();  
                } catch (IOException e) {  
                    osw = null;  
                    throw new RuntimeException(e);  
                } finally {  
                    if (socket != null) {  
                        try {  
                            socket.close();  
                        } catch (IOException e) {  
                            socket = null;  
                            throw new RuntimeException(e);  
                        }  
                    }  
                }  
            }  
            if (is != null) {  
                try {  
                    is.close();  
                } catch (IOException e) {  
                    is = null;  
                    throw new RuntimeException(e);  
                } finally {  
                    if (socket != null) {  
                        try {  
                            socket.close();  
                        } catch (IOException e) {  
                            socket = null;  
                            throw new RuntimeException(e);  
                        }  
                    }  
                }  
            }  
        }  
        return result;  
    }  
  
    /** 
     * @Description:使用socket发送get请求 
     * @author:liuyc 
     * @time:2016年5月18日 上午9:27:18 
     */  
    public static String sendSocketGet(String urlParam, Map<String, Object> params, String charset) {  
        String result = "";  
        // 构建请求参数  
        StringBuffer sbParams = new StringBuffer();  
        if (params != null && params.size() > 0) {  
            for (Entry<String, Object> entry : params.entrySet()) {  
                sbParams.append(entry.getKey());  
                sbParams.append("=");  
                sbParams.append(entry.getValue());  
                sbParams.append("&");  
            }  
        }  
        Socket socket = null;  
        OutputStreamWriter osw = null;  
        InputStream is = null;  
        try {  
            URL url = new URL(urlParam);  
            String host = url.getHost();  
            int port = url.getPort();  
            if (-1 == port) {  
                port = 80;  
            }  
            String path = url.getPath();  
            socket = new Socket(host, port);  
            StringBuffer sb = new StringBuffer();  
            sb.append("GET " + path + " HTTP/1.1\r\n");  
            sb.append("Host: " + host + "\r\n");  
            sb.append("Connection: Keep-Alive\r\n");  
            sb.append("Content-Type: application/x-www-form-urlencoded; charset=utf-8 \r\n");  
            sb.append("Content-Length: ").append(sb.toString().getBytes().length).append("\r\n");  
            // 这里一个回车换行，表示消息头写完，不然服务器会继续等待  
            sb.append("\r\n");  
            if (sbParams != null && sbParams.length() > 0) {  
                sb.append(sbParams.substring(0, sbParams.length() - 1));  
            }  
            osw = new OutputStreamWriter(socket.getOutputStream());  
            osw.write(sb.toString());  
            osw.flush();  
            is = socket.getInputStream();  
            String line = null;  
            // 服务器响应体数据长度  
            int contentLength = 0;  
            // 读取http响应头部信息  
            do {  
                line = readLine(is, 0, charset);  
                if (line.startsWith("Content-Length")) {  
                    // 拿到响应体内容长度  
                    contentLength = Integer.parseInt(line.split(":")[1].trim());  
                }  
                // 如果遇到了一个单独的回车换行，则表示请求头结束  
            } while (!line.equals("\r\n"));  
            // 读取出响应体数据（就是你要的数据）  
            result = readLine(is, contentLength, charset);  
        } catch (Exception e) {  
            throw new RuntimeException(e);  
        } finally {  
            if (osw != null) {  
                try {  
                    osw.close();  
                } catch (IOException e) {  
                    osw = null;  
                    throw new RuntimeException(e);  
                } finally {  
                    if (socket != null) {  
                        try {  
                            socket.close();  
                        } catch (IOException e) {  
                            socket = null;  
                            throw new RuntimeException(e);  
                        }  
                    }  
                }  
            }  
            if (is != null) {  
                try {  
                    is.close();  
                } catch (IOException e) {  
                    is = null;  
                    throw new RuntimeException(e);  
                } finally {  
                    if (socket != null) {  
                        try {  
                            socket.close();  
                        } catch (IOException e) {  
                            socket = null;  
                            throw new RuntimeException(e);  
                        }  
                    }  
                }  
            }  
        }  
        return result;  
    }  
  
    /** 
     * @Description:读取一行数据，contentLe内容长度为0时，读取响应头信息，不为0时读正文 
     * @time:2016年5月17日 下午6:11:07 
     */  
    private static String readLine(InputStream is, int contentLength, String charset) throws IOException {  
        List<Byte> lineByte = new ArrayList<Byte>();  
        byte tempByte;  
        int cumsum = 0;  
        if (contentLength != 0) {  
            do {  
                tempByte = (byte) is.read();  
                lineByte.add(Byte.valueOf(tempByte));  
                cumsum++;  
            } while (cumsum < contentLength);// cumsum等于contentLength表示已读完  
        } else {  
            do {  
                tempByte = (byte) is.read();  
                lineByte.add(Byte.valueOf(tempByte));  
            } while (tempByte != 10);// 换行符的ascii码值为10  
        }  
  
        byte[] resutlBytes = new byte[lineByte.size()];  
        for (int i = 0; i < lineByte.size(); i++) {  
            resutlBytes[i] = (lineByte.get(i)).byteValue();  
        }  
        return new String(resutlBytes, charset);  
    }  
    
    /**
	 * 重写X509TrustManager
	 */
	private static TrustManager myX509TrustManager = new X509TrustManager() {
		
		@Override
		public X509Certificate[] getAcceptedIssuers() {
			return null;
		}
		
		@Override
		public void checkServerTrusted(X509Certificate[] chain, String authType) throws CertificateException {
			
		}
		
		@Override
		public void checkClientTrusted(X509Certificate[] chain, String authType) throws CertificateException {
			
		}
	};
    
    /**
	 * 发送请求
	 * @param url
	 * @param strings
	 * @return
	 */
	public static String sendHttpsJSON(String url, JSONObject obj){
		if(url.isEmpty()){
			return null;
		}
		try {
			//设置SSLContext
			SSLContext ssl = SSLContext.getInstance("SSL");
			ssl.init(null, new TrustManager[]{myX509TrustManager}, null);
			
			//打开连接
			HttpsURLConnection conn = (HttpsURLConnection) new URL(url).openConnection();
			//设置套接工厂
			conn.setSSLSocketFactory(ssl.getSocketFactory());
			//加入数据
			conn.setRequestMethod("POST");
			conn.setDoOutput(true);
			conn.setRequestProperty("Content-type","application/json");
			
	        BufferedOutputStream buffOutStr = new BufferedOutputStream(conn.getOutputStream());
	        buffOutStr.write(obj.toString().getBytes());
	        buffOutStr.flush();
	        buffOutStr.close();
	        
	        //获取输入流
	        BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
	        
	        String line = null;
	        StringBuffer sb = new StringBuffer();
	        while((line = reader.readLine())!= null){
	        	sb.append(line);
	        }
	        return sb.toString();
		
		} catch (Exception e) {
			return null;
		}
	}
      
}