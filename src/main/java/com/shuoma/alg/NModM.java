package com.shuoma.alg;

import static com.shuoma.annotation.Tag.Algorithm.Arithmetic;

import com.shuoma.annotation.Tag;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

@Tag(algs = Arithmetic)
public class NModM {
  public static void main(String[] args) {
    try {
      File file = new File(args[0]);
      BufferedReader in = new BufferedReader(new FileReader(file));
      String line;
      while ((line = in.readLine()) != null) {
        String[] lineArray = line.split(",");
        if (lineArray.length > 0) {
          // Process line of input Here
          int N = Integer.parseInt(lineArray[0]);
          int M = Integer.parseInt(lineArray[1]);
          System.out.println(N - N / M * M);
        }
      }
    } catch (IOException e) {
      System.out.println("File Read Error: " + e.getMessage());
    }
  }
}
