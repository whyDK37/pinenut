package jdk.classloader;

import java.io.*;
import java.util.HashMap;

/**
 * 子类优先类加载器。优先加载子类空间的类，如果子类空间没有该类，则交由父类加载。
 *
 * @author why
 * @see ClassLoader
 */
public class FileSystemClassLoader extends ClassLoader {

  /**
   * Set of package names which are not allowed to be loaded from a webapp
   * class loader without delegating first.
   */
  protected static final String[] packageTriggers = {
  };
  private final ClassLoader parent;

  private final ClassLoader j2seClassLoader;

  protected boolean delegate = false;
  /**
   * The cache of ResourceEntry for classes and resources we have loaded,
   * keyed by resource name.
   */
  protected HashMap<String, ResourceEntry> resourceEntries = new HashMap<String, ResourceEntry>();
  private String rootDir;

  public FileSystemClassLoader(String rootDir) {
    this.rootDir = rootDir;
    ClassLoader p = getParent();
    if (p == null) {
      p = getSystemClassLoader();
    }
    this.parent = p;

    ClassLoader j = String.class.getClassLoader();
    if (j == null) {
      j = getSystemClassLoader();
      while (j.getParent() != null) {
        j = j.getParent();
      }
    }
    this.j2seClassLoader = j;
  }

  protected Class<?> findClass(String name) throws ClassNotFoundException {
    byte[] classData = getClassData(name);
    if (classData == null) {
      return super.findClass(name);
    } else {
      return defineClass(name, classData, 0, classData.length);
    }
  }

  private byte[] getClassData(String className) {
    String path = classNameToPath(className);
    try {

      File classFile = new File(path);
      if (!classFile.exists()) return null;

      InputStream ins = new FileInputStream(classFile);
      ByteArrayOutputStream baos = new ByteArrayOutputStream();
      int bufferSize = 4096;
      byte[] buffer = new byte[bufferSize];
      int bytesNumRead;
      while ((bytesNumRead = ins.read(buffer)) != -1) {
        baos.write(buffer, 0, bytesNumRead);
      }
      return baos.toByteArray();
    } catch (IOException e) {
      e.printStackTrace();
    }
    return null;
  }

  /**
   * Load the class with the specified name.  This method searches for
   * classes in the same manner as <code>loadClass(String, boolean)</code>
   * with <code>false</code> as the second argument.
   *
   * @param name Name of the class to be loaded
   * @throws ClassNotFoundException if the class was not found
   */
  @Override
  public Class<?> loadClass(String name) throws ClassNotFoundException {

    return (loadClass(name, false));

  }

  @Override
  protected Class<?> loadClass(String name, boolean resolve) throws ClassNotFoundException {
    synchronized (getClassLoadingLockInternal(name)) {
      Class<?> clazz = null;

      // (0) Check our previously loaded local class cache
      clazz = findLoadedClass0(name);
      if (clazz != null) {
        if (resolve)
          resolveClass(clazz);
        return (clazz);
      }

      // (0.1) Check our previously loaded class cache
      clazz = findLoadedClass(name);
      if (clazz != null) {
        if (resolve)
          resolveClass(clazz);
        return (clazz);
      }

      // (0.2) Try loading the class with the system class loader, to prevent
      //       the webapp from overriding J2SE classes
      try {
        clazz = j2seClassLoader.loadClass(name);
        if (clazz != null) {
          if (resolve)
            resolveClass(clazz);
          return (clazz);
        }
      } catch (ClassNotFoundException e) {
        // Ignore
      }

      // (0.5) Permission to access this class when using a SecurityManager

      boolean delegateLoad = delegate || filter(name);

      // (1) Delegate to our parent if requested
      if (delegateLoad) {
        try {
          clazz = Class.forName(name, false, parent);
          if (clazz != null) {
            if (resolve)
              resolveClass(clazz);
            return (clazz);
          }
        } catch (ClassNotFoundException e) {
          // Ignore
        }
      }

      // (2) Search local repositories
      try {
        clazz = findClass(name);
        if (clazz != null) {
          if (resolve)
            resolveClass(clazz);
          return (clazz);
        }
      } catch (ClassNotFoundException e) {
        // Ignore
      }

      // (3) Delegate to parent unconditionally
      if (!delegateLoad) {
        try {
          clazz = Class.forName(name, false, parent);
          if (clazz != null) {
            if (resolve)
              resolveClass(clazz);
            return (clazz);
          }
        } catch (ClassNotFoundException e) {
          // Ignore
        }
      }
    }

    throw new ClassNotFoundException(name);
  }

  /**
   * Filter classes.
   *
   * @param name class name
   * @return true if the class should be filtered
   */
  protected boolean filter(String name) {

    if (name == null)
      return false;

    // Looking up the package
    String packageName = null;
    int pos = name.lastIndexOf('.');
    if (pos != -1)
      packageName = name.substring(0, pos);
    else
      return false;

    for (int i = 0; i < packageTriggers.length; i++) {
      if (packageName.startsWith(packageTriggers[i]))
        return true;
    }

    return false;

  }

  private Object getClassLoadingLockInternal(String className) {
//		if (JreCompat.isJre7Available() && GET_CLASSLOADING_LOCK_METHOD != null) {
//			try {
//				return GET_CLASSLOADING_LOCK_METHOD.invoke(this, className);
//			} catch (Exception e) {
//				// ignore
//			}
//		}
    return this;
  }

  /**
   * Finds the class with the given name if it has previously been
   * loaded and cached by this class loader, and return the Class object.
   * If this class has not been cached, return <code>null</code>.
   *
   * @param name Name of the resource to return
   */
  protected Class<?> findLoadedClass0(String name) {

    ResourceEntry entry = resourceEntries.get(name);
    if (entry != null) {
      return entry.loadedClass;
    }
    return (null);  // FIXME - findLoadedResource()

  }

  private String classNameToPath(String className) {
    return rootDir + File.separatorChar
            + className.replace('.', File.separatorChar) + ".class";
  }
}
