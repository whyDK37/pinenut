package guava.collection;

import com.google.common.collect.LinkedListMultimap;
import com.google.common.collect.Multimap;

public class MultimapDemo {
    public static void main(String[] args) {
        Multimap<String, String> weekNameMap = LinkedListMultimap.create();
        weekNameMap.put("星期一", "Monday");
        weekNameMap.put("星期二", "Tuesday");
        weekNameMap.put("星期三", "Wednesday");
        weekNameMap.put("星期四", "Thursday");
        weekNameMap.put("星期五", "Friday");
        weekNameMap.put("星期六", "Saturday");
        weekNameMap.put("星期日", "Sunday");
        weekNameMap.put("星期日", "周日");

        System.out.println("星期日的其他名字  " + weekNameMap.get("星期日"));
    }
}