package com.shuoma.alg;

import static com.shuoma.annotation.Tag.Algorithm.Recursion;
import static com.shuoma.annotation.Tag.DataStructure.StringT;
import static com.shuoma.annotation.Tag.Reference.LeetCode;

import com.shuoma.annotation.Tag;

import java.util.ArrayList;

@Tag(algs = Recursion, dss = StringT, references = LeetCode) public class RestoreIPAddress {
  public static void main(String[] args) {
    new RestoreIPAddress().main();
  }

  public void main() {
    String s = "25525511135";
    System.out.println(getRestIPNums(s, 4));

    System.out.println();
    System.out.println(restore(s));
  }

  //second pass

  public ArrayList<String> restore(String s) {
    return restore(s, 0, new ArrayList<String>(), 0);
  }

  public ArrayList<String> restore(String s, int startIdx, ArrayList<String> ip, int n) {
    ArrayList<String> allIPs = new ArrayList<>();
    if (ip.size() == 4) {
      if (n == s.length()) {
        //System.out.println(ip);
        String newIP = "";
        for (int i = 0; i < 4; i++) {
          if (i > 0)
            newIP += ".";
          newIP += ip.get(i);
        }
        allIPs.add(newIP);
      }
      return allIPs;
    }
    for (int i = 1; i <= 3; i++) {
      if (startIdx + i - 1 < s.length()) {
        String part_str = s.substring(startIdx, startIdx + i);
        //int part=Integer.parseInt(part_str);
        if (isValidIPNum(part_str)) {
          ip.add(part_str);
          //System.out.println(ip);
          allIPs.addAll(restore(s, startIdx + i, ip, n + i));
          ip.remove(ip.size() - 1);
        }
      }
    }
    return allIPs;
  }

  //first pass

  public ArrayList<String> restoreIpAddresses(String s) {
    return getRestIPNums(s, 4);
  }

  private ArrayList<String> getRestIPNums(String s, int num_of_parts) {
    ArrayList<String> resultList = new ArrayList<String>();
    boolean found = false;

    if (num_of_parts == 1) {
      if (isValidIPNum(s)) {
        found = true;
        resultList.add(s);
      }
    } else {
      for (int first_part = 1; first_part <= Math.min(4, s.length()); first_part++) {
        String first_str = s.substring(0, first_part);
        if (isValidIPNum(first_str)) {
          ArrayList<String> restList =
              getRestIPNums(s.substring(first_part, s.length()), num_of_parts - 1);
          if (restList != null) {
            for (String rest_part : restList) {
              found = true;
              resultList.add(first_str + "." + rest_part);
            }
          }
        }
      }
    }
    if (num_of_parts == 4) {
      return resultList;
    } else {
      return found ? resultList : null;
    }
  }

  private boolean isValidIPNum(String num_str) {
    if (num_str.length() > 0 && num_str.length() < 4) {
      if (!num_str.startsWith("0") || num_str.length() == 1) {
        int num = Integer.parseInt(num_str);
        if (num >= 0 && num <= 255) {
          return true;
        }
      }
    }
    return false;
  }
}
