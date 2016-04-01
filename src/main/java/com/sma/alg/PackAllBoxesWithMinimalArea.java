package com.sma.alg;

import static com.sma.annotation.Tag.Algorithm.Greedy;
import static com.sma.annotation.Tag.DataStructure.Array;
import static com.sma.annotation.Tag.Reference.Interview;

import com.sma.annotation.Tag;

import java.util.Arrays;
import java.util.Comparator;

/**
 * A box have a length and width. A box can be put in other box if both its length and width is
 * smaller than the other box, and a box can only have a child box, but the box can put in
 * recursively. Given a list of box, calculate the min area needed to put all the box.
 */
@Tag(algs = {Greedy}, dss = {Array}, references = Interview)
public class PackAllBoxesWithMinimalArea {

  public int minArea(Box[] boxes){
    Arrays.sort(boxes, new Comparator<Box>() {
      @Override public int compare(Box o1, Box o2) {
        return o2.area - o1.area;
      }
    });

    int totalArea = 0;
    boolean[] used = new boolean[boxes.length];
    for(int i = 0; i < boxes.length; i++){
      int placement = -1;
      for(int j = i - 1; j >= 0; j--){
        if(!used[j] && boxes[j].canPut(boxes[i])){
          used[j] = true;
          placement = j;
          break;
        }
      }
      if(placement == -1) totalArea += boxes[i].area;
    }
    return totalArea;
  }

  public static void main(String[] args){
    PackAllBoxesWithMinimalArea ins = new PackAllBoxesWithMinimalArea();
    Box[] boxes = new Box[]{new Box(8,9), new Box(7,3), new Box(4,5), new Box(3,1)};
    System.out.println(ins.minArea(boxes));     //92
  }
}

class Box{
  int length;
  int width;
  int area;

  public Box(int length, int width){
    this.length = length;
    this.width = width;
    this.area = this.length * this.width;
  }

  public boolean canPut(Box box){
    if(box.length < length && box.width < width) return true;
    else return false;
  }
}
