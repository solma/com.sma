package com.shuoma.itw.g;

import java.util.Arrays;
import java.util.LinkedList;

/**
 * // Given a N*N matrix grid, have N^2 house, if the value in house is 1 means the house it locked, you can't pass, if
 * // the value is 0 means the house it opened, if the value is 2, means there is a police in the house.
 * // Write code to get a matrix minDistance[][] is the min Manhattan distance any police can get this house.
 */
public class MinDistanceFromPolice {
    public static void main(String[] args) {
        int[][] roads = {
            {1,0,0,2,0,0,1,2},
            {0,1,0,1,0,0,0,0},
            {1,0,0,1,0,0,1,0}
        };
        
        new MinDistanceFromPolice().minDis(roads);
    }

    class Cell{
        int i;
        int j;  
        int dis;
        
        public Cell(int i, int j, int dis) {
            this.i = i;
            this.j = j;
            this.dis = dis;
        }
        
        @Override
        public String toString() {
            return "i=" + i + " j=" + j + " d=" + dis;
        }
    }


    void minDis(int[][] roads) {
        LinkedList<Cell> toBeVisited = new LinkedList<>();

        for (int i = 0; i < roads.length; i++) {
            for (int j = 0; j < roads[0].length; j++) {
                if (roads[i][j] == 2) {
                    toBeVisited.offer(new Cell(i, j, 0));
                } else if (roads[i][j] == 1){
                    roads[i][j] = -1;
                } else {
                    roads[i][j] = Integer.MAX_VALUE;
                }
            }
        }

        int[][] dirs = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
        // multiple source bfs
        while(!toBeVisited.isEmpty()) {
            Cell top = toBeVisited.poll();
            roads[top.i][top.j] = top.dis;
            //System.out.println(top);
            for(int[] dir : dirs) {
                tryNext(roads, toBeVisited, top, dir);   
            }
        }

        for (int i = 0; i < roads.length; i++) {
            for (int j = 0; j < roads[0].length; j++) {
                if (roads[i][j] == Integer.MAX_VALUE) roads[i][j] = -1;
            }
        }
        

        for (int i = 0; i < roads.length; i++) {
            System.out.println(Arrays.toString(roads[i]));
        }
    }
    
    void tryNext(int[][] roads, LinkedList<Cell> toBeVisited, Cell cur, int[] dir) {
        int i = cur.i + dir[0], j = cur.j + dir[1];
        if (i >=0 && i < roads.length && j >= 0 && j < roads[0].length && roads[i][j] == Integer.MAX_VALUE) {
            toBeVisited.add(new Cell(i, j, cur.dis + 1));
        }
    }
}
