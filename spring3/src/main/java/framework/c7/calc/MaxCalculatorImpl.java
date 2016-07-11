package framework.c7.calc;

import org.springframework.aop.framework.AopContext;

/**
 * Created by Think on 2016/4/24.
 */
public class MaxCalculatorImpl implements MaxCalculator {
    @Override
    public double max(double a, double b) {
        System.out.println("AopContext.currentProxy() : "+AopContext.currentProxy());
        return a>b?a:b;
    }
}
