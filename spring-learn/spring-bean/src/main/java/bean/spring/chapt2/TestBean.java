package bean.spring.chapt2;

/**
 * Hello world!
 *
 */
public class TestBean {


    private TestBean testBean;
    private String hello ="Hello World!";
    public void hello()
    {
        System.out.println( hello );
    }

    public void setTestBean(TestBean testBean) {
        this.testBean = testBean;
    }

    public TestBean getTestBean() {
        return testBean;
    }

    public void setHello(String hello) {
        this.hello = hello;
    }

    public void test(){
        System.out.println(this.hello+"...");
    }
}
