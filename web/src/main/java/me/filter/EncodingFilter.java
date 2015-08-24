package me.filter;

import javax.servlet.*;
import java.io.IOException;

/**
 * Created by admin on 2015/8/24.
 */
public class EncodingFilter implements Filter {
    private String encoding;

    public void destroy() {

    }

    /**
     * ����������ִ�еĴ�����.
     */
    @Override
    public void doFilter(ServletRequest request, ServletResponse response,
                         FilterChain chain) throws IOException, ServletException {

        //System.out.println("CharacterEncodingFilter--begin");
        //�����ַ���.
        request.setCharacterEncoding(encoding);

        //�õ������������µ���.
        chain.doFilter(request, response);
        //������.
        //System.out.println("CharacterEncodingFilter--end");

    }

    public void init(FilterConfig arg0) throws ServletException {
        this.encoding = arg0.getInitParameter("encoding");
        if(this.encoding == null){
            this.encoding = "utf-8";
        }
    }
}
