package bytebuddy;

import net.bytebuddy.ByteBuddy;
import net.bytebuddy.agent.ByteBuddyAgent;
import net.bytebuddy.dynamic.loading.ClassReloadingStrategy;
import net.bytebuddy.implementation.FixedValue;

import static net.bytebuddy.matcher.ElementMatchers.named;


public class BytebuddyRedefine {
    public static void main(String[] args) throws InstantiationException, IllegalAccessException {
        ByteBuddyAgent.install();
        new ByteBuddy()
                .redefine(User.class)
                .method(named("sayHelloFoo"))
                .intercept(FixedValue.value("Hello Foo Redefined"))
                .make()
                .load(User.class.getClassLoader(),
                        ClassReloadingStrategy.fromInstalledAgent());

        User user = new User();
        System.out.println(user.sayHelloFoo());
    }
}

class User{
    public String sayHelloFoo(){
        return "default";
    }
}

interface Response{
}
