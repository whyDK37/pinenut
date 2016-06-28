package jdbc;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by drug on 2016/4/5.
 */
public class DefaultActorService implements ActorService {
    private JdbcTemplate jdbcTemplate;
    private String select_sql = "select id, first_name, last_name,specialty,age from T_ACTOR";
    private String insert_sql = "insert into t_actor (first_name, last_name,age,specialty) values (?, ?, ? , ?)";

    public List<Actor> getAll() {
        System.out.println("get all");
        List<Actor> actors = this.jdbcTemplate.query(
                select_sql,
                new RowMapper<Actor>() {
                    public Actor mapRow(ResultSet rs, int rowNum) throws SQLException {
                        Actor actor = new Actor();
                        actor.setId(rs.getInt("id"));
                        actor.setFirstName(rs.getString("first_name"));
                        actor.setLastName(rs.getString("last_name"));
                        actor.setAge(rs.getInt("age"));
                        actor.setSpecialty(rs.getString("specialty"));
                        return actor;
                    }
                });
        System.out.println("get all done");
        return actors;
    }

    @Override
    public Actor get(String fooName) {
        return null;
    }

    @Override
    public Actor getFoo(String fooName, String barName) {
        return null;
    }

    @Override
    public void insert(Actor foo) {
        System.out.println("before insert...");
        this.jdbcTemplate.update(insert_sql, foo.getFirstName(), foo.getLastName(), foo.getAge(), foo.getSpecialty());
        System.out.println("insert done");
//        if(true) throw new RuntimeException("ee");
    }

    @Override
    public void update(Actor foo) {

    }

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
}
