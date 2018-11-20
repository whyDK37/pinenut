package skiplist;

import org.testng.annotations.Test;

import static org.testng.Assert.assertTrue;

public class SkipListTest {

    @Test
    public void main() {
        assertTrue(true);

        SkipList skipList = new SkipList();
        skipList.insert(1);
        skipList.insert(2);

        skipList.printAll();
    }

}