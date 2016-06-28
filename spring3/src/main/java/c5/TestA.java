package c5;


/**
 * Created by whydk on 2016/3/20.
 */
public class TestA {
    private TestB testB;

    public TestA(){}
    public TestA(TestB testB){
        this.testB = testB;
    }
    public TestB getTestB() {
        return testB;
    }

    public void setTestB(TestB testB) {
        this.testB = testB;
    }
}
