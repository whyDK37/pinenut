package httpclient;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * httpClient工具类
 *
 * @author why
 * @date 2016-09-08
 */
public class HttpClientUtil {
  private static Log log = LogFactory.getLog(HttpClientUtil.class);
  //编码格式。发送编码格式统一用UTF-8
  private final static String DEFAULT_ENCODING = "UTF-8";

  public static String getString(String url, Map<String, String> params) throws URISyntaxException {
    // 创建默认的httpClient实例.
    CloseableHttpClient httpclient = HttpClients.createDefault();
    URI puri = new URI(url);
    // 创建httpget
    URIBuilder uriBuilder = new URIBuilder()
            .setScheme(puri.getScheme())
            .setHost(puri.getHost())
            .setPort(puri.getPort())
            .setPath(puri.getPath());

    RequestConfig requestConfig = getRequestConfig();

    if (params != null)
      for (Map.Entry<String, String> entry : params.entrySet()) {
        uriBuilder.setParameter(entry.getKey(), entry.getValue());
      }
    HttpGet httpget = new HttpGet(uriBuilder.build());
    try {
      httpget.setConfig(requestConfig);
      System.out.println("executing request " + httpget.getURI());
      CloseableHttpResponse response = httpclient.execute(httpget);
      printResponse(response);
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      // 关闭连接,释放资源
      try {
        httpclient.close();
      } catch (IOException e) {
        e.printStackTrace();
      }
    }

    return null;
  }

  private static RequestConfig getRequestConfig() {
    return RequestConfig.custom()
            .setSocketTimeout(100)
            .setConnectTimeout(100)
            .setConnectionRequestTimeout(100)
            .build();
  }

  public static byte[] getBytes(String url, Map<String, String> params) throws URISyntaxException {
    // 创建默认的httpClient实例.
    CloseableHttpClient httpclient = HttpClients.createDefault();
    URI puri = new URI(url);
    // 创建httpget
    URIBuilder uriBuilder = new URIBuilder()
            .setScheme(puri.getScheme())
            .setHost(puri.getHost())
            .setPort(puri.getPort())
            .setPath(puri.getPath());

    RequestConfig requestConfig = getRequestConfig();

    if (params != null)
      for (Map.Entry<String, String> entry : params.entrySet()) {
        uriBuilder.setParameter(entry.getKey(), entry.getValue());
      }
    HttpGet httpget = new HttpGet(uriBuilder.build());
    try {
      httpget.setConfig(requestConfig);
      System.out.println("executing request " + httpget.getURI());
      CloseableHttpResponse response = httpclient.execute(httpget);
      return byteResponse(response);
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      // 关闭连接,释放资源
      try {
        httpclient.close();
      } catch (IOException e) {
        e.printStackTrace();
      }
    }

    return null;
  }

  public static String postString(String url, Map<String, String> params) {
    // 创建默认的httpClient实例.
    CloseableHttpClient httpclient = HttpClients.createDefault();
    // 创建httppost
    HttpPost httppost = new HttpPost(url);

    // 创建参数队列
    List<BasicNameValuePair> formparams = new ArrayList<BasicNameValuePair>();
    for (Map.Entry<String, String> entry : params.entrySet()) {
      formparams.add(new BasicNameValuePair(entry.getKey(), entry.getValue()));
    }
    UrlEncodedFormEntity uefEntity;

    RequestConfig requestConfig = getRequestConfig();

    try {
      uefEntity = new UrlEncodedFormEntity(formparams, DEFAULT_ENCODING);
      httppost.setEntity(uefEntity);
      httppost.setConfig(requestConfig);
      System.out.println("executing request " + httppost.getURI());
      CloseableHttpResponse response = httpclient.execute(httppost);
      String responseString = printResponse(response);
      return responseString;
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      // 关闭连接,释放资源
      try {
        httpclient.close();
      } catch (IOException e) {
        e.printStackTrace();
      }
    }

    return null;
  }

  private static byte[] byteResponse(CloseableHttpResponse response) throws IOException {
    try {
      org.apache.http.HttpEntity entity = response.getEntity();
      if (entity != null) {
        byte[] responsebyte = EntityUtils.toByteArray(entity);
        System.out.println("--------------------------------------");
        System.out.println("Response content: " + responsebyte);
        System.out.println("--------------------------------------");
        return responsebyte;
      }
    } finally {
      response.close();
    }
    return null;
  }

  private static String printResponse(CloseableHttpResponse response) throws IOException {
    try {
      org.apache.http.HttpEntity entity = response.getEntity();
      if (entity != null) {
        String responseString = EntityUtils.toString(entity, DEFAULT_ENCODING);
        System.out.println("--------------------------------------");
        System.out.println("Response content: " + responseString);
        System.out.println("--------------------------------------");
        return responseString;
      }
    } finally {
      response.close();
    }
    return null;
  }
}
