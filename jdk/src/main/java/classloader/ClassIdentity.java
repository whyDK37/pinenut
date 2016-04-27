package classloader;

import java.io.File;
import java.lang.reflect.Method;
import static java.lang.System.out;

public class ClassIdentity {

    public static void main(String[] args) {
        String build = System.getProperty("user.dir") + File.separator + "jdk\\build\\classes\\";//main
        out.println("自定义class loader加载类失败时会调用父class loader。");
        new ClassIdentity().testClassIdentity(build);
        out.println("会抛异常，因为两个不同的自定义 class loader 加载的类被视为不同的类型。");
        new ClassIdentity().testClassIdentity(build+"main");
    }

    public void testClassIdentity(String classpath) {
//        String classDataRootPath = "C:\\Documents and Settings\\Administrator\\workspace\\Classloader\\classData";
        FileSystemClassLoader fscl1 = new FileSystemClassLoader(classpath);
        ClassLoaderTree.printCLTree(fscl1);
        FileSystemClassLoader fscl2 = new FileSystemClassLoader(classpath);
        String className = "classloader.example.Sample";
        try {
            Class<?> class1 = fscl1.loadClass(className);
            Object obj1 = class1.newInstance();
            Class<?> class2 = fscl2.loadClass(className);
            Object obj2 = class2.newInstance();

            System.out.println("classloader :");
            System.out.println("\tobj1:" + obj1.getClass().getClassLoader());
            System.out.println("\tobj2:" + obj2.getClass().getClassLoader());
            Method setSampleMethod = class1.getMethod("setSample", Object.class);
            setSampleMethod.invoke(obj1, obj2);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
