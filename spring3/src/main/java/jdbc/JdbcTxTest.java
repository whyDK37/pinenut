package jdbc;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by drug on 2016/4/5.
 */
public class JdbcTxTest {
    public static void main(String[] args) {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("jdbc-tx.xml", JdbcTxTest.class);
        final ActorService actorService = (ActorService) ctx.getBean("actorService");

        final Thread get = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    System.out.println(actorService.getAll().size());
                }
            }
        });

        Thread insert = new Thread(new Runnable() {
            @Override
            public void run() {
                Actor actor = new Actor();
                actor.setFirstName("first");
                actor.setLastName("last");
                actor.setAge(12);
                actor.setSpecialty("nil");
                try {
                    System.out.println(actorService.getAll().size());
                    get.start();
                    actorService.insert(actor);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        insert.start();
    }
}
