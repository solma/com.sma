package com.sma.alg;

import static com.sma.annotation.Tag.DataStructure.StackT;
import static com.sma.annotation.Tag.Reference.LeetCode;

import com.sma.annotation.Tag;

import java.util.Stack;

@Tag(dss = StackT, references = LeetCode)
public class SimplifyPath {

  public String simplifyPath(String path) {
    if (path == null || path.length() == 0)
      return "/";
    String[] dirs = path.trim().split("/");
    Stack<String> stc = new Stack<>();
    for (String s : dirs) {
      if (s.length() == 0 || s.equals(".")) { //pay attention to this line
        continue;
      } else {
        if (s.equals("..")) {
          if (stc.size() > 0) { stc.pop(); }
        } else {
          stc.push(s);
        }
      }
    }
    StringBuilder sb = new StringBuilder();
    //pay attention to this line
    if (stc.isEmpty()) { return "/"; }
    while (!stc.isEmpty()) {
      sb.insert(0, stc.pop());
      sb.insert(0, "/");
    }
    return sb.toString();
  }
}
