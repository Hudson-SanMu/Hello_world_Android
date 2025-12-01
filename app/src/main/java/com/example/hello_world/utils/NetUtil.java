package com.example.hello_world.utils;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
 public class NetUtil {

    public static String doGet() {
        String result = "";
        BufferedReader reader = null;
        String bookJSONString = null;

        HttpURLConnection httpURLConnection = null;
        String url = "https://www.baidu.com";
        try {
            URL requestUrl = new URL(url);
            httpURLConnection = (HttpURLConnection) requestUrl.openConnection(); // 强转
            httpURLConnection.setRequestMethod("GET");
            httpURLConnection.setConnectTimeout(5000);
            httpURLConnection.connect();

//            2.获取二进制流
            InputStream inputStream = httpURLConnection.getInputStream();

            //3.将二进制流包装
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
            reader = new BufferedReader(inputStreamReader);

            //4.从bufferReader中读取String字符串
            String line;
            StringBuilder stringBuilder = new StringBuilder();
            while ((line = reader.readLine()) != null) {
                stringBuilder.append(line);
                stringBuilder.append("\n");
            }
            if (stringBuilder.length() == 0) {
                return null;
            }
            result = stringBuilder.toString();

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return result;
    }
}
