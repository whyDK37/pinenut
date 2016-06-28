package jdbc;

import org.apache.commons.dbcp.BasicDataSource;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * Created by drug on 2016/4/5.
 */
public class DbcpTest {
    public static void main(String[] args) {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("jdbc-dbcp.xml", DbcpTest.class);
        BasicDataSource ds = ctx.getBean("dataSource", BasicDataSource.class);
        System.out.println(ds);
        System.out.println(ds.getNumActive() +"-"+ ds.getNumIdle());
        try {
            Connection connection = ds.getConnection();
            System.out.println(ds.getNumActive() +"-"+ ds.getNumIdle());
            Connection connection1 = ds.getConnection();
            System.out.println(ds.getNumActive() +"-"+ ds.getNumIdle());
            Connection connection2 = ds.getConnection();
            System.out.println(ds.getNumActive() +"-"+ ds.getNumIdle());
            System.out.println(connection.toString());

            connection.close();
            System.out.println(ds.getNumActive() +"-"+ ds.getNumIdle());
            connection.close();
            System.out.println(ds.getNumActive() +"-"+ ds.getNumIdle());
            connection.close();
            System.out.println(ds.getNumActive() +"-"+ ds.getNumIdle());

        } catch (SQLException e) {
            e.printStackTrace();
        }

//
//        final ActorService actorService = (ActorService) ctx.getBean("actorService");
//
//        final Thread get = new Thread(new Runnable() {
//            @Override
//            public void run() {
//                while (true) {
//                    System.out.println(actorService.getAll().size());
//                }
//            }
//        });
//
//        Thread insert = new Thread(new Runnable() {
//            @Override
//            public void run() {
//                Actor actor = new Actor();
//                actor.setFirstName("first");
//                actor.setLastName("last");
//                actor.setAge(12);
//                actor.setSpecialty("nil");
//                try {
//                    System.out.println(actorService.getAll().size());
//                    get.start();
//                    actorService.insert(actor);
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
//            }
//        });
//
//        insert.start();
    }
}
