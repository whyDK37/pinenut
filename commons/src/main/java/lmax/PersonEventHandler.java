package lmax;

import com.lmax.disruptor.EventHandler;

import java.util.concurrent.TimeUnit;

/**
 * 消费事件处理处理器
 */
public class PersonEventHandler implements EventHandler<PersonEvent> {

    @Override
    public void onEvent(PersonEvent event, long sequence, boolean endOfBatch)
            throws Exception {
        Person person = event.getPerson();
        System.out.println(Thread.currentThread().getName()+"-"+person);
        TimeUnit.MICROSECONDS.sleep(500);
    }

}