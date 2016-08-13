package boot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.support.PersistenceExceptionTranslator;
import org.springframework.http.converter.ByteArrayHttpMessageConverter;
import org.springframework.http.converter.protobuf.ProtobufHttpMessageConverter;
import org.springframework.web.filter.CharacterEncodingFilter;

import java.util.Arrays;

@SpringBootApplication
@Configuration
//@EnableAutoConfiguration
public class Application {

    public static void main(String[] args) {

        ApplicationContext ctx = SpringApplication.run(Application.class, args);
        System.out.println("Let's inspect the beans provided by Spring Boot:");

//        String[] beanNames = ctx.getBeanDefinitionNames();
//
//        Arrays.sort(beanNames);
//        System.out.println(beanNames.length);
//        for (String beanName : beanNames) {
//            System.out.println(beanName);
//        }
    }

    @Bean
    public PersistenceExceptionTranslator persistenceExceptionTranslator() {
        return new PersistenceExceptionTranslator() {
            @Override
            public DataAccessException translateExceptionIfPossible(RuntimeException e) {
                return null;
            }
        };
    }

    @Bean
    public CharacterEncodingFilter FiltercharacterEncodingFilter() {
        CharacterEncodingFilter characterEncodingFilter = new CharacterEncodingFilter();
        characterEncodingFilter.setEncoding("UTF-8");
        characterEncodingFilter.setForceEncoding(true);
        return characterEncodingFilter;
    }

    @Bean
    public ProtobufHttpMessageConverter protobufHttpMessageConverter() {
        return new ProtobufHttpMessageConverter();
    }

}