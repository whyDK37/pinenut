package framework.c7.calc;

/**
 * Created by Think on 2016/4/24.
 */
public class MinCalculatorImpl implements MinCalculator {
    @Override
    public double min(double a, double b) {
        return a>b?b:a;
    }
}
