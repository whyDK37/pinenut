package jdk.net;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import net.sf.json.JSONObject;

/**
 * 原理是根据淘宝提供的service查询IP的归属地并且解析http请求返回的json串。
 * 我之前也是伸手党，现在也给伸手党提供下方便。
 * 淘宝返回的数据为：
 * {"code":0,"data":{"country":"\u4e2d\u56fd","country_id":"CN","area":"\u534e\u4e1c","area_id":"300000","region":"\u5c71\u4e1c\u7701","region_id":"370000","city":"\u4e1c\u8425\u5e02","city_id":"370500","county":"","county_id":"-1","isp":"\u8054\u901a","isp_id":"100026","ip":"60.214.183.158"}}
 * <p/>
 * 用法：
 * String arr1 = GetAddressByIp.GetAddressByIp("120.192.182.1");
 * System.out.println(arr1);
 * 详细请看代码
 */
public class GetAddressByIp {

    public static final String IP_SERVICE = "http://ip.taobao.com/service/getIpInfo.php?ip=";

    /**
     * @param IP
     * @return
     */
    public static String getAddressByIp(String IP) {
        String resout = "";
        try {
            String str = getJsonContent(IP_SERVICE + IP);
//            System.out.println(str);

            JSONObject obj = JSONObject.fromObject(str);
            JSONObject obj2 = (JSONObject) obj.get("data");
            String code = String.valueOf(obj.get("code"));
            if (code.equals("0")) {
                resout = obj2.get("country") + "--" + obj2.get("area") + "--" + obj2.get("city") + "--" + obj2.get("isp");
            } else {
                resout = "IP地址有误";
            }
        } catch (Exception e) {
            e.printStackTrace();
            resout = "获取IP地址异常：" + e.getMessage();
        }
        return resout;

    }

    public static String getJsonContent(String urlStr) {
        try {// 获取HttpURLConnection连接对象
            URL url = new URL(urlStr);
            HttpURLConnection httpConn = (HttpURLConnection) url.openConnection();
            // 设置连接属性
            httpConn.setConnectTimeout(3000);
            httpConn.setDoInput(true);
            httpConn.setRequestMethod("GET");
            // 获取相应码
            int respCode = httpConn.getResponseCode();
            if (respCode == 200) {
                return ConvertStream2Json(httpConn.getInputStream());
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }


    private static String ConvertStream2Json(InputStream inputStream) {
        String jsonStr = "";
        // ByteArrayOutputStream相当于内存输出流
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024];
        int len = 0;
        // 将输入流转移到内存输出流中
        try {
            while ((len = inputStream.read(buffer, 0, buffer.length)) != -1) {
                out.write(buffer, 0, len);
            }
            // 将内存流转换为字符串
            jsonStr = new String(out.toByteArray());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return jsonStr;
    }
}