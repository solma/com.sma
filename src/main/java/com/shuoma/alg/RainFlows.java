package com.shuoma.alg;

import static com.shuoma.annotation.Tag.Algorithm.DepthFirstSearch;
import static com.shuoma.annotation.Tag.DataStructure.MatrixGraph;
import static com.shuoma.annotation.Tag.Reference.LeetCode;

import com.shuoma.annotation.Tag;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * // Given a N*N matrix, each integer in matrix is the elevation of point (row, col). If rain, water will flow to the
 * // lower position, assume the upside and left-side of the matrix is the Pacific Ocean, and downside and right-side
 * // is the Atlantic Ocean. Find out the positions whose rain can both flow into the two ocean.
 */
@Tag(algs = DepthFirstSearch, dss = MatrixGraph, reference = LeetCode)
public class RainFlows {
    public static void main(String[] args) {
        int[][] elevations = {
            {3, 2, 1},
            {4, 5, 1},
            {5, 6, 1},
            {6, 6, 6}
        };
        int[][] res = new RainFlows().highGrounds(elevations);
        for (int[] row : res)
            System.out.println(Arrays.toString(row));
    }

    class Cell {
        int i, j, ele;

        Cell(int i, int j, int ele) {
            this.i = i;
            this.j = j;
            this.ele = ele;
        }
    }

    int[][] highGrounds(int[][] elevations) {
        int m = elevations.length, n = elevations[0].length;
        boolean[][][] flag = new boolean[m][n][2];
        for (int i = 0; i < m; i++)
            for (int j = 0; j < n; j++)
                flag[i][j] = new boolean[2];

        // Pacific side;
        List<List<Cell>> banks = new LinkedList<>();
        banks.add(new LinkedList<Cell>());
        banks.add(new LinkedList<Cell>());

        for (int i = 0; i < m; i++)
            banks.get(0).add(new Cell(i, 0, elevations[i][0]));
        for (int j = 0; j < n; j++)
            banks.get(0).add(new Cell(m - 1, j, elevations[m - 1][j]));
        for (int i = 0; i < m; i++)
            banks.get(1).add(new Cell(i, n - 1, elevations[i][n - 1]));
        for (int j = 0; j < n; j++)
            banks.get(0).add(new Cell(0, j, elevations[0][j]));


        int[][] dirs = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
        for (int bank = 0; bank < 2; bank++)
            for (Cell cur : banks.get(bank)) {
                if (!flag[cur.i][cur.j][bank])
                    dfs(elevations, flag, dirs, cur.i, cur.j, bank, m, n);
            }

        int[][] res = new int[m][n];
        for (int i = 0; i < m; i++)
            for (int j = 0; j < n; j++) {
                if (flag[i][j][0] && flag[i][j][1])
                    res[i][j] = 1;
            }
        return res;
    }

    void dfs(int[][] elevations, boolean[][][] flag, int[][] dirs, int ci, int cj, int bank, int m, int n) {
        flag[ci][cj][bank] = true;
        for (int[] dir : dirs) {
            int i = ci + dir[0], j = cj + dir[1];
            //System.out.println("i=" + i + " j=" + j);
            if (i >= 0 && i < m && j >= 0 && j < n && !flag[i][j][bank] && elevations[ci][cj] < elevations[i][j]) {
                dfs(elevations, flag, dirs, i, j, bank, m, n);
            }
        }
    }
}
