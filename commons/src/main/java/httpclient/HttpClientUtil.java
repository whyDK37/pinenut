/**
 * Copyright (C) 2015 北京学信科技有限公司
 *
 * @className com.xuexin.stemcenter.util.HttpClientUtil
 * @version v1.0.0 
 * @author 何智勇
 * 
 * Modification History:
 * Date         Author      Version     Description
 * -----------------------------------------------------------------
 * 2015年10月13日      何智勇      v1.0.0      create
 *
 */
package httpclient;

import java.util.Map;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.params.HttpMethodParams;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/** 
 * httpClient工具类
 *
 * @author 何智勇
 * @date 2015年10月13日 下午4:20:47 
 */
public class HttpClientUtil {
    private static Log log = LogFactory.getLog("netdata");
    //编码格式。发送编码格式统一用UTF-8
    private static String ENCODING = "UTF-8";

    /**
     * 基于HttpClient 3.1的通用POST方法
     *
     * @param url       提交的URL
     * @param paramsMap 提交<参数，值>Map
     * @return 提交响应
     * @throws Exception 
     */
    public static String post(String url, Map<String, Object> paramsMap)
        throws Exception {
        HttpClient client = new HttpClient();
        PostMethod method = new PostMethod(url);
        try {
            if (paramsMap != null) {
                NameValuePair[] namePairs = new NameValuePair[paramsMap.size()];
                int i = 0;
                for (Map.Entry<String, Object> param : paramsMap.entrySet()) {
                    NameValuePair pair = new NameValuePair(param.getKey(),
                            String.valueOf(param.getValue()));
                    namePairs[i++] = pair;
                }
                method.setRequestBody(namePairs);
                HttpMethodParams param = method.getParams();
                param.setContentCharset(ENCODING);
            }
            client.executeMethod(method);
            if (method.getStatusCode() == 200) {
                return method.getResponseBodyAsString();
            } else {
                method.abort();
                throw new RuntimeException(String.valueOf(method
                        .getStatusCode()));
            }
        } catch (Exception e) {
            method.abort();
            log.error(e.getMessage(), e);
            throw e;
        }
    }

    public static String get(String url) throws Exception {
        HttpClient client = new HttpClient();
        GetMethod method = new GetMethod("http://" + url);
        try {
            HttpMethodParams param = method.getParams();
            param.setContentCharset(ENCODING);
            client.executeMethod(method);
            if (method.getStatusCode() == 200) {
                return method.getResponseBodyAsString();
            } else {
                method.abort();
                throw new RuntimeException(String.valueOf(method
                        .getStatusCode()));
            }
        } catch (Exception e) {
            method.abort();
            log.error(e.getMessage(), e);
            throw e;
        }
    }
}
