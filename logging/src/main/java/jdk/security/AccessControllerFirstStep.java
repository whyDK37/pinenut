package jdk.security;


import java.io.FilePermission;
import java.security.AccessController;
import java.security.PrivilegedAction;

/**
 * Created by wanghuanyu on 2015/7/3.
 * AccessControllerFir的功能探索习惯代码。
 * 在阅读commons-logging是，经常看到如下代码
 * <pre>
 *     Object result = AccessController.doPrivileged(
         new PrivilegedAction() {
         public Object run() {
         return createFactory(factoryClass, classLoader);
         }
       });
 * </pre>
 * 不理解这样使用的原因。
 */
public class AccessControllerFirstStep {

    public static void main(String[] args) {
        AccessControllerFirstStep accessControllerFirstStep = new AccessControllerFirstStep();
        accessControllerFirstStep.property();
        accessControllerFirstStep.FilePermissionTest();
    }
    public void property() {
        final String key = "user.dir";
        Object result = AccessController.doPrivileged(
                new PrivilegedAction() {
                    public Object run() {
                        return getSystemProperty(key);
//                        return createFactory(factoryClass, classLoader);
                    }
                });

        System.out.println(result);
    }


    public static String getSystemProperty(String key){
        return System.getProperty(key);
    }

    public void FilePermissionTest(){
        FilePermission perm = new FilePermission("/temp/testFile", "read");
        AccessController.checkPermission(perm);
    }
}
