package lmax;

import com.lmax.disruptor.EventFactory;

/**
 * 消费事件
 */
public class PersonEvent implements EventFactory {

  public final static EventFactory<PersonEvent> EVENT_FACTORY = () -> new PersonEvent();
  private Person person;

  public Person getPerson() {
    return person;
  }

  public void setPerson(Person person) {
    this.person = person;
  }

  @Override
  public Object newInstance() {
    return new Person();
  }
}