package com.sma.lang.system;

import com.google.common.io.Resources;

import java.io.IOException;
import java.net.URL;
import java.util.Properties;


public class ResourcePath {
  public static void main(String[] args) {
    new ResourcePath().main();
  }

  public void main() {
    //getResource();
    System.out.println(Runtime.getRuntime().availableProcessors());
  }

  void getResource() {
    Properties properties = new Properties();
    //"/Users/solma/workspace/ups-dev/config/luna-config-template.properties"
    //"../ups-dev/config/luna-config-template.properties"
    String configFilePath = "../ups-dev/config/luna-config-template.properties";
    try {
      URL url;
      if (configFilePath.startsWith("/")){
        url = new URL("file://"+configFilePath);
      } else {
        url = new URL(new URL("file:"), configFilePath);
      }
      properties.load(Resources.asByteSource(url).openBufferedStream());
      for (Object key : properties.keySet())
        System.out.printf("%s = %s\n", key, properties.get(key));
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
