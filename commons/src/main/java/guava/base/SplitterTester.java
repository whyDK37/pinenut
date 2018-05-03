package guava.base;

import com.google.common.base.Splitter;

public class SplitterTester {
    public static void main(String args[]){
        SplitterTester tester = new SplitterTester();
        tester.testSplitter();
    }

    private void testSplitter(){
        System.out.println(Splitter.on(',')
                .trimResults()
                .omitEmptyStrings()
                .split("the ,quick, , brown         , fox,  ,            jumps, over, the, lazy, little dog."));
    }
}