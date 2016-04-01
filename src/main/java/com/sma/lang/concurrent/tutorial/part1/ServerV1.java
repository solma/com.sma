package com.sma.lang.concurrent.tutorial.part1;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerV1 {
  public static void main(String[] args) throws IOException {
    ServerSocket socket = new ServerSocket(9000);
    while (true) {
      final Socket s = socket.accept();
      Runnable r = new Runnable() {
        @Override public void run() {
          doWork(s);
        }
      };
      new Thread(r).start();
    }
  }

  static void doWork(Socket s) {
  }
}
