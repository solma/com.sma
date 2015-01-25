package com.shuoma.alg.bit;

public class SpecialElementInArray {
    public static void main(String[] args) {
        System.out.println(missingElement(new int[] {1, 5, 6, 4, 7, 3}));
        System.out.println(singularElement(new int[] {1, 1, 1, 5, 5, 5, 6, 4, 4, 4, 3, 7, 3, 7, 7, 3}, 3));
    }

    /**
     * Given an array in which each element appears K times and only one element appears once,
     * find the element.
     */
    static int singularElement(int[] a, int k) {
        int ones = 0;
        int twos = 0;
        int not_threes, x;

        String[][] bins = new String[4][a.length + 1];
        bins[1][0] = "ones";
        bins[2][0] = "twos";
        bins[3][0] = "not_threes";
        
        for (int i = 0; i < a.length; ++i) {
            x = a[i];
            twos |= ones & x;
            ones ^= x;
            not_threes = ~(ones & twos);
            ones &= not_threes;
            twos &= not_threes;
            
            bins[0][i + 1] = Integer.toBinaryString(x);
            bins[1][i + 1] = Integer.toBinaryString(ones);
            bins[2][i + 1] = Integer.toBinaryString(twos);
            bins[3][i + 1] = String.valueOf(not_threes);
        }
        
        for(String[] row : bins) {
            for (int i = 0; i < row.length; i++) {
                int width = i == 0 ? 10 : 5;
                System.out.print(String.format("%" + width + "s ", row[i]));
            }
            System.out.println();
        }
        
        return ones;
    }

    /**
     * Given an array of [1...n] with one number missing, find the missing number.
     */
    static int missingElement(int[] a) {
        int res = 0;
        for (int i = 0; i <= a.length; i++) {
            res ^= i + 1;
        }
        for (int i = 0; i < a.length; i++) {
            res ^= a[i];
        }
        return res;
    }
}
