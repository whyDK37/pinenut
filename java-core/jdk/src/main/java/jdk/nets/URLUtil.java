package jdk.nets;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by whydk on 10/24/2016.
 */
public class URLUtil {

  /**
   * only url,without param.
   *
   * @param strURL
   * @return
   */
  public static String pureUrl(String strURL) {
    String strPage = null;

    strURL = strURL.trim().toLowerCase();

    String[] arrSplit = strURL.split("[?]");
    if (strURL.length() > 0) {
      if (arrSplit.length > 1) {
        if (arrSplit[0] != null) {
          strPage = arrSplit[0];
        }
      }
    }

    return strPage;
  }

  /**
   * 去掉url中的路径，留下请求参数部分
   *
   * @param strURL url地址
   * @return url请求参数部分
   */
  public static String queryString(String strURL) {
    String strAllParam = null;

    strURL = strURL.trim().toLowerCase();
    String[] arrSplit = strURL.split("[?]");
    if (strURL.length() > 1) {
      if (arrSplit.length > 1) {
        if (arrSplit[1] != null) {
          strAllParam = arrSplit[1];
        }
      }
    }

    return strAllParam;
  }

  /**
   * 解析出url参数中的键值对
   * 如 "index.jsp?Action=del&id=123"，解析出Action:del,id:123存入map中,只有参数没有值，不加入.
   *
   * @param URL url地址
   * @return url请求参数部分
   */
  public static Map<String, String> requestParamMap(String URL) {
    Map<String, String> mapRequest = new HashMap<String, String>();

    String strUrlParam = queryString(URL);
    if (strUrlParam == null) {
      return mapRequest;
    }
    //每个键值为一组 www.2cto.com
    String[] arrSplit = strUrlParam.split("[&]");
    for (String strSplit : arrSplit) {
      String[] arrSplitEqual = null;
      arrSplitEqual = strSplit.split("[=]");

      //解析出键值
      if (arrSplitEqual.length > 1) {
        //正确解析
        mapRequest.put(arrSplitEqual[0], arrSplitEqual[1]);

      } else {
        if (arrSplitEqual[0] != "") {
          //只有参数没有值，不加入
          mapRequest.put(arrSplitEqual[0], "");
        }
      }
    }
    return mapRequest;
  }
}
