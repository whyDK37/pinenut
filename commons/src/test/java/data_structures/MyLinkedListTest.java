package data_structures;

import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * Created by why on 4/28/2017.
 */
public class MyLinkedListTest {
    @Test
    public void testAdd() throws Exception {
        MyLinkedList<String> list = new MyLinkedList();
        list.add("1");
        list.add("2");
        list.add("3");
        list.add("4");
        list.add("5");
        list.add("6");
        for (int i = 0; i < list.size(); i++) {
            System.out.println((i < (list.size() >> 1)));
        }

        Assert.assertEquals(list.indexOf("2"), 1);
        Assert.assertEquals(list.get(1), "2");

        list.add(6, "333");

        list.remove(0);
        list.remove(list.size() - 1);

        for (String integer : list) {
            System.out.print(integer + ", ");
        }
    }

}