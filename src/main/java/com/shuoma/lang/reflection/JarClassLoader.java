package com.shuoma.lang.reflection;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Enumeration;
import java.util.NoSuchElementException;
import java.util.jar.JarFile;
import java.util.zip.ZipEntry;


public class JarClassLoader extends ClassLoader {
  private final JarFile jarFile;

  public static void main(String[] args) {
    String jarPath = "custom-class-loader.jar";
    String packagePrefix = "test";

    try {
      //URLClassLoader libClassLoader = new URLClassLoader(new URL[] {new File("appengine-mapreduce-0.8.jar").toURL()});
      //URLClassLoader urlClassLoader = new URLClassLoader(new URL[] {new File(jarPath).toURL()}, libClassLoader);

      JarClassLoader jarClassLoader = new JarClassLoader(new File(jarPath)) ;//, libClassLoader);
//      for (Enumeration<JarEntry> entries = jarClassLoader.getJarFile().entries(); entries
//          .hasMoreElements();) {
//        System.out.println(entries.nextElement());
//      }

      String className = packagePrefix + ".DummyGrandchildClass";
      Class<?> loadedClass;

//      jarClassLoader.loadClass("appengine-mapreduce-0.8.jar",
//          "com.google.appengine.tools.mapreduce.impl.CountersImpl");
      loadedClass = jarClassLoader.dynamicLoadClass(className);//loadClass(className);
//      loadedClass = urlClassLoader.loadClass(className);

      DummyBaseClass childIns = (DummyBaseClass) loadedClass.newInstance();
      childIns.foo();

      //Method mainMethod = loadedClass.getDeclaredMethod("main", new Class<?>[] {String[].class});
      //mainMethod.invoke(null, new Object[1]);
      // baseIns.foo();
    } catch (IOException | ClassNotFoundException | InstantiationException | IllegalAccessException
        |  SecurityException | IllegalArgumentException e) {
      e.printStackTrace();
    }
  }

  public JarClassLoader(File f) throws IOException {
    jarFile = new JarFile(f);
  }

  public JarClassLoader(File f, ClassLoader parent) throws IOException {
    //super(parent);
    jarFile = new JarFile(f);
  }

  public JarFile getJarFile() {
    return jarFile;
  }


  public Class<?> loadClassFromLib(String libary, String className)
      throws ClassNotFoundException, IOException {
    ZipEntry entry = jarFile.getEntry("libs/" + libary);
    System.out.println(entry);

    JarClassLoader libCL = new JarClassLoader(new File(entry.getName()));
    return libCL.findClass(className);
  }


  //@MethodOverride
  //protected Class<?> findClass(String name) throws ClassNotFoundException {
  protected Class<?> dynamicLoadClass(String name) throws ClassNotFoundException {
    ZipEntry entry = jarFile.getEntry(name.replace('.', '/') + ".class");
    if (entry == null) {
      throw new ClassNotFoundException(name);
    }
    try {
      byte[] classData = loadClassData(entry);
      System.out.println(name + " class data loaded, class length: " + classData.length);
      return defineClass(name, classData, 0, classData.length);
    } catch (IOException exception) {
      throw new ClassNotFoundException(name, exception);
    }
  }

  @Override
  protected URL findResource(String name) {
    ZipEntry entry = jarFile.getEntry(name);
    if (entry == null) {
      return null;
    }
    try {
      return new URL("jar:file:" + jarFile.getName() + "!/" + entry.getName());
    } catch (MalformedURLException exception) {
      return null;
    }
  }

  @Override
  protected Enumeration<URL> findResources(final String name) {
    return new Enumeration<URL>() {
      private URL element = findResource(name);

      @Override
      public boolean hasMoreElements() {
        return element != null;
      }

      @Override
      public URL nextElement() {
        if (element != null) {
          URL next = element;
          element = null;
          return next;
        }
        throw new NoSuchElementException();
      }
    };
  }

  protected byte[] loadClassData(ZipEntry entry) throws IOException {
    InputStream input = jarFile.getInputStream(entry);
    ByteArrayOutputStream buffer = new ByteArrayOutputStream();
    int data = input.read();
    while(data != -1){
        buffer.write(data);
        data = input.read();
    }
    input.close();

    return buffer.toByteArray();
  }
}
