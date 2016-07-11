package framework.jdbc;

import java.util.List;

/**
 * Created by drug on 2016/4/5.
 */
public interface ActorService {

    List<Actor> getAll() ;
    Actor get(String fooName);

    Actor getFoo(String fooName, String barName);

    void insert(Actor foo);

    void update(Actor foo);
}
