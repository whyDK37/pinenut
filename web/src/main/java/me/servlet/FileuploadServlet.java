package me.servlet;

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
 *
 * @author Administrator
 * �ļ��ϴ�
 * ���岽�裺
 * 1����ô����ļ���Ŀ���� DiskFileItemFactory Ҫ����
 * 2�� ���� request ��ȡ ��ʵ·�� ������ʱ�ļ��洢���� �����ļ��洢 ���������洢λ�ÿɲ�ͬ��Ҳ����ͬ
 * 3���� DiskFileItemFactory ��������һЩ ����
 * 4����ˮƽ��API�ļ��ϴ�����  ServletFileUpload upload = new ServletFileUpload(factory);
 * Ŀ���ǵ��� parseRequest��request������  ��� FileItem ����list ��
 *
 * 5���� FileItem ������ ��ȡ��Ϣ��   ������ �ж� ���ύ��������Ϣ �Ƿ��� ��ͨ�ı���Ϣ  ��������
 * 6��
 *    ��һ��. �õ����� �ṩ��  item.write( new File(path,filename) );  ֱ��д��������
 *    �ڶ���. �ֶ�����
 *
 */
public class FileuploadServlet extends HttpServlet{

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        //��ô����ļ���Ŀ����
        DiskFileItemFactory factory = new DiskFileItemFactory();
        //��ȡ�ļ���Ҫ�ϴ�����·��
        String path = req.getRealPath("/");

        //���û�����������õĻ����ϴ���� �ļ� ��ռ�� �ܶ��ڴ棬
        //������ʱ��ŵ� �洢�� , ����洢�ң����Ժ� ���մ洢�ļ� ��Ŀ¼��ͬ
        /**
         * ԭ�� �����ȴ浽 ��ʱ�洢�ң�Ȼ��������д�� ��ӦĿ¼��Ӳ���ϣ�
         * ������˵ ���ϴ�һ���ļ�ʱ����ʵ���ϴ������ݣ���һ������ .tem ��ʽ��
         * Ȼ���ٽ�������д�� ��ӦĿ¼��Ӳ����
         */
        factory.setRepository(new File(path));
        //���� ����Ĵ�С�����ϴ��ļ������������û���ʱ��ֱ�ӷŵ� ��ʱ�洢��
        factory.setSizeThreshold(1024*1024*4) ;

        //��ˮƽ��API�ļ��ϴ�����
        ServletFileUpload upload = new ServletFileUpload(factory);

        try {
            //�����ϴ�����ļ�
            List<FileItem> list = (List<FileItem>)upload.parseRequest(req);

            for(FileItem item : list)
            {
                //��ȡ������������
                String name = item.getFieldName();

                //�����ȡ�� ����Ϣ����ͨ�� �ı� ��Ϣ
                if(item.isFormField())
                {
                    //��ȡ�û�����������ַ��� ���������ͦ�ã���Ϊ���ύ�������� �ַ������͵�
                    String value = item.getString() ;

                    req.setAttribute(name, value);
                }
                //�Դ���ķ� �򵥵��ַ������д��� ������˵�����Ƶ� ͼƬ����Ӱ��Щ
                else
                {
                    /**
                     * ������������Ҫ��ȡ �ϴ��ļ�������
                     */
                    //��ȡ·����
                    String value = item.getName() ;
                    //���������һ����б��
                    int start = value.lastIndexOf("\\");
                    String filename = value;
//                    filename = new String(filename.getBytes(), "utf-8");
                    req.setAttribute(name, filename);

                    //����д��������
                    //���׳����쳣 ��exception ��׽

                    //item.write( new File(path,filename) );//�������ṩ��

                    //�ֶ�д��
                    OutputStream out = new FileOutputStream(new File(path,filename));

                    InputStream in = item.getInputStream() ;

                    int length = 0 ;
                    byte [] buf = new byte[1024] ;

                    System.out.println("��ȡ�ϴ��ļ����ܹ���������"+item.getSize());

                    // in.read(buf) ÿ�ζ��������ݴ����   buf ������
                    while( (length = in.read(buf) ) != -1)
                    {
                        //��   buf ������ ȡ������ д�� ���������������
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
