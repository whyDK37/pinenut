package jdk.util;//package jdk.me.util;
//

import jdk.lang.MyInteger;
import net.sourceforge.sizeof.SizeOf;

import java.util.ArrayList;
import java.util.List;

/**
 * start the jvm with the argument: -javaagent:D:\workspace\mygit\pinenut\java-core\jdk\libs\SizeOf.jar
 */
public class SizeOfTest {
    public static void main(String[] args) {
//        System.out.println(SizeOf.skipStaticField(true));
//        System.out.println(SizeOf.setMinSizeToLog(10);

        System.out.println(SizeOf.sizeOf(new MyInteger(1)));
        System.out.println(SizeOf.sizeOf((short) 1));
        System.out.println(SizeOf.sizeOf(1));
        System.out.println(SizeOf.sizeOf(1L));
        System.out.println(SizeOf.sizeOf(1.2));

        //collection
        System.out.println("collection & array size");
        int[] ia = new int[1];
        List<MyInteger> il = new ArrayList<>(1);
        System.out.println(SizeOf.deepSizeOf(ia));
        System.out.println(SizeOf.deepSizeOf(il));
        System.out.println(SizeOf.deepSizeOf(new ArrayList<>(100)));
        //calculate object size
//        SizeOf.iterativeSizeOf(<your object>)
    }
}
