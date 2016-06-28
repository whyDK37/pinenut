package c7.calc;

import c7.calc.Counter;

/**
 * Created by Think on 2016/4/24.
 */
public class CounterImpl implements Counter {

    private int count;
    @Override
    public void increase() {
        count++;
    }

    @Override
    public int getCount() {
        return count;
    }
}
