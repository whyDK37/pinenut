
import com.aliyun.openservices.ots.model.Direction;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.BeanUtilsBean;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by whydk on 2016/6/30.
 */
public class Main {
    private String a;
    Integer b;
    Boolean c;
    Double d;
    public static void main(String[] args) throws UnsupportedEncodingException {
//        AtomicInteger atomicInteger = new AtomicInteger(1);
//        for (int i = 0; i < 100; i++) {
//            System.out.println(atomicInteger.addAndGet(1));
//        }
//


    }

    public static <T> List<T> a(Object a) {
        List<T> list = new ArrayList<T>();
        list.add((T)a);
        return list;
    }
}
