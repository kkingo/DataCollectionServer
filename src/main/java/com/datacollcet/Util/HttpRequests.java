package com.datacollcet.Util;

/**
 * Created by 郭诗韬 on 2017/10/12.
 */
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.security.SecureRandom;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Map.Entry;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.KeyManager;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;

public class HttpRequests {
    private static final int CONNECT_TIME_OUT = 30000;
    private static final int READ_OUT_TIME = 50000;
    private static String boundaryString = getBoundary();

    public HttpRequests() {
    }

    protected static byte[] post(String url, HashMap<String, String> map, byte[] fileByte, File file) throws Exception {
        URL url1 = new URL(url);
        HttpURLConnection conne = (HttpURLConnection)url1.openConnection();
        conne.setDoOutput(true);
        conne.setUseCaches(false);
        conne.setRequestMethod("POST");
        conne.setConnectTimeout(30000);
        conne.setReadTimeout('9');
        conne.setRequestProperty("accept", "*/*");
        conne.setRequestProperty("Content-Type", "multipart/form-data; boundary=" + boundaryString);
        conne.setRequestProperty("connection", "Keep-Alive");
        conne.setRequestProperty("user-agent", "Mozilla/4.0 (compatible;MSIE 6.0;Windows NT 5.1;SV1)");
        DataOutputStream obos = new DataOutputStream(conne.getOutputStream());
        Iterator iter = map.entrySet().iterator();

        while(iter.hasNext()) {
            Entry<String, String> entry = (Entry)iter.next();
            String key = (String)entry.getKey();
            String value = (String)entry.getValue();
            obos.writeBytes("--" + boundaryString + "\r\n");
            obos.writeBytes("Content-Disposition: form-data; name=\"" + key + "\"\r\n");
            obos.writeBytes("\r\n");
            obos.writeBytes(encode(value) + "\r\n");
        }

        if(fileByte != null) {
            obos.writeBytes("--" + boundaryString + "\r\n");
            obos.writeBytes("Content-Disposition: form-data; name=\"image_file\"\r\n");
            obos.writeBytes("\r\n");
            obos.write(fileByte);
            obos.writeBytes("\r\n");
        }

        if(file != null) {
            byte[] buf = getBytesFromFile(file);
            obos.writeBytes("--" + boundaryString + "\r\n");
            obos.writeBytes("Content-Disposition: form-data; name=\"image_file\"; filename=\"" + encode(file.getName()) + "\"\r\n");
            obos.writeBytes("\r\n");
            obos.write(buf);
            obos.writeBytes("\r\n");
        }

        obos.writeBytes("--" + boundaryString + "--" + "\r\n");
        obos.writeBytes("\r\n");
        obos.flush();
        InputStream ins = conne.getInputStream();
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        byte[] buff = new byte[4096];

        int len;
        while((len = ins.read(buff)) != -1) {
            baos.write(buff, 0, len);
        }

        byte[] bytes = baos.toByteArray();
        ins.close();
        return bytes;
    }

    protected static byte[] post(String url, HashMap<String, String> map) throws Exception {
        try {
            URL url1 = new URL(url);
            HttpURLConnection conne = (HttpURLConnection)url1.openConnection();
            conne.setDoOutput(true);
            conne.setUseCaches(false);
            conne.setRequestMethod("POST");
            conne.setRequestProperty("accept", "*/*");
            conne.setRequestProperty("connection", "Keep-Alive");
            conne.setRequestProperty("user-agent", "Mozilla/4.0 (compatible;MSIE 6.0;Windows NT 5.1;SV1)");
            PrintWriter obos = new PrintWriter(conne.getOutputStream());
            Iterator iter = map.entrySet().iterator();
            StringBuffer sb = new StringBuffer();

            while(iter.hasNext()) {
                Entry<String, String> entry = (Entry)iter.next();
                String key = (String)entry.getKey();
                String value = (String)entry.getValue();
                sb.append(key);
                sb.append("=");
                sb.append(value);
                sb.append("&");
            }

            if(map.size() > 0) {
                sb = sb.deleteCharAt(sb.length() - 1);
            }

            obos.print(sb.toString());
            obos.flush();
            InputStream ins = conne.getInputStream();
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            byte[] buff = new byte[4096];

            int len;
            while((len = ins.read(buff)) != -1) {
                baos.write(buff, 0, len);
            }

            byte[] bytes = baos.toByteArray();
            ins.close();
            return bytes;
        } catch (Exception var12) {
            var12.printStackTrace();
            return null;
        }
    }

