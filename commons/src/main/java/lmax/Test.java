package lmax;

public class Test {
    /**
     * @param args
     */
    public static void main(String[] args) {
        PersonHelper.start();
        for (int i = 0; i < 5; i++) {
            Person p = new Person("jbgtwang " + i, i, "男", "1234566" + i);
            //生产者生产数据
            PersonHelper.produce(p);
        }

        PersonHelper.stop();
    }

}