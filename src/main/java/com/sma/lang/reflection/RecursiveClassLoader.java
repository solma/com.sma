package com.sma.lang.reflection;

import com.google.common.io.Resources;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Set;

/**
 * This class provides dynamic loading in the sense that the given class and other changed classes
 * are loaded from disk, not from cache. Ensures most recent version is used for those classes.
 */
public class RecursiveClassLoader extends ClassLoader {

  private Set<String> classes;
  private ClassLoader parentClassLoader;
//  public RecursiveClassLoader(Set<String> changedClasses) {
//    this.classes = changedClasses;
//  }

  public RecursiveClassLoader(ClassLoader parent) {
    super(parent);
    parentClassLoader = parent;
  }

  public static void main(String[] args) {
    RecursiveClassLoader classLoader = new RecursiveClassLoader(RecursiveClassLoader.class.getClassLoader());
    try {
      classLoader.loadClass("test.DummyChildClass", "/Users/solma/Desktop/custom-class-loader/test/DummyChildClass.class");
    } catch (ClassNotFoundException e) {
      e.printStackTrace();
    }
  }


  /**
   * Dynamically loads the class from it's binary file. Classes this class depends on will only be
   * loaded dynamically when they are in list of changed classes given at construction.
   *
   * @throws ClassNotFoundException
   */
  public Class<?> dynamicallyLoadClass(String name) throws ClassNotFoundException {
    classes.add(name);
    return loadClass(name);
  }

  /**
   * Finds the class dynamically, contrary to Class.forname which can use cached copies. This means
   * it forces a reload of the class data. Reference: http://stackoverflow.com/questions/3971534/
   */
  @Override
  protected Class<?> findClass(String className) throws ClassNotFoundException {
    try {
      byte[] bytes = loadClassData(className);
      return defineClass(className, bytes, 0, bytes.length);
    } catch (IOException e) {
      e.printStackTrace();
      throw new ClassNotFoundException();
    }
  }

  protected Class<?> loadClass(String className, String classPath) throws ClassNotFoundException {
    try {
      byte[] bytes = loadClassData(classPath);
      return defineClass(className, bytes, 0, bytes.length);
    } catch (IOException e) {
      return parentClassLoader.loadClass(className);
//      e.printStackTrace();
//      throw new ClassNotFoundException();
    }
  }

  @Override
  public Class<?> loadClass(String name) throws ClassNotFoundException {
    return loadClass(name, true);
  }

  /**
   * Overridden to not check the parent's loadClass method first.
   */
  @Override
  protected Class<?> loadClass(String name, boolean resolve) throws ClassNotFoundException {
    Class<?> clazz = findLoadedClass(name);
    if (clazz != null) return clazz;

    //if (classes.contains(name)) {
      clazz = findClass(name);
      resolveClass(clazz);
      return clazz;
    //}
    //return super.loadClass(name, resolve);
  }

  /**
   * Load class data from byte code.
   *
   * @param classFilePath
   * @return
   * @throws IOException
   */
  protected byte[] loadClassData(String classFilePath) throws IOException {
    URL url = new URL("file://" + classFilePath);
    ByteArrayOutputStream buffer = new ByteArrayOutputStream();
    InputStream in = Resources.asByteSource(url).openBufferedStream();
    int data = in.read();
    while(data != -1){
        buffer.write(data);
        data = in.read();
    }
    in.close();
    return buffer.toByteArray();
  }
}
