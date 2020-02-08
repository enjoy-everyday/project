package com.sise.familyEducation.location;


import org.json.JSONException;
import org.json.JSONObject;

import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Map;

/**
 * @program: FamilyEducation
 * @description: 通过IP获取位置
 * @author: wxy
 * @create: 2020-02-07 19:38
 **/

public class GetPlaceByIp {

    private static String readAll(Reader rd) throws IOException {
        StringBuilder sb = new StringBuilder();
        int cp;
        while ((cp = rd.read()) != -1) {
            sb.append((char) cp);
        }
        return sb.toString();
    }

    public static JSONObject readJsonFromUrl(String url) throws IOException, JSONException {
        InputStream is = new URL(url).openStream();
        try {
            BufferedReader rd = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));
            String jsonText = readAll(rd);
            JSONObject json = new JSONObject(jsonText);
            return json;
        } finally {
            is.close();            // System.out.println("同时 从这里也能看出 即便return了，仍然会执行finally的！");
        }
    }

    public static Map<String, String> getPlace(HttpServletRequest request) throws IOException, JSONException {
        String ip = IpUtil.getIpAddr(request);
        if (ip.equals("0:0:0:0:0:0:0:1")) {
            ip = "";//如果本机地址，ip设为空
        }
        String url = "https://api.map.baidu.com/location/ip?ip=" + ip + "&ak=AmvCasrXy5ftVZ5uQioWQgdEtVyuXv4o&coor=bd09ll";
        JSONObject jsonObject = readJsonFromUrl(url);
        System.out.println(jsonObject.toString());
        String place = (String) ((JSONObject) jsonObject.get("content")).get("address");
        System.out.println(place);
        Map<String, String> map = new HashMap<>();
        map.put("province", (String) ((JSONObject) ((JSONObject) jsonObject.get("content")).get("address_detail")).get("province"));
        map.put("city", (String) ((JSONObject) ((JSONObject) jsonObject.get("content")).get("address_detail")).get("city"));
        System.out.println(map);
        return map;
    }

}
