package com.example.hello_world.utils;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;

import androidx.annotation.NonNull;

import com.example.hello_world.R;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class NetUtil {
    public static String doGet() {
        String result;
        BufferedReader reader ;
        String bookJSONString ;

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
    public static void okHttpGET(Handler mhandler) {
        OkHttpClient okHttpClient = new OkHttpClient();
        Request request = new Request.Builder().url("https://www.baidu.com").build();
        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {
                // 请求失败
                Log.e("OkHttp", "Request failed", e);
            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                if (!response.isSuccessful()) {
                    Log.e("OkHttp", "Unexpected response code: " + response.code());
                    return;
                }

                // 注意：这里在子线程，需要切换到主线程更新UI
                String responseBody = response.body().string();
                response.close();
                Message message = new Message();
                message.what = 1;
                message.obj = responseBody;
                mhandler.sendMessage(message);
            }
        });
    }
}
