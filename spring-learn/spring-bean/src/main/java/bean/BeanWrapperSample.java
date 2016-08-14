package bean;

import framework.c0.User;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.MutablePropertyValues;
import org.springframework.beans.PropertyAccessorFactory;
import org.springframework.beans.PropertyValues;
import org.springframework.beans.propertyeditors.CustomDateEditor;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by why on 2016/8/14.
 */
public class BeanWrapperSample {
    public static void main(String[] args) {
        Map<String,Object> map = new HashMap<>();
        map.put("name","username");
        map.put("age","12");
        map.put("birthday","2016-01-02");
        PropertyValues pvs = new MutablePropertyValues(map);
        BeanWrapper beanWrapper = PropertyAccessorFactory.forBeanPropertyAccess(new User());

        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        beanWrapper.registerCustomEditor(Date.class,new CustomDateEditor(dateFormat,true));
        beanWrapper.setPropertyValues(pvs, true);

        System.out.println(beanWrapper.getWrappedInstance());
    }
}
