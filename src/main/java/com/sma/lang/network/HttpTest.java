package com.sma.lang.network;

import java.net.HttpURLConnection;
import java.net.URL;

public class HttpTest {
  public static void main(String[] args)
      throws Exception {
    HttpURLConnection connection = (HttpURLConnection) new URL("http://localhost:9091").openConnection();
    if (connection != null && connection.getResponseCode() == HttpURLConnection.HTTP_OK) {
      System.out.println("connection estsablished!");
    }
  }
}
