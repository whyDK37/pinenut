package jdk.me.servlet;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.List;

/**
 * @author Administrator
 *         文件上传
 *         具体步骤：
 *         1）获得磁盘文件条目工厂 DiskFileItemFactory 要导包
 *         2） 利用 request 获取 真实路径 ，供临时文件存储，和 最终文件存储 ，这两个存储位置可不同，也可相同
 *         3）对 DiskFileItemFactory 对象设置一些 属性
 *         4）高水平的API文件上传处理  ServletFileUpload upload = new ServletFileUpload(factory);
 *         目的是调用 parseRequest（request）方法  获得 FileItem 集合list ，
 *         <p/>
 *         5）在 FileItem 对象中 获取信息，   遍历， 判断 表单提交过来的信息 是否是 普通文本信息  另做处理
 *         6）
 *         第一种. 用第三方 提供的  item.write( new File(path,filename) );  直接写到磁盘上
 *         第二种. 手动处理
 */
public class FileuploadServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        String p = req.getParameter("path");
        //获得磁盘文件条目工厂
        DiskFileItemFactory factory = new DiskFileItemFactory();
        //获取文件需要上传到的路径
//        String path = this.getServletContext().getRealPath("/");

        //如果没以下两行设置的话，上传大的 文件 会占用 很多内存，
        //设置暂时存放的 存储室 , 这个存储室，可以和 最终存储文件 的目录不同
        /**
         * 原理 它是先存到 暂时存储室，然后在真正写到 对应目录的硬盘上，
         * 按理来说 当上传一个文件时，其实是上传了两份，第一个是以 .tem 格式的
         * 然后再将其真正写到 对应目录的硬盘上
         */
//        factory.setRepository(new File(path));
        //设置 缓存的大小，当上传文件的容量超过该缓存时，直接放到 暂时存储室

        //高水平的API文件上传处理
        ServletFileUpload upload = new ServletFileUpload(factory);

        try {
            //可以上传多个文件
            List<FileItem> list = upload.parseRequest(req);

            //获取表单的属性名字
            for (FileItem item : list) {
                String name = item.getFieldName();
                if (item.isFormField()) {
                    //获取用户具体输入的字符串 ，名字起得挺好，因为表单提交过来的是 字符串类型的
                    String value = item.getString();
                    req.setAttribute(name, value);
                }
            }

            String path = (String)req.getAttribute("path");
            for (FileItem item : list) {
                //获取表单的属性名字
                String name = item.getFieldName();

                //如果获取的 表单信息是普通的 文本 信息

                //对传入的非 简单的字符串进行处理 ，比如说二进制的 图片，电影这些
                if (!item.isFormField()) {
                    /**
                     * 以下三步，主要获取 上传文件的名字
                     */
                    //获取路径名
                    String value = item.getName();
                    //索引到最后一个反斜杠
//                    int start = value.lastIndexOf("\\");
                    String filename = value;
//                    filename = new String(filename.getBytes(), "utf-8");
                    req.setAttribute(name, filename);

                    //手动写的
                    OutputStream out = new FileOutputStream(new File(path, filename));
                    InputStream in = item.getInputStream();

                    int length = 0;
                    byte[] buf = new byte[1024];
                    System.out.println("获取上传文件的总共的容量：" + item.getSize());
                    // in.read(buf) 每次读到的数据存放在   buf 数组中
                    while ((length = in.read(buf)) != -1) {
                        out.write(buf, 0, length);
                    }

                    in.close();
                    out.close();
                }
            }

            req.getRequestDispatcher("multfileupload.html").forward(req, resp);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
