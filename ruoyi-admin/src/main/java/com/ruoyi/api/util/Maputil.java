package com.ruoyi.api.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.ruoyi.common.utils.StringUtils;
import org.apache.commons.collections4.MapUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;


public class Maputil {
    public final static Logger logger=LoggerFactory.getLogger(Maputil.class);
    private static String key = "c7dc0afd187e002926f842e73b790105";
    public static Map<String, Double> getLatAndLogByName(String address) {
        if (StringUtils.isEmpty(address)) {
            return new HashMap<>();
        }
        try {
            // 构建请求参数
            StringBuffer params = new StringBuffer();
            params.append("key=").append(key)
                    .append("&address=").append(URLEncoder.encode(address, "UTF-8"))
                    .append("&output=JSON");

            // 发送请求
            String res = sendGet("https://restapi.amap.com/v3/geocode/geo", params.toString());
            logger.info("地址: {}, API响应: {}", address, res);

            // 解析响应
            JSONObject jsonObject = JSON.parseObject(res);
            if ("1".equals(jsonObject.getString("status"))) {
                JSONArray jsonArray = jsonObject.getJSONArray("geocodes");
                if (jsonArray != null && !jsonArray.isEmpty()) {
                    JSONObject location = jsonArray.getJSONObject(0);
                    String locationStr = location.getString("location");
                    if (StringUtils.isNotEmpty(locationStr)) {
                        String[] parts = locationStr.split(",");
                        if (parts.length == 2) {
                            Map<String, Double> coordinates = new HashMap<>();
                            coordinates.put("longitude", Double.parseDouble(parts[0]));
                            coordinates.put("latitude", Double.parseDouble(parts[1]));
                            return coordinates;
                        }
                    }
                }
                logger.warn("未找到地理编码结果: {}", address);
            } else {
                logger.warn("地址解析失败: {} - {} - {}",
                        address,
                        jsonObject.getString("info"),
                        jsonObject.getString("infocode"));
            }
        } catch (Exception e) {
            logger.error("地址解析异常: " + address, e);
        }

        return new HashMap<>();
    }

    public static String getNameByLatAndLog(Double latitude,Double longitude){
        if(latitude==null||longitude==null){
            return "";
        }
        int maxRetries=3;
        int retryDelay=1000;
        for(int retry=0;retry<maxRetries;retry++){
            try {
                if(retry>0){
                    Thread.sleep(retryDelay);
                    logger.info("第{}次重试获取地址信息:{},{}",retry,longitude,latitude);
                }
                StringBuffer params=new StringBuffer();
                params.append("key=").append(key).append("&location=").append(longitude).append(",").append(latitude).append("&output=JSON");
                String res=sendGet("https://restapi.amap.com/v3/geocode/regeo",params.toString());
                logger.info("经纬度: {},{}, API响应: {}", longitude, latitude, res);  // 添加响应日志
                JSONObject jsonObject=JSON.parseObject(res);
                if("1".equals(jsonObject.getString("status"))){
                    JSONObject regeocode=jsonObject.getJSONObject("regeocode");
                    if(regeocode!=null){
                        JSONObject addressComponent=regeocode.getJSONObject("addressComponent");
                        if(addressComponent!=null){
                            String district=addressComponent.getString("district");
                            if (StringUtils.isNotEmpty(district)){
                                return district;
                            }
                        }
                    }
                    logger.error("未找到城市信息:{},{}",latitude,longitude);
                }else {
                    String infocode=jsonObject.getString("infocode");
                    if("10021".equals(infocode)){
                        if(retry<maxRetries-1){
                            continue;
                        }
                    }
                    logger.error("逆地理编码失败{},{} -{}-{}",latitude,longitude,jsonObject.getString("info"),jsonObject.getString("infocode"));
                }
            }catch (Exception e){
                logger.error("获取城市名异常",latitude+","+longitude,e.getMessage());
            }
        }

        return "";
    }




