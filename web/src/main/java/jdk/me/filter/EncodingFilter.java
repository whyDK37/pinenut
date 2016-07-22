package jdk.me.filter;

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
     * 过滤器真正执行的处理功能.
     */
    @Override
    public void doFilter(ServletRequest request, ServletResponse response,
                         FilterChain chain) throws IOException, ServletException {

        //System.out.println("CharacterEncodingFilter--begin");
        //设置字符集.
        request.setCharacterEncoding(encoding);

        //拿到链条继续向下调用.
        chain.doFilter(request, response);
        //返回了.
        //System.out.println("CharacterEncodingFilter--end");

    }

    public void init(FilterConfig arg0) throws ServletException {
        this.encoding = arg0.getInitParameter("encoding");
        if(this.encoding == null){
            this.encoding = "utf-8";
        }
    }
}
