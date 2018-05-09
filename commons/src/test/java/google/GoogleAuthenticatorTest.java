package google;

import org.junit.Test;

public class GoogleAuthenticatorTest {

    @Test
    public void test() {
        String secret = GoogleAuthenticator2.genSecret();
        System.out.println(secret);

        System.out.println(GoogleAuthenticator2.authcode("613522", "I5OFMYTTR3F2GUNY"));
    }

    //当测试authTest时候，把genSecretTest生成的secret值赋值给它
    private static String secret = "R2Q3S52RNXBTFTOM";

    @Test
    public void genSecretTest() {// 生成密钥
        secret = GoogleAuthenticator.generateSecretKey();
        // 把这个qrcode生成二维码，用google身份验证器扫描二维码就能添加成功
        String qrcode = GoogleAuthenticator.getQRBarcode("2816661736@qq.com", secret);
        System.out.println("qrcode:" + qrcode + ",key:" + secret);
    }

    /**
     * 对app的随机生成的code,输入并验证
     */
    @Test
    public void verifyTest() {
        long code = 630725;
        long t = System.currentTimeMillis();
        GoogleAuthenticator ga = new GoogleAuthenticator();
        ga.setWindowSize(5);
        boolean r = ga.check_code(secret, code, t);
        System.out.println("检查code是否正确？" + r);
    }
}