    public static String sendGet(String url, String param) {
        String result = "";
        BufferedReader in = null;
        try {
            String urlNameString = url + "?" + param;
            URL realUrl = new URL(urlNameString);
            // 打开和URL之间的连接
            URLConnection connection = realUrl.openConnection();
            // 设置通用的请求属性
            connection.setRequestProperty("accept", "*/*");
            connection.setRequestProperty("connection", "Keep-Alive");
            connection.setRequestProperty("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
            // 建立实际的连接
            connection.connect();
            // 获取所有响应头字段
            Map<String, List<String>> map = connection.getHeaderFields();
            // 遍历所有的响应头字段
            for (String key : map.keySet()) {
                logger.info(key + "--->" + map.get(key));
            }
            // 定义 BufferedReader输入流来读取URL的响应
            in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String line;
            while ((line = in.readLine()) != null) {
                result += line;
            }
        } catch (Exception e) {
            logger.info("发送GET请求出现异常！" + e);
            e.printStackTrace();
        }
        // 使用finally块来关闭输入流
        finally {
            try {
                if (in != null) {
                    in.close();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return result;
    }

    /**
     * 向指定 URL 发送POST方法的请求
     *
     * @param url   发送请求的 URL
     * @param param 请求参数，请求参数应该是 name1=value1&name2=value2 的形式。
     * @return 所代表远程资源的响应结果
     */
    public static String sendPost(String url, String param) {
        PrintWriter out = null;
        BufferedReader in = null;
        String result = "";
        try {
            URL realUrl = new URL(url);
            // 打开和URL之间的连接
            URLConnection conn = realUrl.openConnection();
            // 设置通用的请求属性
            conn.setRequestProperty("accept", "*/*");
            conn.setRequestProperty("connection", "Keep-Alive");
            conn.setRequestProperty("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
            // 发送POST请求必须设置如下两行
            conn.setDoOutput(true);
            conn.setDoInput(true);
            // 获取URLConnection对象对应的输出流
            out = new PrintWriter(conn.getOutputStream());
            // 发送请求参数
            out.print(param);
            // flush输出流的缓冲
            out.flush();
            // 定义BufferedReader输入流来读取URL的响应
            in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String line;
            while ((line = in.readLine()) != null) {
                result += line;
            }
        } catch (Exception e) {
            logger.info("发送 POST 请求出现异常！" + e);
            e.printStackTrace();
        }
        // 使用finally块来关闭输出流、输入流
        finally {
            try {
                if (out != null) {
                    out.close();
                }
                if (in != null) {
                    in.close();
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        return result;
    }

    /**
     * GET请求数据
     *
     * @param get_url url地址
     * @param content key=value形式
     * @return 返回结果
     * @throws Exception
     */
    public static String sendGetData(String get_url, String content) throws Exception {
        String result = "";
        URL getUrl = null;
        BufferedReader reader = null;
        String lines = "";
        HttpURLConnection connection = null;
        try {
            if (content != null && !content.equals(""))
                get_url = get_url + "?" + content;
            // get_url = get_url + "?" + URLEncoder.encode(content, "utf-8");
            getUrl = new URL(get_url);
            connection = (HttpURLConnection) getUrl.openConnection();
            connection.connect();
            // 取得输入流，并使用Reader读取
            reader = new BufferedReader(new InputStreamReader(connection.getInputStream(), "utf-8"));// 设置编码
            while ((lines = reader.readLine()) != null) {
                result = result + lines;
            }
            return result;
        } catch (Exception e) {
            throw e;
        } finally {
            if (reader != null) {
                reader.close();
                reader = null;
            }
            connection.disconnect();
        }
    }

    /**
     * @param POST_URL url地址
     * @param content  key=value形式
     * @return 返回结果
     * @throws Exception
     */
    public static String sendPostData(String POST_URL, String content) throws Exception {
        HttpURLConnection connection = null;
        DataOutputStream out = null;
        BufferedReader reader = null;
        String line = "";
        String result = "";
        try {
            URL postUrl = new URL(POST_URL);
            connection = (HttpURLConnection) postUrl.openConnection();
            connection.setDoOutput(true);
            connection.setDoInput(true);
            connection.setRequestMethod("POST");
            // Post 请求不能使用缓存
            connection.setUseCaches(false);
            connection.setInstanceFollowRedirects(true);
            connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            connection.connect();

            out = new DataOutputStream(connection.getOutputStream());
            // content = URLEncoder.encode(content, "utf-8");
            // DataOutputStream.writeBytes将字符串中的16位的unicode字符�?8位的字符形式写道流里�?
            out.writeBytes(content);
            out.flush();
            out.close();
            // 获取结果
            reader = new BufferedReader(new InputStreamReader(connection.getInputStream(), "utf-8"));// 设置编码
            while ((line = reader.readLine()) != null) {
                result = result + line;
            }
            return result;
        } catch (Exception e) {
            throw e;
        } finally {
            if (out != null) {
                out.close();
                out = null;
            }
            if (reader != null) {
                reader.close();
                reader = null;
            }
            connection.disconnect();
        }
    }

    /*
     * 过滤掉html里不安全的标签，不允许用户输入这些标�?
     */
    public static String htmlFilter(String inputString) {
        // return inputString;
        String htmlStr = inputString; // 含html标签的字符串
        String textStr = "";
        java.util.regex.Pattern p_script;
        java.util.regex.Matcher m_script;

        try {
            String regEx_script = "<[\\s]*?(script|style)[^>]*?>[\\s\\S]*?<[\\s]*?\\/[\\s]*?(script|style)[\\s]*?>";
            String regEx_onevent = "on[^\\s]+=\\s*";
            String regEx_hrefjs = "href=javascript:";
            String regEx_iframe = "<[\\s]*?(iframe|frameset)[^>]*?>[\\s\\S]*?<[\\s]*?\\/[\\s]*?(iframe|frameset)" +
                    "[\\s]*?>";
            String regEx_link = "<[\\s]*?link[^>]*?/>";

            htmlStr = Pattern.compile(regEx_script, Pattern.CASE_INSENSITIVE).matcher(htmlStr).replaceAll("");
            htmlStr = Pattern.compile(regEx_onevent, Pattern.CASE_INSENSITIVE).matcher(htmlStr).replaceAll("");
            htmlStr = Pattern.compile(regEx_hrefjs, Pattern.CASE_INSENSITIVE).matcher(htmlStr).replaceAll("");
            htmlStr = Pattern.compile(regEx_iframe, Pattern.CASE_INSENSITIVE).matcher(htmlStr).replaceAll("");
            htmlStr = Pattern.compile(regEx_link, Pattern.CASE_INSENSITIVE).matcher(htmlStr).replaceAll("");

            textStr = htmlStr;

        } catch (Exception e) {
            System.err.println("Html2Text: " + e.getMessage());
        }

        return textStr;
    }
}

