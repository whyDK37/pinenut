package framework.c7.calc;

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
