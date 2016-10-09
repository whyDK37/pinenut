package util;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.common.BitMatrix;
import org.testng.annotations.Test;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

public class QrCodeTest {

    @Test
    public void main() {

        try {
            String content = "这是测试xing二维码生成";
//        String path = "D:/tt";
            String path = "D:/tmp/";
            MultiFormatWriter multiFormatWriter = new MultiFormatWriter();
            Map hints = new HashMap();
            //内容所使用编码
            hints.put(EncodeHintType.CHARACTER_SET, "UTF-8");
            BitMatrix bitMatrix = multiFormatWriter.encode(content, BarcodeFormat.QR_CODE, 200, 200, hints);
            //生成二维码
            File outputFile = new File(path, "14.jpg");
            QrCodeWriter.writeToFile(bitMatrix, "jpg", outputFile);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void test2() {
        // 依次为内容(不支持中文),宽,长,中间图标路径,储存路径 
        MatrixToImageWriter.encode("http://www.baidu.com/", 512, 512, "D:\\tmp\\logo.png", "D:\\tmp\\2013-01.jpg");
    }
}