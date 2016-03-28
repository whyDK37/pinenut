package me.util.secret;

import org.apache.commons.codec.digest.DigestUtils;

import java.io.FileInputStream;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by drug on 2016/3/14.
 */
public class SHA1UtilsTest {

    public static void main(String args[]) throws IOException, NoSuchAlgorithmException {
        String str = "adminadminadminadminadminadminadminadmin";
        System.out.println("admin的SHA1的值为：" + SHA1Utils.hex_sha1(str) + ",length=" + SHA1Utils.hex_sha1(str).length());


        String file = "D:\\OneDrive\\.m2\\repository\\ant\\ant\\1.6.5\\ant-1.6.5.jar";
        System.out.println(SHA1Utils.getFileSha1(file));

        String string = new SHA1().getDigestOfFile(file);
        System.out.println(string);
        System.out.println(org.apache.commons.codec.digest.DigestUtils.sha1Hex(new FileInputStream(file)));
        System.out.println(sha256("test string to sha256"));
    }


    static String sha256(String input) throws NoSuchAlgorithmException {
        MessageDigest mDigest = MessageDigest.getInstance("SHA256");
        byte[] result = mDigest.digest(input.getBytes());
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < result.length; i++) {
            sb.append(Integer.toString((result[i] & 0xff) + 0x100, 16).substring(1));
        }

        return sb.toString();
    }
}
