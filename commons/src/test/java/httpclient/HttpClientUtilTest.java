package httpclient;

import org.testng.annotations.Test;

public class HttpClientUtilTest {
  @Test
  public void testGetString() throws Exception {
//    String string = HttpClientUtil.getString("http://ucenter.dev.imdada.cn/v1_0/picking/transporter/loginPlus/?sign=390ccd7d4279126ab3cab47176165107&phone=13264578562&signType=MD5&sdCardId=3AD6C666-8667-46F4-A8EA-E4D13CC5E6D2&lng=116.5095&code=kskskndnd&platForm=IOS&lat=39.79488", null);
//    System.out.println(string);
    String string = HttpClientUtil.getString("http://220.248.56.170:5053/v1_0/picking/transporter/loginPlus/?sign=390ccd7d4279126ab3cab47176165107&phone=13264578562&signType=MD5&sdCardId=3AD6C666-8667-46F4-A8EA-E4D13CC5E6D2&lng=116.5095&code=kskskndnd&platForm=IOS&lat=39.79488", null);
    System.out.println(string);
  }

  @Test
  public void testGetBytes() throws Exception {
  }

  @Test
  public void testPostString() throws Exception {
  }

}