package me.servlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;

/**
 * Created by admin on 2015/8/24.
 */
public class FiledownloadServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException {

        String filename = req.getParameter("filename");
        req.getParameterMap();
//        resp.setContentType("text/html");
        if(filename == null){
            filename = new String("index.html");
        }
        //下载文件
        resp.setHeader("Content-Disposition", "attachment;filename=" + filename);

        //1获取要下载文件的全路径
        String path = this.getServletContext().getRealPath("/");
        File file = new File(path, filename);
        //2创建文件传输流

        try {
            OutputStream os = null;
            FileInputStream fis = null;
            try {
                os = resp.getOutputStream();
                fis = new FileInputStream(file);
                //做一个缓冲字节数组
                byte buff[] = new byte[1024 * 2014 * 4];
                int len = 0; //实际每次读取的字节数
                while ((len = fis.read(buff)) > 0) {
                    os.write(buff, 0, len);
                }
            } finally {
                //关闭
                if(os!=null)os.close();
                if(fis!=null)fis.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
