package jdk.net;

import org.testng.annotations.Test;

import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

/**
 * Created by whydk on 10/31/2016.
 */
public class LoalIpList {

  @Test
  public void test() throws SocketException {
    List<String> list = getLocalIPList();
    for (String s : list) {
      System.out.println(s);
    }
    System.out.println("\n\n");
    System.out.println(IPAddressUtil.getLocalIp());
  }


  public List<String> getLocalIPList() throws SocketException {
    List<String> ipList = new ArrayList<String>();
    try {
      Enumeration<NetworkInterface> networkInterfaces = NetworkInterface.getNetworkInterfaces();
      NetworkInterface networkInterface;
      Enumeration<InetAddress> inetAddresses;
      InetAddress inetAddress;
      String ip;
      while (networkInterfaces.hasMoreElements()) {
        networkInterface = networkInterfaces.nextElement();
        inetAddresses = networkInterface.getInetAddresses();
        while (inetAddresses.hasMoreElements()) {
          inetAddress = inetAddresses.nextElement();
          if (inetAddress != null && inetAddress instanceof Inet4Address) { // IPV4
            ip = inetAddress.getHostAddress();
            ipList.add(ip);
          }
        }
      }
    } catch (SocketException e) {
      e.printStackTrace();
    }
    return ipList;
  }

}
