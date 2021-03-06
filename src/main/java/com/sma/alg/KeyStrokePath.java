package com.sma.alg;

import static com.sma.annotation.Tag.DataStructure.Array;
import static com.sma.annotation.Tag.DataStructure.StringT;
import static com.sma.annotation.Tag.Reference.Interview;

import com.sma.annotation.Tag;

/**
 * Given a number n, arrange alphabet into rows each of which has n letters (except the last row)
 * Output the key strokes (direction keys) needed to move current letter C to destination letter D.
 */
@Tag(dss = {Array, StringT}, references = Interview)
public class KeyStrokePath {

  String getPath(char cur, char des, int n) {
    final int[] curCoord = new int[]{(cur - 'A') / n, (cur - 'A') % n};
    final int[] desCoord = new int[]{(des - 'A') / n, (des - 'A') % n};
    final int[] zCoord = new int[]{25 / n, 25 % n};

    StringBuilder ret = new StringBuilder();
    // move left to column zCoord[1]
    if (desCoord[0] == zCoord[0] && curCoord[1] > zCoord[1]) {
      addKeyStrokes(ret, 'l', curCoord[1] - zCoord[1]);
      curCoord[1] = zCoord[1];
    }

    // first move vertically to the destination row
    if (curCoord[0] != desCoord[0]) {
      addKeyStrokes(ret, curCoord[0] > desCoord[0] ? 'u' : 'd', Math.abs(curCoord[0] - desCoord[0]));
    }

    // then move horizontally to the destination column
    if (curCoord[1] != desCoord[1]) {
      addKeyStrokes(ret, curCoord[1] > desCoord[1] ? 'l' : 'r', Math.abs(curCoord[1] - desCoord[1]));
    }

    return ret.toString();
  }

  private void addKeyStrokes(StringBuilder sb, char c, int n) {
    for (int i = 0; i < n; i++) {
      sb.append(c);
    }
  }
}
