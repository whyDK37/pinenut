package framework.c0;

/**
 * Created by whydk on 2016/3/10.
 */
public class MyTestBean {

    private MyTestBean2 myTestBean2;
    public MyTestBean(){

    }
    public MyTestBean(String teststr){
        this.teststr = teststr;
    }
    private String teststr;

    public String getTeststr() {
        return teststr;
    }

    public void setTeststr(String teststr) {
        this.teststr = teststr;
    }


    public String test(){
        System.out.println(teststr);
        return teststr;
    }

    @LoggingRequired
    public String logging(){
        System.out.println(teststr+" logging(1)");
        myTestBean2.logging();
        return teststr;
    }

    public MyTestBean2 getMyTestBean2() {
        return myTestBean2;
    }

    public void setMyTestBean2(MyTestBean2 myTestBean2) {
        this.myTestBean2 = myTestBean2;
    }
}
