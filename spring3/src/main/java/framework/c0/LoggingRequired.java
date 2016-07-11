package framework.c0;

import java.lang.annotation.*;

/**
 * Created by Think on 2016/4/24.
 */
@Target({ElementType.METHOD,ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface LoggingRequired {
}
