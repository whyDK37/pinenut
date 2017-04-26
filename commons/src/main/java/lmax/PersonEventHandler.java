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
        System.out.println("name = " + person.getName() + ", age = " + person.getAge() + ", gender = " + person.getGender() + ", mobile = " + person.getMobile());
        TimeUnit.MICROSECONDS.sleep(2);
    }

}