import aliyun.ots.StringUtils;
import com.aliyun.openservices.ots.model.PrimaryKeyType;

import java.io.UnsupportedEncodingException;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

/**
 * Created by whydk on 2016/6/30.
 */
public class Main {
    public static void main(String[] args) throws UnsupportedEncodingException {
        char c = '`';
        System.out.println((int)c);
        Map<String,String> map  = new LinkedHashMap<String, String>();
        map.put("d","d");
        map.put("2","d");
        map.put("a","d");
        map.put("e","d");
        for (Map.Entry<String, String> entry : map.entrySet()) {
            System.out.println(entry.getKey()+ "-"+entry.getValue());
        }

        System.out.println(StringUtils.appendNinePreString("",2));
    }
}