    private static String getBoundary() {
        StringBuilder sb = new StringBuilder();
        Random random = new Random();

        for(int i = 0; i < 32; ++i) {
            sb.append("ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789_-".charAt(random.nextInt("ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789_".length())));
        }

        return sb.toString();
    }

    public static byte[] getBytesFromFile(File f) {
        if(f == null) {
            return null;
        } else {
            try {
                FileInputStream stream = new FileInputStream(f);
                ByteArrayOutputStream out = new ByteArrayOutputStream(1000);
                byte[] b = new byte[1000];

                int n;
                while((n = stream.read(b)) != -1) {
                    out.write(b, 0, n);
                }

                stream.close();
                out.close();
                return out.toByteArray();
            } catch (IOException var5) {
                return null;
            }
        }
    }

    public static String sendGet(String url, String param) {
        String result = "";
        BufferedReader in = null;

        try {
            String urlNameString = url + "?" + param;
            URL realUrl = new URL(urlNameString);
            URLConnection connection = realUrl.openConnection();
            connection.setRequestProperty("accept", "*/*");
            connection.setRequestProperty("connection", "Keep-Alive");
            connection.setRequestProperty("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
            connection.setRequestProperty("Accept-Charset", "utf-8");
            connection.setRequestProperty("contentType", "utf-8");
            connection.connect();
            Map<String, List<String>> map = connection.getHeaderFields();

            String line;
            for(Iterator var9 = map.keySet().iterator(); var9.hasNext(); line = (String)var9.next()) {
                ;
            }

            for(in = new BufferedReader(new InputStreamReader(connection.getInputStream(), "utf-8")); (line = in.readLine()) != null; result = result + line) {
                ;
            }
        } catch (Exception var18) {
            System.out.println("发送GET请求出现异常！" + var18);
            var18.printStackTrace();
        } finally {
            try {
                if(in != null) {
                    in.close();
                }
            } catch (Exception var17) {
                var17.printStackTrace();
            }

        }

        return result;
    }

    private static String encode(String value) throws Exception {
        return URLEncoder.encode(value, "UTF-8");
    }

    public static String httpsRequest(String requestUrl, String requestMethod, String outputStr) {
        StringBuffer buffer = null;

        try {
            SSLContext sslContext = SSLContext.getInstance("SSL");
            TrustManager[] tm = new TrustManager[]{new MyX509TrustManager()};
            sslContext.init((KeyManager[])null, tm, new SecureRandom());
            SSLSocketFactory ssf = sslContext.getSocketFactory();
            URL url = new URL(requestUrl);
            HttpsURLConnection conn = (HttpsURLConnection)url.openConnection();
            conn.setDoOutput(true);
            conn.setDoInput(true);
            conn.setUseCaches(false);
            conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            conn.setRequestMethod(requestMethod);
            conn.setSSLSocketFactory(ssf);
            conn.connect();
            if(outputStr != null) {
                OutputStream os = conn.getOutputStream();
                os.write(outputStr.getBytes("utf-8"));
                os.close();
            }

            InputStream is = conn.getInputStream();
            InputStreamReader isr = new InputStreamReader(is, "utf-8");
            BufferedReader br = new BufferedReader(isr);
            buffer = new StringBuffer();
            String line = null;

            while((line = br.readLine()) != null) {
                buffer.append(line);
            }
        } catch (Exception var13) {
            var13.printStackTrace();
        }

        return buffer.toString();
    }

    public static String sendPost(String url, String param) {
        PrintWriter out = null;
        BufferedReader in = null;
        String result = "";

        try {
            URL realUrl = new URL(url);
            URLConnection conn = realUrl.openConnection();
            conn.setDoOutput(true);
            conn.setUseCaches(false);
            conn.setRequestProperty("accept", "*/*");
            conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            conn.setRequestProperty("connection", "Keep-Alive");
            conn.setRequestProperty("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
            conn.setDoOutput(true);
            conn.setDoInput(true);
            out = new PrintWriter(conn.getOutputStream());
            out.print(param);
            out.flush();

            String line;
            for(in = new BufferedReader(new InputStreamReader(conn.getInputStream())); (line = in.readLine()) != null; result = result + line) {
                ;
            }
        } catch (Exception var16) {
            System.out.println("发送 POST 请求出现异常！" + var16);
            var16.printStackTrace();
        } finally {
            try {
                if(out != null) {
                    out.close();
                }

                if(in != null) {
                    in.close();
                }
            } catch (IOException var15) {
                var15.printStackTrace();
            }

        }

        return result;
    }
}
