package framework.c7;

import org.aspectj.lang.reflect.AjType;
import org.aspectj.lang.reflect.AjTypeSystem;

/**
 * Created by whydk on 2016/12/6.
 */
public class AjTypeSystemTest {

    public static void main(String[] args) {
        AjType ajType = AjTypeSystem.getAjType(AspectJ4Logging.class);
        System.out.println(ajType.isArray());
    }
}
