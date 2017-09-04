package jdk.net;

import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.net.URL;

import static jdk.net.URLUtil.*;

/**
 * Created by whydk on 10/24/2016.
 */
public class UrlTest {
  @Test
  public void test() throws MalformedURLException {
    URL aURL = new URL("http://biz.xuexindev.com/bizserver/sns/snsinfo?snsid=1006200&brand=ercihui");
    System.out.println("protocol = " + aURL.getProtocol());
    System.out.println("authority = " + aURL.getAuthority());
    System.out.println("host = " + aURL.getHost());
    System.out.println("port = " + aURL.getPort());
    System.out.println("path = " + aURL.getPath());
    System.out.println("query = " + aURL.getQuery());
    System.out.println("filename = " + aURL.getFile());
    System.out.println("ref = " + aURL.getRef());
    System.out.println(aURL.toString());

    System.out.println(queryString(aURL.toString()));
    System.out.println(requestParamMap(aURL.toString()));
    System.out.println(pureUrl(aURL.toString()));
  }


}
