package com.example.lcheng215.fixture;

import android.util.Log;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class HttpUtils {
    //private final static String URL_PATH = "http://192.168.6.9/webel/images/corporate.png";
    private final static String URL_PATH = "http://c.hiphotos.baidu.com/album/w%3D2048/sign=aa13cdbb023b5bb5bed727fe02ebd539/7dd98d1001e93901a318412e7aec54e736d1968c.jpg";

    public static InputStream getImageViewInputStream() throws IOException{
        InputStream inputStream = null;
        URL url = new URL(URL_PATH);
        if(url != null){
            HttpURLConnection httpURLConnection = (HttpURLConnection)url.openConnection();
            httpURLConnection.setConnectTimeout(3000);
            httpURLConnection.setRequestMethod("GET");
            httpURLConnection.setDoInput(true);
            int response_code = httpURLConnection.getResponseCode();
            if(response_code == 200){
                inputStream = httpURLConnection.getInputStream();
            }
        }
        return inputStream;
    }

    public static byte[] getImageViewArray(){
        byte[] data = null;
        InputStream inputStream = null;
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        try{
            Log.v("kkk", "in class HttpUtils getImageViewArray()");
            Log.v("kkk", URL_PATH);
            URL url = new URL(URL_PATH);
            if(url != null){
                Log.v("kkk", "url not null");
                HttpURLConnection httpURLConnection = (HttpURLConnection)url.openConnection();
                Log.v("kkk", "httpURLConnection implented");
                httpURLConnection.setConnectTimeout(3000);
                Log.v("kkk", "after setConnecttimeout()");
                httpURLConnection.setRequestMethod("GET");
                Log.v("kkk", "after setRequestMethod()");
                httpURLConnection.setDoInput(true);
                Log.v("kkk", "after setDoInput()");
                int response_code = httpURLConnection.getResponseCode();
                //int response_code = 200;
                Log.v("kkk", "after getResponseCode():");
                if(response_code == 200){
                    int len;
                    byte[] b_data = new byte[1024];
                    Log.v("kkk","connection 200, ok");
                    inputStream = httpURLConnection.getInputStream();
                    while ((len = inputStream.read(b_data)) != -1){
                        outputStream.write(b_data,0,len);
                    }
                    data = outputStream.toByteArray();
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            if(inputStream != null){
                try{
                    inputStream.close();
                }catch (IOException e){
                    e.printStackTrace();
                }
            }
        }
        return data;
    }
}
