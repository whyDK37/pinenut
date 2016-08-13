package framework.c5;

/**
 * Created by whydk on 2016/3/20.
 */
public class TestB {

    private TestA testA;

    public TestB(){}
    public TestB(TestA testA){
        this.testA = testA;
    }
    public TestA getTestA() {

        return testA;
    }

    public void setTestA(TestA testA) {
        this.testA = testA;
    }
}
