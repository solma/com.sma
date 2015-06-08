package com.shuoma.alg;

import static com.shuoma.annotation.Tag.Difficulty.D3;
import static com.shuoma.annotation.Tag.Reference.LeetCode;
import static com.shuoma.annotation.Tag.DataStructure.Stack;
import com.shuoma.annotation.Tag;

import java.util.Stack;

@Tag(dl = D3, dss = Stack, reference = LeetCode)
public class LargestRectangleInHistogram {

  //second pass
  public int largestRectangleArea(int[] height) {
    int n = height.length;
    Stack<Integer> hStc = new Stack<>();
    Stack<Integer> wStc = new Stack<>();
    int maxArea = 0;
    for (int i = 0; i < n; i++) {
      if (hStc.isEmpty() || hStc.peek() <= height[i]) {
        hStc.push(height[i]);
        wStc.push(i);
      } else {
        int lastWidth = 0;
        while (!hStc.isEmpty() && hStc.peek() > height[i]) {
          lastWidth = wStc.pop();
          maxArea = Math.max(maxArea, hStc.pop() * (i - lastWidth));
        }
        hStc.push(height[i]);
        wStc.push(lastWidth);
      }
    }
    while (!hStc.isEmpty()) {
      maxArea = Math.max(maxArea, hStc.pop() * (n - wStc.pop()));
    }
    return maxArea;
  }

  //first pass
  // public int largestSquareArea(int[] height) {
  // Stack<ArrayList<Integer>> stc=new Stack<ArrayList<Integer>>();//first integer is height, second is index
  // int ret=0;
  // for(int i=0;i<height.length;i++){
  // ArrayList<Integer> li=new ArrayList<Integer>();
  // if(stc.isEmpty()||stc.peek().get(0)<=height[i]){
  // li.add(height[i]);
  // li.add(i);
  // stc.push(li);
  // }
  // else{
  // int lastIdx=0;
  // while(!stc.isEmpty()&&stc.peek().get(0)>height[i]){
  // li=stc.pop();
  // lastIdx=li.get(1);
  // int area=li.get(0)*(i-lastIdx);
  // ret=Math.max(area, ret);
  // }

  // li=new ArrayList<Integer>();
  // li.add(height[i]);
  // li.add(lastIdx);
  // stc.push(li);
  // }
  // //System.out.println(stc.peek());
  // }
  // //after the process, there may still be values in stacks, pop out each and test size
  // while(!stc.isEmpty())
  // {
  // //we need compute the size
  // ArrayList<Integer> li=stc.pop();
  // //System.out.println(li);
  // int area = li.get(0)*(height.length - li.get(1));//the width=currentIndex(last one) - stored index
  // ret=Math.max(area, ret);
  // }
  // return ret;
  // }
}
