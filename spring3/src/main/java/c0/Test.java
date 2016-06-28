package c0;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by drug on 2016/4/15.
 */
public class Test {
    public static void main(String[] args) {
        BigDecimal bigDecimal = new BigDecimal("234234");
        System.out.println(bigDecimal.toString());

        BigInteger bigInteger = new BigInteger("234");
        bigInteger = new BigInteger("123456789987654321123456789987654321123456789987654321");
        System.out.println(bigInteger);

        //保持引用，防止自动垃圾回收
        List<String> list = new ArrayList<String>(2000);
        int i = 0;
        while(true){
            //通过intern方法向常量池中手动添加常量
            list.add(String.valueOf("通过intern方法向常量池中手动添加常量"+i++).intern());
            System.out.println(list.get(list.size()-1));
        }
    }
}
