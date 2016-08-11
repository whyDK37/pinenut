package framework.c7.calc;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by Think on 2016/4/23.
 */
public class Test {
    public static void main(String[] args) {

        ApplicationContext ac = new ClassPathXmlApplicationContext("c7/calc.xml");

        ArithmeticCalulator arithmeticCalulator = (ArithmeticCalulator)ac.getBean("arithmeticCalulator");
        System.out.println(arithmeticCalulator);
        MaxCalculator maxCalculator = (MaxCalculator)arithmeticCalulator;
        System.out.println(maxCalculator.max(1.2,3));

        MinCalculator minCalculator = (MinCalculator)arithmeticCalulator;
        System.out.println(minCalculator.min(1.2,3));

    }
}
