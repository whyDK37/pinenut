package me.net;

import junit.framework.Test;
import junit.framework.TestSuite;
import junit.framework.TestCase;
import net.GetAddressByIp;

import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * GetAddressByIp Tester.
 *
 * @author <Authors name>
 * @version 1.0
 * @since <pre>06/11/2016</pre>
 */
public class GetAddressByIpTest extends TestCase {
//    public static final String ip = "117.73.209.71";//中国
//    public static final String ip = "93.123.23.1";//保加利亚
//    public static final String ip = "218.189.25.129";//香港
    public static final String ip = "197.199.253.1";//埃及
//    public static final String ip = "218.176.242.10";//日本
//    public static final String ip = "149.126.86.1";//冰岛

    public GetAddressByIpTest(String name) {
        super(name);
    }

    public void setUp() throws Exception {
        super.setUp();
    }

    public void tearDown() throws Exception {
        super.tearDown();
    }

    /**
     * Method: GetAddressByIp(String IP)
     */
    public void testGetAddressByIp() throws Exception {
        System.out.println(GetAddressByIp.getAddressByIp(ip));
    }

    /**
     * Method: getJsonContent(String urlStr)
     */
    public void testGetJsonContent() throws Exception {
        System.out.println(GetAddressByIp.getJsonContent(GetAddressByIp.IP_SERVICE + ip));
    }


    /**
     * Method: ConvertStream2Json(InputStream inputStream)
     */
    public void testConvertStream2Json() throws Exception {
////TODO: Test goes here...
//        try {
//            Method method = GetAddressByIp.getClass().getMethod("ConvertStream2Json", InputStream.class);
//            method.setAccessible(true);
//            method.invoke( < Object >,<Parameters >);
//        } catch (NoSuchMethodException e) {
//        } catch (IllegalAccessException e) {
//        } catch (InvocationTargetException e) {
//        }
    }


    public static Test suite() {
        return new TestSuite(GetAddressByIpTest.class);
    }
} 
