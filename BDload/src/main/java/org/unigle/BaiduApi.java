package org.unigle;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class BaiduApi {
//    在线API解析：http://www.pojiewo.com/baidu.php/?url=【网盘链接】&pwd=【提取码】
    public Map<Integer, String> BaiduApi(String URL, String PWD){
        String conurl=null;
        String Url = "url=";
        String Pwd = "&pwd=";
        String pojiewo = "http://www.pojiewo.com/baidu.php/?";

        String inputLine = "";
        String read = "";
        try {
            Url=Url+URL;
            Pwd=Pwd+PWD;
            URL url = new URL(pojiewo+Url+Pwd);
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            BufferedReader in = new BufferedReader(new InputStreamReader(urlConnection.getInputStream(),"UTF-8"));
            while ((read = in.readLine()) != null) {
                inputLine += read;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        String patter = "href=\"(.*?)\">";
        Pattern p = Pattern.compile(patter);
        Matcher m = p.matcher(inputLine);
        int a= 0;
        Map<Integer, String> map = new HashMap<Integer, String>();
        while(m.find()){
            String ipstr = m.group(0);
            String hef="/wen/css/layuio.css";
            if (ipstr.contains(hef)){
                ipstr=null;
            }else {
                map.put(a,ipstr);
                a++;
            }
        }
        return map;
    }
}

