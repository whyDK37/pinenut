package data_structures;

import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * Created by why on 4/28/2017.
 */
public class MyArrayListTest {
    @Test
    public void testSize() throws Exception {
        MyArrayList<Integer> list = new MyArrayList();


        list.add(1);
        Assert.assertEquals(list.indexOf(1), 0);

        list.add(0, 2);
        Assert.assertEquals(list.indexOf(1), 1);

        list.remove(1);

        Assert.assertFalse(list.isEmpty());
        Assert.assertTrue(list.contains(1));
        Assert.assertTrue(list.contains(2));


        for (int i = 0; i < 20; i++) {
            list.add(i);
        }

        System.out.println(list.size());

        for (Integer integer : list) {
            System.out.print(integer + ", ");
        }
    }

}