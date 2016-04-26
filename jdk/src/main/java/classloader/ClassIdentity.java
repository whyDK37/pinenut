package classloader;

import java.io.File;
import java.lang.reflect.Method;


public class ClassIdentity {

    public static void main(String[] args) {
        String build = System.getProperty("user.dir") + File.separator + "jdk\\build\\classes\\main";
        System.out.println(build);
//        build = "D:\\main";
		new ClassIdentity().testClassIdentity(build);
    }

    public void testClassIdentity(String classpath) {
//        String classDataRootPath = "C:\\Documents and Settings\\Administrator\\workspace\\Classloader\\classData";
        FileSystemClassLoader fscl1 = new FileSystemClassLoader(classpath);
        FileSystemClassLoader fscl2 = new FileSystemClassLoader(classpath);
        String className = "classloader.example.Sample";
        try {
            Class<?> class1 = fscl1.loadClass(className);
            Object obj1 = class1.newInstance();
            Class<?> class2 = fscl2.loadClass(className);
            Object obj2 = class2.newInstance();
            System.out.println(obj1.getClass().getClassLoader());
            System.out.println(obj2.getClass().getClassLoader());
            Method setSampleMethod = class1.getMethod("setSample", Object.class);
            setSampleMethod.invoke(obj1, obj2);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
