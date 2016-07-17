package batch;

import org.springframework.boot.SpringApplication;

/**
 * Created by whydk on 2016/7/8.
 */
public class Main {
    public static void main(String [] args) {
        System.exit(SpringApplication.exit(SpringApplication.run(
                BatchConfiguration.class, args)));

    }
}
