package com.sma.lang.string;

import com.google.common.collect.ImmutableList;
import com.google.common.io.Resources;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Properties;


public class StringPlayground {
  public static void main(String[] args) throws InterruptedException, IOException {
    new StringPlayground().main();
  }

  public void main() throws IOException {

    // String s = "gs://something/sdew/com.motorola.luna.model/Moto.class";
    // System.out.println(stripThru(s, "/"));
    // System.out.println(keepThru(s.substring(s.indexOf("com.motorola")), "com.").replace('/', '.')
    // );
    //stringContainRegex();
    //immutableList();
    // jsonPrint();
    // printBackwards();
    // stringToProperty();
    // testReadLine();
    // printTimestamp();
    // System.out.println(stripThru("com/bimo/aes.class", "/").split("\\.")[0]);
    //System.out.println(keepThru("com.bimo.13.aes", "ee"));

    //String gcsLocation = "gs://motocare/com.motorola.luna/MotoCareLowFilesystemRecommenderOperation.class";
    //System.out.println(keepThru(gcsLocation.substring(gcsLocation.indexOf("com.motorola")), "/").replace('/', '.'));

    // pass null in, assign value, still null
    String s = null;
    nullParameter(s);
    System.out.println(s);
  }

  void envi() {
    Map<String, String> env = System.getenv();
    for (String envName : env.keySet()) {
      System.out.format("%s=%s%n", envName, env.get(envName));
    }
  }

  void immutableList() {
    List<String> list = new ArrayList<>();
    list.add("something");
    ImmutableList<String> imli = ImmutableList.of("1", "2");
    list.addAll(imli);
    System.out.println(list);
  }

  void nullParameter(String in) {
    in = "abc";
  }

  public void jsonPrint() {
    String input =
        "{\n  \"BQExtractor\": {\n    \"bqQuery\": {\n      \"hashCol\": \"${deviceid}\",\n      \"numSplits\": 10,\n      \"outputDatasetId\": \"${output_dataset}\",\n      \"outputTableId\": \"${output_table}\",\n      \"query\": \"gs://motocare/scripts/battery_low.sql\"\n    },\n    \"exportGcsBucket\": \"ups_res\",\n    \"interestSegmentId\": \"batterylow\",\n    \"name\": \"batterylow\",\n    \"projectId\": \"ups-dev\",\n    \"serviceAccountEmail\": \"271721860868-bg6k9lefb5trv3t7ituucm1d7dte7i9f@developer.gserviceaccount.com\",\n    \"serviceAccountPrivateKey\": \"6acec923cfcae03de7fe799733db85fdd7207069-privatekey.p12\",\n    \"udfGcsObjects\": [\n      \"gs://motocare/classes/MotoCareOperation.class\"\n    ],\n    \"udfPackageName\": \"com.motorola.luna.model\"\n  }\n}";
    JsonElement obj = new JsonParser().parse(input);

    Gson prettyPrinter = new GsonBuilder().setPrettyPrinting().create();
    System.out.println(prettyPrinter.toJson(obj));
    // System.out.println(obj.toString());

    JsonObject wrapper = new JsonObject();
    wrapper.add("value", new Gson().toJsonTree(obj).getAsJsonObject());
    System.out.println(prettyPrinter.toJson(wrapper));
  }

  String keepThru(String str, String pattern) {
    return str.substring(0, Math.max(0, str.lastIndexOf(pattern)));
  }

  void printBackwards() throws InterruptedException {
    System.out.print("hello");
    Thread.sleep(1000); // Just to give the user a chance to see "hello".
    System.out.print("\b\b\b\b\b");
    System.out.println("world");
  }

  String readLinesToString(BufferedReader reader) throws IOException {
    StringBuilder sb = new StringBuilder();
    for (;;) {
      String line = reader.readLine();
      if (line == null) {
        return sb.toString();
      }
      System.out.println(line);
      sb.append(line).append('\n');
    }
  }

  void stringContainRegex() {
    String s = "a = 3\n  b = 4";
    System.out.println(s);
    System.out.println(s.matches("([^=]+=[^=]+\n)+.*"));
  }

  void stringToProperty() {
    final Properties properties = new Properties();
    try {
      URL url = new URL(new URL("file:"), "../ups-dev/config/luna-config-test.properties");
      properties.load(Resources.asByteSource(url).openBufferedStream());
    } catch (IOException e) {
      e.printStackTrace();
    }
    for (Object key : properties.keySet())
      System.out.printf("%s = %s\n", key, properties.get(key));

    System.out.println(properties.getProperty("serviceaccount.email").trim().length());

    String dependency = properties.getProperty("udf.class.dependency").trim();
    String[] fieldsStrings = dependency.split(",")[0].split(":");
    System.out.println(Arrays.toString(fieldsStrings) + " " + fieldsStrings[0].length());
  }

  String stripThru(String str, String pattern) {
    return str.substring(Math.max(0, str.lastIndexOf(pattern) + pattern.length()));
  }

  void testReadLine() throws IOException {
    String s = "adasd \n asd sd\n";
    String processed = readLinesToString(new BufferedReader(new StringReader(s))).trim();
    System.out.println(processed);
    System.out.println(s.equals(processed));
  }
}
