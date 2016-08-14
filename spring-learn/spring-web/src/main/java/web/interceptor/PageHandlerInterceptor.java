package web.interceptor;

import org.springframework.ui.ModelMap;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.context.request.WebRequestInterceptor;

/**
 * 为请求和相应增加分页信息。
 * Created by WangHuanyu on 2015/11/2.
 */
public class PageHandlerInterceptor implements WebRequestInterceptor {
    @Override
    public void preHandle(WebRequest request) throws Exception {
//        String pagesize = request.getParameter(Page.PAGE_SIZE_KEY);
//        String pagenumber = request.getParameter(Page.PAGE_NUMBER_KEY);
//        //存在分页信息，则为当前请求增加分信息。
//        if (pagenumber != null || pagesize != null ){
//            PageHelper.startPage(Integer.parseInt(pagenumber), Integer.parseInt(pagesize));
//        }
    }

    @Override
    public void postHandle(WebRequest request, ModelMap model) throws Exception {
//        Set<String> keyset = model.keySet();
//
//        Page.PageInfo pageinfo = null;
//        for (String key:keyset){
//            Object value = model.get(key);
//            if(value instanceof Page){
//                pageinfo = ((Page) value).getPageInfo();
//            }
//        }
//        if(pageinfo != null){
//            model.put(Page.DEFAULT_PAGE_KEY, pageinfo);
//        }
    }

    @Override
    public void afterCompletion(WebRequest request, Exception ex) throws Exception {

    }

}
