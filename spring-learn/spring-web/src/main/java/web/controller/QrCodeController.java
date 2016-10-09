package web.controller;

import com.google.zxing.WriterException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import util.QrCodeWriter;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by why on 10/9/2016.
 */
@Controller
@RequestMapping("/qrcode")
public class QrCodeController {

    @RequestMapping("/{qrcodestring}")
    @ResponseBody
    public void qrcode(@PathVariable String qrcodestring, HttpServletRequest request,
                       HttpServletResponse response) throws IOException {
        // 禁止图像缓存。
        response.setHeader("Pragma", "no-cache");
        response.setHeader("Cache-Control", "no-cache");
        response.setDateHeader("Expires", 0);

        response.setContentType("image/jpeg");
//        response.setHeader("Content-Disposition", "qrcode.jpg");
        // 将图像输出到Servlet输出流中。
        ServletOutputStream sos = response.getOutputStream();

        try {
            QrCodeWriter.writeToStream("http://www.baidu.com",QrCodeWriter.DEFAULT_IMAGE_FORMATE,sos);
        } catch (WriterException e) {
            e.printStackTrace();
        }finally {
            sos.close();
        }
    }
}
