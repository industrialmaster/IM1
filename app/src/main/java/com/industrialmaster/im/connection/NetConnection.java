package com.industrialmaster.im.connection;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by Yudeshi on 10/12/2016.
 */
public  class NetConnection {
    public static String call(String urlString){
        String webPage = "";
        try {
            String link = "http://www.idex.tk/im/"+urlString;
            URL url = new URL(link);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.connect();

            InputStream is = conn.getInputStream();
            BufferedReader reader =new BufferedReader(new InputStreamReader(is, "UTF-8"));
            String data="";

            while ((data = reader.readLine()) != null){
                webPage += data + "\n";
            }
        }catch(Exception e){
            e.printStackTrace();
            webPage = "Error";
        }
        return webPage;
    }
}
